Http Log Monitor
1.	LogMonitor.java
It’s the entry point read the configuration file to initialize
2.	FileObserver.java
Its an thread which looks for change in File, and read the new lines
3.	LogParser.java
Its also a thread which parse and convert the log file contents to java object
4.	LogAnalysier.java
This also run as thread which check for the logs and aggregate for 10 sec stats and check for high traffic alert. It publishes the messages to an message queue. From where the main program read and print

All these threads publish and consume data to BlockingQueue.

To Test
GenerateMockData.java which will produce data, which will raise an alert. And clear subsequently.


Tests
=====

JUnit test for each important class is in test.com.core

test.LogMonitorCoreTestSuit is the test suit to run all the unit test cases.

 