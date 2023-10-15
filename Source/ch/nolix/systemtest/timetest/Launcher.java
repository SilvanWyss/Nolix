//package declaration
package ch.nolix.systemtest.timetest;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-14
 */
public final class Launcher {

  // main method
  /**
   * Creates a new {@link TimeTestPool} and runs it.
   * 
   * @param args
   */
  public static void main(String[] args) {
    new TimeTestPool().run();
  }

  // constructor
  /**
   * Prevents that an instance of the {@link Launcher} can be created.
   */
  private Launcher() {
  }
}
