/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalTokenHolder;

/**
 * A {@link FluentMutableOptionalTokenHolder} is a {@link OptionalTokenHolder}
 * whose token can be set programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalTokenHolder}
 */
public interface FluentMutableOptionalTokenHolder<H extends FluentMutableOptionalTokenHolder<H>>
extends OptionalTokenHolder {
  /**
   * Removes the token of the current {@link FluentMutableOptionalTokenHolder}.
   */
  void removeToken();

  /**
   * Sets the token of the current {@link FluentMutableOptionalTokenHolder}.
   * 
   * @param token
   * @return the current {@link FluentMutableOptionalTokenHolder}
   * @throws RuntimeException if the given token is null or blank
   */
  H setToken(String token);
}
