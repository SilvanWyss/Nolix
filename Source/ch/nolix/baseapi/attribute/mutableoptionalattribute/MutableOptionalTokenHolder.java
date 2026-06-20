/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalTokenHolder;

/**
 * A {@link MutableOptionalTokenHolder} is a {@link OptionalTokenHolder} whose
 * token can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalTokenHolder extends OptionalTokenHolder {
  /**
   * Removes the token of the current {@link MutableOptionalTokenHolder}.
   */
  void removeToken();

  /**
   * Sets the token of the current {@link MutableOptionalTokenHolder}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null or blank
   */
  void setToken(String token);
}
