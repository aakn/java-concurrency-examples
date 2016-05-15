package fk.examples.concurrency.arrays;

import java.util.Arrays;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SharedArray {

  private final Random random = new Random();

  private final int[] array;
  private int index = 0;

  public SharedArray(int size) {
    this.array = new int[size];
  }

  public void add(int value) {

    int writeTo = index;

    try {
      Thread.sleep(random.nextInt(100));
    } catch (InterruptedException e) {
      log.error("an error", e);
    }

    array[writeTo] = value;
    log.info("writing {} to position {}", value, writeTo);

    ++index;
    log.info("next write position is {}", index);
  }

  @Override
  public String toString() {
    return "SharedArray{" +
           "array=" + Arrays.toString(array) +
           '}';
  }
}
