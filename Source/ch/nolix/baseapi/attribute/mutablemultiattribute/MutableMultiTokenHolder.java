/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.MultiTokenHolder;

/**
 * A {@link MutableMultiTokenHolder} is a {@link MultiTokenHolder} whose
 * tokens can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableMultiTokenHolder extends MultiTokenHolder {
  /**
   * Adds the given token to the current {@link MutableMultiTokenHolder} if the
   * current {@link MutableMultiTokenHolder} does not contain already the given
   * token.
   * 
   * @param token
   * @throws RuntimeException if the given token is null or blank
   */
  void addToken(String token);

  /**
   * Removes the given token from the current {@link MutableMultiTokenHolder} if
   * the current {@link MutableMultiTokenHolder} contains the given token.
   * 
   * @param token
   */
  void removeToken(String token);

  /**
   * Removes all tokens from the current {@link MutableMultiTokenHolder}.
   */
  void removeTokens();
}
