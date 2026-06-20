/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

/**
 * A {@link TokenHolder} has a token.
 * 
 * @author Silvan Wyss
 */
public interface TokenHolder {
  /**
   * @return the token of the current {@link TokenHolder}
   */
  String getToken();

  /**
   * @param token
   * @return true if the current {@link TokenHolder} has the given token, false
   *         otherwise
   */
  default boolean hasToken(final String token) {
    return getToken().equals(token);
  }
}
