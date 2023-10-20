//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-02-06
 */
public final class Launcher {

  //constructor
  /**
   * Prevents that an instance of the {@link Launcher} can be created.
   */
  private Launcher() {
  }

  //main method
  /**
   * Creates a new {@link NodeTestPool} and runs it.
   * 
   * @param arguments
   */
  public static void main(final String[] arguments) {
    new NodeTestPool().run();
  }
}
