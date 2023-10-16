//package declaration
package ch.nolix.coretest.commontypetest.commontypehelpertest;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-08-18
 */
public final class Launcher {

  //main method
  /**
   * Creates a new {@link CommonTypeHelperTestPool} and runs it.
   * 
   * @param args
   */
  public static void main(String[] args) {
    new CommonTypeHelperTestPool().run();
  }

  //constructor
  /**
   * Prevents that an instance of the {@link Launcher} can be created.
   */
  private Launcher() {
  }
}
