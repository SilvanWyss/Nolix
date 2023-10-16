//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalNamed;

//interface
/**
 * A {@link FluentOptionalNameable} is a {@link OptionalNamed} whose name can be
 * set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <FON> is the type of a {@link FluentOptionalNameable}.
 */
public interface FluentOptionalNameable<FON extends FluentOptionalNameable<FON>> extends OptionalNamed {

  //method declaration
  /**
   * Removes the name of the current {@link FluentOptionalNameable}.
   * 
   * @return the current {@link FluentOptionalNameable}.
   */
  FON removeName();

  //method declaration
  /**
   * Sets the name of the current {@link FluentOptionalNameable}.
   * 
   * @param name
   * @return the current {@link FluentOptionalNameable}.
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  FON setName(String name);
}
