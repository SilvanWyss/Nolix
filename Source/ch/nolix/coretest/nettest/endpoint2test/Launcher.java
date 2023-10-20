//package declaration
package ch.nolix.coretest.nettest.endpoint2test;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-02-01
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
   * Creates a new {@link EndPointTestPool} and runs it.
   * 
   * @param args
   */
  public static void main(String[] args) {
    new EndPointTestPool().run();
  }
}
