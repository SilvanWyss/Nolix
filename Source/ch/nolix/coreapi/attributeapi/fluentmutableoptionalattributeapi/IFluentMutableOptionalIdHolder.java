//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalIdHolder;

//interface
/**
 * A {@link IFluentMutableOptionalIdHolder} is a {@link IOptionalIdHolder} whose
 * id can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2020-02-01
 * @param <FMOIH> is the type of a {@link IFluentMutableOptionalIdHolder}.
 */
public interface IFluentMutableOptionalIdHolder<FMOIH extends IFluentMutableOptionalIdHolder<FMOIH>>
extends IOptionalIdHolder {

  //method declaration
  /**
   * Removes the id of the current {@link IFluentMutableOptionalIdHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalIdHolder}.
   */
  FMOIH removeId();

  //method declaration
  /**
   * Sets the id of the current {@link IFluentMutableOptionalIdHolder}.
   * 
   * @param id
   * @return the current {@link IFluentMutableOptionalIdHolder}.
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  FMOIH setId(String id);
}
