package fk.examples.concurrency.threadlocals;

public class Context {

  private static ThreadLocal<String> id = ThreadLocal.withInitial(() -> "empty");

  public static String getId() {
    return id.get();
  }

  public static void setId(String value) {
    id.set(value);
  }

  public static void clear() {
    id.remove();
  }
}
