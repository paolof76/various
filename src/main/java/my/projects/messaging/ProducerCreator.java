package my.projects.messaging;

import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;
public class ProducerCreator {
    public static Properties createProducerProperties(String keyClassName, String valueClassName) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keyClassName);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueClassName);
        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
        return props;
    }
}
