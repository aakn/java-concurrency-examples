package fk.examples.concurrency.subscription;

public interface Buffer {

  void blockingPut(int value) throws InterruptedException;

  int blockingGet() throws InterruptedException;

}
