package demo.blockingQueue;

public class Producer implements Runnable {
	private MyDataStore dataStore;
	String greetings[] = { "Hello", "Hallo", "Good Morning", "Guten Morgain", "Bye", "Sayo Nara" };
	private int runTill;

	@Override
	public void run() {
		for (int i = 0; i < runTill; i++) {
			int ri = (int) (Math.random() * 10) % greetings.length;
			try {
				dataStore.add(greetings[ri]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Producer(MyDataStore dataStore , int rt) {
		super();
		this.dataStore = dataStore;
		this.runTill = rt;
	}

}
