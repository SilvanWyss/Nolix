//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalNamed;

//interface
/**
 * A {@link IFluentOptionalNameHolder} is a {@link OptionalNamed} whose name can
 * be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <FONH> is the type of a {@link IFluentOptionalNameHolder}.
 */
public interface IFluentOptionalNameHolder<FONH extends IFluentOptionalNameHolder<FONH>> extends OptionalNamed {

  //method declaration
  /**
   * Removes the name of the current {@link IFluentOptionalNameHolder}.
   * 
   * @return the current {@link IFluentOptionalNameHolder}.
   */
  FONH removeName();

  //method declaration
  /**
   * Sets the name of the current {@link IFluentOptionalNameHolder}.
   * 
   * @param name
   * @return the current {@link IFluentOptionalNameHolder}.
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  FONH setName(String name);
}
