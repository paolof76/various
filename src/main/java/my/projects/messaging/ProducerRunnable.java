package my.projects.messaging;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public abstract class ProducerRunnable<K, V> implements Runnable {
    final private Producer<K, V> producer;
    private String topic;

    public ProducerRunnable(String topic, Class<?> kClass, Class<?> vClass) {
        this.topic = topic;
        this.producer = new KafkaProducer<>(ProducerCreator.createProducerProperties(kClass.getName(), vClass.getName()));

    }

    @Override
    public void run() {
        System.out.println("Starting producer...");
        int index = 0;
        while (true) {
            ProducerRecord<K, V> record = new ProducerRecord<>(topic, getKey(index), getValue(getRandomPhrase()));
            try {
                producer.send(record).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            index++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void close() {
        System.out.println("Closing producer...");

        producer.flush();
        producer.close();
        System.out.println("Producer completed writing and closed gracefully.");
    }

    public abstract K getKey(int key);

    public abstract V getValue(String value);


    private String getRandomPhrase() {
        int index = new Random().nextInt(10);
        return words.getOrDefault(index, "not-found");
    }

    private static Map<Integer, String> words = Map.of(
            1, "hello",
            2, "world",
            3, "hi",
            4, "planet",
            5, "bye",
            6, "blue dot",
            7, "home",
            8, "aloa",
            9, "celestial object",
            0, "good to see you"
    );
}
