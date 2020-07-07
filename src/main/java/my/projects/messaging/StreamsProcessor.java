package my.projects.messaging;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

public class StreamsProcessor implements Runnable {
    private KafkaStreams streams;

    public StreamsProcessor(String topic) {
        Properties streamsConfiguration = buildStreamConfig();

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> textLines = builder.stream(topic);
        Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);

        KTable<String, Long> wordCounts = textLines
                .flatMapValues(value -> Arrays.asList(pattern.split(value == null ? "zip".toLowerCase() : value)))
                .groupBy((key, word) -> word)
                .count();

        System.out.println("KTable prepared...");
        wordCounts.toStream().foreach((w, c) -> System.out.println("word: " + w + " -> " + c));

        this.streams = new KafkaStreams(builder.build(), streamsConfiguration);
        this.streams.cleanUp();
    }

    private Properties buildStreamConfig() {
        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(
                StreamsConfig.APPLICATION_ID_CONFIG,
                "wordcount-live-test");
        streamsConfiguration.put(
                StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
                IKafkaConstants.KAFKA_BROKERS);
        streamsConfiguration.put(
                StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
                Serdes.String().getClass().getName());
        streamsConfiguration.put(
                StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
                Serdes.String().getClass().getName());
        streamsConfiguration.put(
                StreamsConfig.STATE_DIR_CONFIG,
                "C:\\develop\\mystreamstest\\");
        return streamsConfiguration;
    }

    @Override
    public void run() {
        System.out.println("Starting kafka streams...");
        streams.start();
    }

    public void close() {
        System.out.println("Closing Kafka Streams...");
        streams.close();
        System.out.println("Kafka Streams greacefully closed.");

    }
}
