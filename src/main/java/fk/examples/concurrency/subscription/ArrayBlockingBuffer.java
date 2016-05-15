package fk.examples.concurrency.subscription;

import java.util.concurrent.ArrayBlockingQueue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArrayBlockingBuffer implements Buffer {

  private final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

  @Override
  public void blockingPut(int value) throws InterruptedException {
    queue.put(value);
  }

  @Override
  public int blockingGet() throws InterruptedException {
    return queue.take();
  }
}
