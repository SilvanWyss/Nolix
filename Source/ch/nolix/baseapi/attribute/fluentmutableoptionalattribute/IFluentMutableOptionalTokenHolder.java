/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalTokenHolder;

/**
 * A {@link IFluentMutableOptionalTokenHolder} is a {@link IOptionalTokenHolder}
 * whose token can be set programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableOptionalTokenHolder}
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
   * @return the current {@link IFluentMutableOptionalTokenHolder}
   * @throws RuntimeException if the given token is null or blank
   */
  H setToken(String token);
}
