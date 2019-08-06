package demo.blockingQueue;

public class MyDataStore {

	private String data[];
	private int index;
	private int waitTimeOut = 1000;

	public MyDataStore() {
		super();
		data = new String[10];
		index = -1;
	}

	public boolean add(String str) throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (index < data.length - 1) {
					data[++index] = str;					
					return true;
				} else {
					System.out.println("add waiting: "+Thread.currentThread().getName());
					this.notifyAll();
					this.wait(waitTimeOut);
				}
			}
		}

	}

	public String get() throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (index >= 0) {
					return data[index--];
				} else {
					System.out.println("get waiting: "+Thread.currentThread().getName());
					this.notifyAll();
					this.wait(waitTimeOut);
				}
			}
		}
	}

}
