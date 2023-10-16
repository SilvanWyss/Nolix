//package declaration
package ch.nolix.coretest;

//class
/**
 * @author Silvan Wyss
 * @date 2016-02-01
 */
public final class Launcher {

  //main method
  /**
   * Creates a new {@link CoreTestPool} and runs it.
   * 
   * @param args
   */
  public static void main(String[] args) {
    new CoreTestPool().run();
  }

  //constructor
  /**
   * Prevents that an instance of the {@link Launcher} can be created.
   */
  private Launcher() {
  }
}
