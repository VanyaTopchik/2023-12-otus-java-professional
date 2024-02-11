package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestedClass {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestedClass.class);

  @Before
  public void beforeAll() {
    LOGGER.info("Call method beforeAll");
  }

  @After
  public void afterAll() {
    LOGGER.info("Call method afterAll");
  }

  @Test
  public void testWithException() {
    LOGGER.info("Call method testWithException");
    throw new RuntimeException("Some exception");
  }

  @Test
  public void test1() {
    LOGGER.info("Call method test1");
  }

  @Test
  public void test2() {
    LOGGER.info("Call method test2");
  }
}
