//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalNameHolder;

//interface
/**
 * A {@link IFluentMutableOptionalNameHolder} is a {@link IOptionalNameHolder}
 * whose name can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <FMONH> is the type of a {@link IFluentMutableOptionalNameHolder}.
 */
public interface IFluentMutableOptionalNameHolder<FMONH extends IFluentMutableOptionalNameHolder<FMONH>>
extends IOptionalNameHolder {

  //method declaration
  /**
   * Removes the name of the current {@link IFluentMutableOptionalNameHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalNameHolder}.
   */
  FMONH removeName();

  //method declaration
  /**
   * Sets the name of the current {@link IFluentMutableOptionalNameHolder}.
   * 
   * @param name
   * @return the current {@link IFluentMutableOptionalNameHolder}.
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  FMONH setName(String name);
}
