//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.reflect.Executable;

//class
/**
 * The {@link GlobalExecutableHelper} provides methods to handle
 * {@link Executable}s. Of the {@link GlobalExecutableHelper} an instance cannot
 * be created.
 * 
 * @author Silvan Wyss
 * @date 2023-01-22
 */
public final class GlobalExecutableHelper {

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalExecutableHelper} can be
   * created.
   */
  private GlobalExecutableHelper() {
  }

  //static method
  /**
   * @param executable
   * @param type
   * @return true if all parameters of the given executable are of the given type,
   *         false otherwise.
   */
  public static boolean allParametersOfMethodAreOfType(final Executable executable, final Class<?> type) {

    //Iterates the parameters of the given executable.
    for (final var p : executable.getParameters()) {
      if (!p.getType().isAssignableFrom(type)) {
        return false;
      }
    }

    return true;
  }
}
