package demo.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class ConsumerBq implements Runnable {

	private BlockingQueue<String> dataStore;
	private int runTill;

	@Override
	public void run() {
		for (int i = 0; i < runTill; i++) {
			try {
				System.out.println(dataStore.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public ConsumerBq(BlockingQueue<String> dataStore, int runTill) {
		super();
		this.dataStore = dataStore;
		this.runTill = runTill;
	}

}