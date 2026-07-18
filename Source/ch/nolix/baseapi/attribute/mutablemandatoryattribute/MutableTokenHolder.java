/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.TokenHolder;

/**
 * A {@link MutableTokenHolder} is a {@link TokenHolder} whose token can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableTokenHolder extends TokenHolder {
  /**
   * Sets the token of the current {@link MutableTokenHolder}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null or blank
   */
  void setToken(String token);
}
