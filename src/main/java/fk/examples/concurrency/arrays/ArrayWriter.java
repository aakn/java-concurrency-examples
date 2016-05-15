package fk.examples.concurrency.arrays;

public class ArrayWriter implements Runnable {

  private final SharedArray array;
  private final int startPosition;

  public ArrayWriter(SharedArray array, int startPosition) {
    this.array = array;
    this.startPosition = startPosition;
  }

  @Override
  public void run() {
    for (int i = startPosition; i < startPosition + 3; i++) {
      array.add(i);
    }
  }
}
