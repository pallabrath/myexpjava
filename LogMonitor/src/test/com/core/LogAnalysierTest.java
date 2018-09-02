package test.com.core;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;

import com.core.HttpLog;
import com.core.LogAnalysier;

public class LogAnalysierTest {
	
	
	@Test(timeout = 180000)
	// timeout in 3 minute
	public void testLogAnalysier() throws InterruptedException {
		// check for alert
		ArrayBlockingQueue<HttpLog> httpLogs = new ArrayBlockingQueue<HttpLog>(1000);
		ArrayBlockingQueue<String> msgQueue = new ArrayBlockingQueue<String>(1000);
		// creating logs
		SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyy:HH:mm:ss ZZZZ");		
		String logFormat = "127.0.0.1 - james [%s] \"GET /api/test HTTP/1.0\" 200 1234\n";
		// Add 200 logs
		for (int i = 0; i < 200; i++)
		{
			Date nowDate = new Date();
			httpLogs.put(HttpLog.parseHttpLog(String.format(logFormat, sd.format(nowDate))));			
		}
		LogAnalysier lga = new LogAnalysier(1, httpLogs, msgQueue);
		lga.start();
		while (true)
		{
			String str = msgQueue.take();			
			if (str != null && str.contains("High traffic generated an alert"));
			{
				break;
			}
		}
		lga.stopAnalyize();		
	}
	
	@Test
	public void testGetSection()
	{
		LogAnalysier  la = new LogAnalysier(0, null, null);
		String test1 = "GET /api/user HTTP/1.0";
		String test2 = "GET http://my.site.com/pages/create HTTP/1.0";
		String test3 = "GET http://my.site.com/pages HTTP/1.0";
		assertEquals("/api/user", la.getSection(test1));
		assertEquals("http://my.site.com/pages", la.getSection(test2));
		assertEquals("http://my.site.com/pages", la.getSection(test3));
	}

}
