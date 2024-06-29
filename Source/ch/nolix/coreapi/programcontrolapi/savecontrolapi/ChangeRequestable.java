//package declaration
package ch.nolix.coreapi.programcontrolapi.savecontrolapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link ChangeRequestable} can be asked if it has uncommitted changes.
 * 
 * @author Silvan Wyss
 * @version 2021-02-26
 */
@AllowDefaultMethodsAsDesignPattern
public interface ChangeRequestable {

  //method declaration
  /**
   * @return true if the current {@link ChangeRequestable} has uncomitted changes.
   */
  boolean hasChanges();

  //method
  /**
   * @return true if the current {@link ChangeRequestable} does not have
   *         uncommitted changes.
   */
  default boolean isChangeFree() {
    return !hasChanges();
  }
}
