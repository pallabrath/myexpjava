package demo.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class ProducerBq implements Runnable {
	private BlockingQueue<String> dataStore;
	String greetings[] = { "Hello", "Hallo", "Good Morning", "Guten Morgain", "Bye", "Sayo Nara" };
	private int runTill;

	@Override
	public void run() {
		for (int i = 0; i < runTill; i++) {
			int ri = (int) (Math.random() * 10) % greetings.length;
			dataStore.offer(greetings[ri]);
		}
	}

	public ProducerBq(BlockingQueue<String> dataStore , int rt) {
		super();
		this.dataStore = dataStore;
		this.runTill = rt;
	}

}