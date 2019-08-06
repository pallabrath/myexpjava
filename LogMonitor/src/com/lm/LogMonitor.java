package com.lm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

import com.core.FileObserver;
import com.core.HttpLog;
import com.core.HttpLogParser;
import com.core.LogAnalysier;

public class LogMonitor {
	public static String CONFIG_FILE = "config.properties";
	public static String LOG_FILE_NAME = "logfile";
	public static String AVG_REQ_PERSEC = "avgRequestPerSec";
	public static String DELAY = "delay";	
	public static void main (String[] args) throws InterruptedException
	{
		System.out.println("Welcome to Log Monitor ...");
		try {
			Properties prop = getInputProperties(CONFIG_FILE);
			// TO DO validate prop
			
			ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(1000);
			FileObserver lfo = new FileObserver(prop.getProperty(LOG_FILE_NAME), 
					buffer, Long.parseLong(prop.getProperty(DELAY)));
			System.out.println("Log montior will read " + prop.getProperty(LOG_FILE_NAME));
			// It will start reading the log files.
			lfo.startObserving();
			ArrayBlockingQueue<HttpLog> httpLogs = new ArrayBlockingQueue<HttpLog>(1000);
			HttpLogParser httpParser = new HttpLogParser(buffer, httpLogs);	
			httpParser.start();
			ArrayBlockingQueue<String> msgQueue = new ArrayBlockingQueue<String>(1000);
			int avgRequestPerSec = Integer.parseInt(prop.getProperty(AVG_REQ_PERSEC));
			LogAnalysier lga = new LogAnalysier(avgRequestPerSec, httpLogs, msgQueue);
			lga.start();
			while (true)
			{
				System.out.println(msgQueue.take());
			}
		} catch (IOException e) {
			
			System.out.println(CONFIG_FILE + "is not found at classpath" );
		}
	}
	private static Properties getInputProperties(String fileName) throws IOException {
		Properties prop = new Properties();				
		InputStream input = LogMonitor.class.getResourceAsStream(fileName);
		prop.load(input);
		return prop;
	}
}
