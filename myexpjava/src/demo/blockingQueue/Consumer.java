package demo.blockingQueue;

public class Consumer implements Runnable {

	private MyDataStore dataStore;
	private int runTill;

	@Override
	public void run() {
		for (int i = 0; i < runTill; i++) {
			try {
				System.out.println(dataStore.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Consumer(MyDataStore dataStore, int runTill) {
		super();
		this.dataStore = dataStore;
		this.runTill = runTill;
	}

}
