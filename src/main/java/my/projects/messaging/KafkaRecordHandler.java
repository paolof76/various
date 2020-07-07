package my.projects.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.function.Function;

public class KafkaRecordHandler<K, V> implements Runnable {
    private ConsumerRecord<K, V> record;
    private Function<K, String> keyConsumer;
    private Function<V, String> valueConsumer;

    public KafkaRecordHandler(ConsumerRecord<K, V> record, Function<K, String> keyConsumer, Function<V, String> valueConsumer) {
        this.record = record;
        this.keyConsumer = keyConsumer;
        this.valueConsumer = valueConsumer;
    }
    @Override
    public void run() { // this is where further processing happens
        System.out.println("Record consumed, key:" + keyConsumer.apply(record.key()));
        System.out.println("Record consumed, value:" + valueConsumer.apply(record.value()));
        System.out.println("Thread id = "+ Thread.currentThread().getId());
    }
}
