package com.core;

import java.util.concurrent.BlockingQueue;

public class HttpLogParser extends Thread {
	private BlockingQueue<String> buffer;
	private BlockingQueue<HttpLog> httpLogs;
	private boolean continueParse;

	public HttpLogParser(BlockingQueue<String> buffer, BlockingQueue<HttpLog> httpLogs) {
		super();
		this.buffer = buffer;
		this.httpLogs = httpLogs;
		continueParse = true;
	}

	@Override
	public void run() {
		HttpLog hl = null;
		while (continueParse) {
			try {
				String strLog = buffer.take();
				hl = HttpLog.parseHttpLog(strLog);
				if (hl != null)
				{
					httpLogs.put(hl);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void stopParsing() {
		continueParse = false;
	}
}
