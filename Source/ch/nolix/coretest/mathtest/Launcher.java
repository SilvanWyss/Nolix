//package declaration
package ch.nolix.coretest.mathtest;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public final class Launcher {

  //main method
  /**
   * Creates a new {@link MathTestPool} and executes it.
   * 
   * @param arguments
   */
  public static void main(final String[] arguments) {
    new MathTestPool().run();
  }

  //constructor
  /**
   * Prevents that an instance of the {@link Launcher} can be created.
   */
  private Launcher() {
  }
}
