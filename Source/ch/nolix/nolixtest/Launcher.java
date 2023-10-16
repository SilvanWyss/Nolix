//package declaration
package ch.nolix.nolixtest;

//own imports
import ch.nolix.core.errorcontrol.logger.GlobalLogger;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-11-17
 */
public final class Launcher {

  //main method
  /**
   * Creates a new {@link NolixTestPool} and runs it.
   * 
   * @param args
   */
  public static void main(String[] args) {

    GlobalLogger.disable();

    new NolixTestPool().run();
  }

  //constructor
  /**
   * Prevents that an instance of the {@link Launcher} can be created.
   */
  private Launcher() {
  }
}
