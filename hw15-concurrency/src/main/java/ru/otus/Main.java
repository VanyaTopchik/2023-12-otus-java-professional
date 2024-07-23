package ru.otus;

import static java.lang.Thread.sleep;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);
  private static final CyclicBarrier BARRIER = new CyclicBarrier(2, new Task());
  private static final ExecutorService executorService = Executors.newFixedThreadPool(2);
  private static int currentCount = 1;

  public static void main(String[] args) {
    executorService.submit(new CounterThread("Поток 1"));
    executorService.submit(new CounterThread("Поток 2"));
  }

  public static class Task implements Runnable {
    private Order order = Order.INCREMENT;

    @Override
    public void run() {
      if (currentCount == 1) {
        order = Order.INCREMENT;
      }
      if (currentCount == 10) {
        order = Order.DECREMENT;
      }
      switch (order) {
        case INCREMENT -> currentCount++;
        case DECREMENT -> currentCount--;
      }
    }

    enum Order {
      INCREMENT,
      DECREMENT
    }
  }

  public static class CounterThread implements Runnable {

    private final String name;

    public CounterThread(String name) {
      this.name = name;
    }

    @Override
    public void run() {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          if (name.equals("Поток 2")) {
            while (BARRIER.getNumberWaiting() == 0) {
              //Ждем Поток 1
            }
          }
          logger.info("{}:{}", name, currentCount);
          sleep(500);
          BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
          logger.error(e.getMessage());
          Thread.currentThread().interrupt();
        }
      }
    }
  }

}