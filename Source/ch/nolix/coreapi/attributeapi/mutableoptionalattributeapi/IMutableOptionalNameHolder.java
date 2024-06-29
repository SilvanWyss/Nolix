//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalNameHolder;

//interface
/**
 * A {@link IMutableOptionalNameHolder} is a {@link IOptionalNameHolder} whose
 * name can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-07
 */
public interface IMutableOptionalNameHolder extends IOptionalNameHolder {

  //method declaration
  /**
   * Removes the name of the current {@link IMutableOptionalNameHolder}.
   */
  void removeName();

  //method declaration
  /**
   * Sets the name of the current {@link IMutableOptionalNameHolder}.
   * 
   * @param name
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  void setName(String name);
}
