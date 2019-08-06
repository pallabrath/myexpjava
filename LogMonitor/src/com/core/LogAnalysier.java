package com.core;

import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;



public class LogAnalysier extends Thread {
	private int avgRequestPerSec = 10;
	private long alertInterval = 120000;	
	private long tenSec = 10000;
	private long lastTime = Long.MAX_VALUE;
	private boolean continueAnalyize;
	private boolean highTraffic;
	private BlockingQueue<HttpLog> httpLogs;
	private BlockingQueue<String> msgQueue;
	private CircularArray<Integer> circleArr;

	public LogAnalysier(int avgRequestPerSec, BlockingQueue<HttpLog> httpLogs, BlockingQueue<String> msgQueue) {
		super();
		this.avgRequestPerSec = avgRequestPerSec;
		this.continueAnalyize = true;
		this.httpLogs = httpLogs;
		this.msgQueue = msgQueue;
		circleArr = new CircularArray<Integer>((int) (alertInterval / 10000));
	}

	// This will analyze logs and collect stat and look for alert
	public void run() {
		boolean firstTime = true;
		long tr = avgRequestPerSec * (alertInterval / 1000);
		while (continueAnalyize) {
			HttpLog aLog = null;
			SectionCount scount = null;
			Map<String, SectionCount> countMap = new HashMap<String, SectionCount>();
			Long logTime = 0L;
			Long lt= 0L;			
			// processing logs for 10 sec
			while (logTime <= lastTime + tenSec) {
				try {
					aLog = httpLogs.poll(tenSec, TimeUnit.MILLISECONDS);
					if (aLog == null)
					{
						break;
					}
					lt = aLog.getTs().getTime();
					if (firstTime)
					{
						lastTime = lt;
						firstTime = false;
					}
					String section = getSection(aLog.getClientRequest());
					if (countMap.containsKey(section)) {
						scount = countMap.get(section);
						scount.inc();
					} else {
						countMap.put(section, new SectionCount(section, 1));
					}
					logTime = aLog.getTs().getTime();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// prepare summary for 10 sec period  and publish			
			Collection<SectionCount> sectionStats = countMap.values();			
			int noOfReq = 0;
			if (sectionStats.size() > 0) {
			    List<SectionCount> sorted = Arrays.asList(sectionStats.toArray(new SectionCount[0]));
			    Collections.sort(sorted);
				StringBuffer msg = new StringBuffer("10 sec Stat");
				msg.append("****************\n");
				for (SectionCount sc : sorted) {
					msg.append("\n");
					msg.append(sc);
					noOfReq += sc.getTrafficCount();
				}
				msg.append("\nToatl number of request\t" + noOfReq + "\n");
				try {
					msgQueue.put(msg.toString());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			circleArr.set(noOfReq);
			int noOfReqs2Min = circleArr.sum();
			Date sysdate = new Date();
			if (noOfReqs2Min > tr) {				
				// raise an alert
				try {					
					if (highTraffic == false)
					{
						msgQueue.put("High traffic generated an alert - hits = " + noOfReqs2Min + ", triggered at "
							+ sysdate + " .");
					}
					highTraffic = true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else if (highTraffic) {
				try {
					msgQueue.put("High traffic generated an alert cleared - hits = " + noOfReqs2Min + ", triggered at "
							+ sysdate + " .");
					highTraffic = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			lastTime = lt;
		}
	}

	public void stopAnalyize() {
		continueAnalyize = false;
	}

	/**
	 * It takes client request as parameter and return section ex : GET /report
	 * HTTP/1.0 it will return /report
	 * 
	 * @param str
	 * @return
	 */
	public String getSection(String str) {
		String[] sr = str.split("\\s+");
		String section = sr[1];
		try {
			if (section.contains(".")) {
				int ti = section.indexOf("/", section.lastIndexOf("."));				
				int sti = section.indexOf("/", ti + 1);				
				if (sti != -1) {
					section = section.substring(0, sti);
				}
			}
		} catch (Exception exp) {
			exp.toString();
		}
		return section;
	}
}
