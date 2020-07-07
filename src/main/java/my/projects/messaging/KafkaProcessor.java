package my.projects.messaging;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class KafkaProcessor<K, V> {
    private Consumer<K, V> consumer;
    private ExecutorService executor;
    private Function<K, String> keyConsumer;
    private Function<V, String> valueConsumer;

    public KafkaProcessor(List<String> topics, Function<K, String> keyConsumer, Function<V, String> valueConsumer, Class<?> kClass, Class<?> vClass) {
        this.keyConsumer = keyConsumer;
        this.valueConsumer = valueConsumer;
        Properties properties = ConsumerCreator.createConsumerProperties(kClass.getName(), vClass.getName());
        this.consumer = new KafkaConsumer<>(properties);
        this.consumer.subscribe(topics);
    }



    public void init(int numberOfThreads) {
        //Create a threadpool
        executor = new ThreadPoolExecutor(numberOfThreads, numberOfThreads, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
        while (true) {
            ConsumerRecords<K, V> records = consumer.poll(Duration.ofMillis(100));
            System.out.println("Records received: " + records.count());
            for (final ConsumerRecord<K, V> record : records) {
                executor.submit(new KafkaRecordHandler<>(record, keyConsumer, valueConsumer));
            }
        }
    }
    public void shutdown() {
        if (consumer != null) {
            consumer.close();
        }
        if (executor != null) {
            executor.shutdown();
        }
        try {
            if (executor != null && !executor.awaitTermination(60, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        }catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
