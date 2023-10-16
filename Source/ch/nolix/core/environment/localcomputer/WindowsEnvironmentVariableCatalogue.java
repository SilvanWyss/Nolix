//package declaration
package ch.nolix.core.environment.localcomputer;

//class
/**
 * The {@link WindowsEnvironmentVariableCatalogue} provides environment
 * variables for Microsoft Windows.
 * 
 * Of the {@link WindowsEnvironmentVariableCatalogue} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 */
public final class WindowsEnvironmentVariableCatalogue {

  //constant
  public static final String APP_DATA = "APPDATA";

  //constructor
  /**
   * Prevents that an instance of the {@link WindowsEnvironmentVariableCatalogue}
   * can be created.
   */
  private WindowsEnvironmentVariableCatalogue() {
  }
}
