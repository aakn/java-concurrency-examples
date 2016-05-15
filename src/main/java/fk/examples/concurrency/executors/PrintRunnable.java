package fk.examples.concurrency.executors;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintRunnable implements Runnable {

  private final Random random;
  private final String taskName;

  public PrintRunnable(String taskName) {
    this.taskName = taskName;
    random = new Random();
  }

  @Override
  public void run() {

    log.info("Starting {}", taskName);
    try {
      int millis = random.nextInt(3000);
      log.info("going to sleep for {} for task", millis, taskName);
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      log.error("an exception", e);
    }
    log.info("done with {}", taskName);
  }
}
