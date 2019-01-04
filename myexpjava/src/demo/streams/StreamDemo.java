package demo.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamDemo {

	public static void main (String args[])
	{
		String[] arr = {"python", "perl", "java", "java script", "c", "c++", "html", "sql"};
		List<String> strList =  Arrays.asList(arr);
		// steam creation
		Stream<String> stream  = strList.stream();
		// sort the elements
		stream.sorted().forEach(System.out::println);
		// find first element starts with j
		Stream<String> filteredStream = strList.stream().filter(string-> string.startsWith("j"));
		System.out.println(filteredStream.findFirst().get());
		// in first 4 element how many elements start with p
		System.out.println(strList.stream().limit(4).filter(str->str.startsWith("p")).count());		
		// map
		// prepend hello begin of each string
		strList.stream().map(str->"Hello "+str).forEach(System.out::println);		
		// close
		filteredStream.close();
		stream.close();
		// parallel Stream, count the number of letters
		strList.parallelStream().map(str->str + "="+ str.length()).forEach(System.out::println);		
	}
}