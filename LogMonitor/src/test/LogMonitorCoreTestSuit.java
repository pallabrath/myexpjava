package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.com.core.CircularArrayTest;
import test.com.core.FileObserverTest;
import test.com.core.HttpLogParserTest;
import test.com.core.HttpLogTest;
import test.com.core.LogAnalysierTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	CircularArrayTest.class,
	FileObserverTest.class,
	HttpLogParserTest.class,
	HttpLogTest.class,
	LogAnalysierTest.class
})

public class LogMonitorCoreTestSuit {

}
