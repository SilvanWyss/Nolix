package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalTokenHolder;

/**
 * A {@link IFluentMutableOptionalTokenHolder} is a {@link IOptionalTokenHolder}
 * whose token can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <FMOTH> is the type of a {@link IFluentMutableOptionalTokenHolder}.
 */
public interface IFluentMutableOptionalTokenHolder<FMOTH extends IFluentMutableOptionalTokenHolder<FMOTH>>
extends IOptionalTokenHolder {

  /**
   * Removes the token of the current {@link IFluentMutableOptionalTokenHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalTokenHolder}.
   */
  FMOTH removeToken();

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
