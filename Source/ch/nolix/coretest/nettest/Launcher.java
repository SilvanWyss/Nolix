//package declaration
package ch.nolix.coretest.nettest;

//class
/**
 * @author Silvan Wyss
 * @date 2022-06-16
 */
public final class Launcher {

  //main method
  /**
   * Creates a new {@link NetTestPool} and runs it.
   * 
   * @param arguments
   */
  public static void main(final String[] arguments) {
    new NetTestPool().run();
  }

  //constructor
  /**
   * Prevents that an instance of the {@link Launcher} can be created.
   */
  private Launcher() {
  }
}
