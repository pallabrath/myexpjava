package demo.blockingQueue;

public class ProducerConsumer {
	public static void main(String args[])
	{
		MyDataStore dataStore = new MyDataStore();
		Thread producer = new Thread(new Producer(dataStore, 15), "producer");
		Thread consumer = new Thread(new Consumer(dataStore, 15), "consumer");
		producer.start();
		consumer.start();
	}
	
}
