//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.reflect.Executable;

//class
/**
 * The {@link ExecutableTool} provides methods to handle {@link Executable}s.
 * 
 * @author Silvan Wyss
 * @version 2023-01-22
 */
public final class ExecutableTool {

  //method
  /**
   * @param executable
   * @param type
   * @return true if all parameters of the given executable are of the given type,
   *         false otherwise.
   */
  public boolean allParametersOfMethodAreOfType(final Executable executable, final Class<?> type) {

    //Iterates the parameters of the given executable.
    for (final var p : executable.getParameters()) {
      if (!p.getType().isAssignableFrom(type)) {
        return false;
      }
    }

    return true;
  }
}