package my.projects.messaging;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static my.projects.messaging.IKafkaConstants.KAFKA_BROKERS;

public class App {
    private static StreamsProcessor streamsProcessor;
    private static  ProducerRunnable<String, String> producerRunnable;

    public static void main(String[] args) {
        // Add shutdown hook to respond to SIGTERM and gracefully close Kafka Streams
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            streamsProcessor.close();
            producerRunnable.close();
        }));

        createKafkaTopic("demo", KAFKA_BROKERS);

        streamsProcessor = new StreamsProcessor("demo");
        runStreams();
        runProducer();
    }

    private static void runStreams() {
        streamsProcessor.run();
    }

    static void runConsumer() {
        KafkaProcessor<Long, String> processor = new KafkaProcessor<>(List.of("demo"), Objects::toString, Objects::toString, LongDeserializer.class, StringDeserializer.class);
        try {
            processor.init(5);
        }catch (Exception exp) {
            processor.shutdown();
        }
    }
    static void runProducer() {
        producerRunnable = new ProducerRunnable<>("demo", StringSerializer.class, StringSerializer.class) {
            @Override
            public String getKey(int key) {
                return String.valueOf(key);
            }

            @Override
            public String getValue(String value) {
                return value;
            }
        };

        producerRunnable.run();
    }

    public static void createKafkaTopic(String sourceTopicName, String kafkaUrl) {

        try {
            Properties properties = new Properties();
            properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
            AdminClient kafkaAdminClient = KafkaAdminClient.create(properties);
            ListTopicsResult topics = kafkaAdminClient.listTopics();
            Set<String> names = topics.names().get();

            boolean containsSourceTopic = names.contains(sourceTopicName);

            if (!containsSourceTopic) {
                CreateTopicsResult result = kafkaAdminClient.createTopics(
                        Stream.of(sourceTopicName).map(
                                name -> new NewTopic(name, 1, (short) 1)
                                        .configs(Map.of(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT))
                        ).collect(Collectors.toList())
                );
                //result.all().get();
                System.out.println("new sourceTopicName: " + sourceTopicName);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
