package fk.examples.concurrency.threadlocals;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;

@Slf4j
public class ContextTest {

  ExecutorService executorService;

  @Before
  public void setUp() throws Exception {
    executorService = Executors.newCachedThreadPool();
  }

  @Test
  public void shouldSetContext() throws Exception {

    assertEquals("empty", Context.getId());
    Context.setId("foobar");
    assertEquals("foobar", Context.getId());

    Context.clear();
  }

  @Test
  public void shouldHaveDifferentContextsInDifferentThreads() throws Exception {

    executorService.execute(getRunnable("first"));
    executorService.execute(getRunnable("second"));

    Thread.sleep(2000);

    executorService.execute(getRunnable("third"));
    executorService.execute(getRunnable("fourth"));

    executorService.shutdown();
    executorService.awaitTermination(1L, TimeUnit.MINUTES);

  }

  private Runnable getRunnable(String id) {
    return () -> {
      try {
        Thread.sleep(500);
        log.info("Previous context {}", Context.getId());
        Context.setId(id);
        log.info("setting the context");
        Thread.sleep(500);
        log.info("new context {}", Context.getId());
      } catch (InterruptedException e) {
        log.error("interrupt", e);
      }
    };
  }
}