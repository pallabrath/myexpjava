package demo.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBq {
	public static void main(String args[])
	{
		BlockingQueue<String> dataStore = new ArrayBlockingQueue<String>(10);
		Thread producer = new Thread(new ProducerBq(dataStore, 15), "producer");
		Thread consumer = new Thread(new ConsumerBq(dataStore, 15), "consumer");
		producer.start();
		consumer.start();
	}
}
