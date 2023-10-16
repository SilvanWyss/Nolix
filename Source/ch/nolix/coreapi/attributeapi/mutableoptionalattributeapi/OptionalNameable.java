//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalNamed;

//interface
/**
 * A {@link OptionalNameable} is a {@link OptionalNamed} whose name can be set
 * and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
public interface OptionalNameable extends OptionalNamed {

  //method declaration
  /**
   * Removes the name of the current {@link OptionalNameable}.
   */
  void removeName();

  //method declaration
  /**
   * Sets the name of the current {@link OptionalNameable}.
   * 
   * @param name
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  void setName(String name);
}
