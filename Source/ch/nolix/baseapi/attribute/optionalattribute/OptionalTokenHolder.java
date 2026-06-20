/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link OptionalTokenHolder} can have a token.
 * 
 * @author Silvan Wyss
 */
public interface OptionalTokenHolder {
  /**
   * @return the token of the current {@link OptionalTokenHolder}
   * @throws RuntimeException if the current {@link OptionalTokenHolder} does not
   *                          have a token
   */
  String getToken();

  /**
   * @return true if the current {@link OptionalTokenHolder} has a token, false
   *         otherwise
   */
  boolean hasToken();

  /**
   * @param token
   * @return true if the current {@link OptionalTokenHolder} has the given token,
   *         false otherwise
   */
  default boolean hasToken(final String token) {
    return hasToken() && getToken().equals(token);
  }
}
