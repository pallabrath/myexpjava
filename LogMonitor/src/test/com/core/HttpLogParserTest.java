package test.com.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.core.HttpLog;
import com.core.HttpLogParser;

public class HttpLogParserTest {
	
	@Test
	public void testHttpLogParser() throws IOException, InterruptedException {
		String testLog = "127.0.0.1 - james [09/May/2018:16:00:39 +0000] \"GET /report HTTP/1.0/\" 200 1234";
		ArrayBlockingQueue<HttpLog> httpLogs = new ArrayBlockingQueue<HttpLog>(10);
		ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(10);
		buffer.put(testLog);
		HttpLogParser httpParser = new HttpLogParser(buffer, httpLogs);	
		httpParser.start();
		HttpLog hl = httpLogs.poll(1000, TimeUnit.MILLISECONDS);
		assertNotNull(hl);
		assertEquals("127.0.0.1", hl.getClient());
		assertEquals("GET /report HTTP/1.0/", hl.getClientRequest());
		httpParser.stopParsing();
	}


}
