package ch.nolix.coreapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalTokenHolder;

/**
 * A {@link IFluentMutableOptionalTokenHolder} is a {@link IOptionalTokenHolder}
 * whose token can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <H> is the type of a {@link IFluentMutableOptionalTokenHolder}.
 */
public interface IFluentMutableOptionalTokenHolder<H extends IFluentMutableOptionalTokenHolder<H>>
extends IOptionalTokenHolder {

  /**
   * Removes the token of the current {@link IFluentMutableOptionalTokenHolder}.
   */
  void removeToken();

  /**
   * Sets the token of the current {@link IFluentMutableOptionalTokenHolder}.
   * 
   * @param token
   * @return the current {@link IFluentMutableOptionalTokenHolder}.
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  H setToken(String token);
}
