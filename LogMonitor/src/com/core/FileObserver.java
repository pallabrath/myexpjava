/**
 * This class will be used to read a file continuously.
 * 
 * @author pallab.rath@gmail.com
 *
 */
package com.core;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;

public class FileObserver implements TailerListener {

	private String filePath;
	private BlockingQueue<String> buffer;
	private long delay;
	private Tailer tailer;

	@Override
	public void fileNotFound() {

		System.out.println("Error: File not found !!! " + filePath);
	}

	@Override
	public void fileRotated() {
		System.out.println("Error: File rotation detected !!! " + filePath);

	}

	@Override
	public void handle(String line) {
		try {
			buffer.put(line);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handle(Exception arg0) {
		arg0.printStackTrace();
	}

	@Override
	public void init(Tailer arg0) {
		tailer = arg0;
	}
	
	public void stopObserving()
	{
		tailer.stop();
	}
	
	public void startObserving()
	{		
		Tailer tailer = new Tailer(new File(filePath), this, delay);
	    Thread thread = new Thread(tailer);
	    thread.setDaemon(true);
	    thread.start();
	}

	public FileObserver(String filePath, BlockingQueue<String> buffer, long delay) {
		super();
		this.filePath = filePath;
		this.buffer = buffer;
		this.delay = delay;
	}

}
