package fk.examples.concurrency.subscription;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleBuffer implements Buffer {

  int value = -1;

  @Override
  public void blockingPut(int value) throws InterruptedException {
    this.value = value;
  }

  @Override
  public int blockingGet() throws InterruptedException {
    return value;
  }
}
