/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ITokenHolder;

/**
 * A {@link IMutableTokenHolder} is a {@link ITokenHolder} whose token can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableTokenHolder extends ITokenHolder {
  /**
   * Sets the token of the current {@link IMutableTokenHolder}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null or blank
   */
  void setToken(String token);
}
