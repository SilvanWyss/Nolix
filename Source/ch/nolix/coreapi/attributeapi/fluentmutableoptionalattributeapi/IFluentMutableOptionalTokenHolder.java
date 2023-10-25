//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalTokenHolder;

//interface
/**
 * A {@link IFluentMutableOptionalTokenHolder} is a {@link IOptionalTokenHolder}
 * whose token can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <FMOTH> is the type of a {@link IFluentMutableOptionalTokenHolder}.
 */
public interface IFluentMutableOptionalTokenHolder<FMOTH extends IFluentMutableOptionalTokenHolder<FMOTH>>
    extends IOptionalTokenHolder {

  //method declaration
  /**
   * Removes the token of the current {@link IFluentMutableOptionalTokenHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalTokenHolder}.
   */
  FMOTH removeToken();

  //method declaration
  /**
   * Sets the token of the current {@link IFluentMutableOptionalTokenHolder}.
   * 
   * @param token
   * @return the current {@link IFluentMutableOptionalTokenHolder}.
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  FMOTH setToken(String token);
}
