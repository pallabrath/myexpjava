package test.com.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.core.HttpLog;

public class HttpLogTest {

	@Test
	public void testParseHttpLog() {
		String testLog = "127.0.0.1 - james [09/May/2018:16:00:39 +0000] \"GET /report HTTP/1.0/\" 200 1234";
		HttpLog hl = HttpLog.parseHttpLog(testLog);
		assertEquals("127.0.0.1", hl.getClient());
		assertEquals("GET /report HTTP/1.0/", hl.getClientRequest());
		assertNotNull(hl.getTs());
	}	
	
}
