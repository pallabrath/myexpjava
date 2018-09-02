package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateMockData {

	public static String logFormat = "127.0.0.1 - james [%s] \"GET %s HTTP/1.0\" 200 1234\n";
	private static String[] apis = { "/rama", "/hari", "/krishna", "/ganesha", "/laxmi" };
	private static SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyy:HH:mm:ss ZZZZ");

	public static void main(String args[]) throws IOException, InterruptedException {
		String fileName = "D:\\\\test\\\\testLog.txt";
		long ts = 0;
		long sleepInterval = 50;
		// generate logs for 4 minutes
		// run for 4 minutes
		while (ts < 240000) {
			Date nowDate = new Date();
			int i = (int) ((Math.random() * 100) % apis.length);
			String log = String.format(logFormat, sd.format(nowDate), apis[i]);
			try {
				Files.write(Paths.get(fileName), log.getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				// exception handling left as an exercise for the reader
			}
			ts += 5;
			Thread.sleep(sleepInterval);
		}
	}

}
