package fk.examples.concurrency.arrays;

public class SynchronizedSharedArray extends SharedArray {

  public SynchronizedSharedArray(int size) {
    super(size);
  }

  @Override
  public synchronized void add(int value) {
    super.add(value);
  }
}
