//package declaration
package ch.nolix.systemtest.graphictest.colortest;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-11-14
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
   * Creates a new {@link ColorTestPool} and runs it.
   * 
   * @param args
   */
  public static void main(String[] args) {
    new ColorTestPool().run();
  }
}
