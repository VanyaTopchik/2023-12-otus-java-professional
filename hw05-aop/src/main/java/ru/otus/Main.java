package ru.otus;

public class Main {
  public static void main(String[] args) throws Exception {
    TestLoggingInterface testLogging = (TestLoggingInterface) AOPLogging.createProxyLogging(TestLogging.class);
    testLogging.calculation(6);
    testLogging.calculation(2,3,"Test");
    AnotherTestLogging anotherTestLogging = (AnotherTestLogging) AOPLogging.createProxyLogging(AnotherTestLoggingInterface.class);
    anotherTestLogging.doSomething();
  }
}