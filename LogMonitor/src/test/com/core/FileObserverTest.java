package test.com.core;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.core.FileObserver;

public class FileObserverTest {

	@Test
	public void testFileObserver() throws IOException, InterruptedException {
		String fileName = "D:\\\\test\\\\testLog1.txt";
		String[] arr = {"Hello","World","Pallab","Rath","Data"};
		FileWriter fileWriter = new FileWriter(fileName);
		fileWriter.append(arr[0]);
		fileWriter.append(System.lineSeparator());
		fileWriter.close();
		ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(1000);
		long sleepInterval = 50;
		FileObserver lfo = new FileObserver(fileName, buffer, sleepInterval);
		lfo.startObserving();
		// write some text in a file with some delay
		
		for (int i = 1; i < arr.length; i++ ) {						
			Files.write(Paths.get(fileName), (arr[i] + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
			Thread.sleep(sleepInterval);
		}
		Set<String> arrList = new HashSet<String>(Arrays.asList(arr));
	    // Lets check for buffer whether FileObserver read everything or not
		// after 10 sec we will say time out
		
		for (int i = 0; i < 10; i++)
		{
			String str = buffer.poll(1000, TimeUnit.MILLISECONDS);
			if (str != null)
			{				
				arrList.remove(str);
			}
		}
		lfo.stopObserving();
		assertTrue(arrList.isEmpty());		
	}

}
