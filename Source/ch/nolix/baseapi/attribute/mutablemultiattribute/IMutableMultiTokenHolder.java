/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.IMultiTokenHolder;

/**
 * A {@link IMutableMultiTokenHolder} is a {@link IMultiTokenHolder} whose
 * tokens can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableMultiTokenHolder extends IMultiTokenHolder {
  /**
   * Adds the given token to the current {@link IMutableMultiTokenHolder} if the
   * current {@link IMutableMultiTokenHolder} does not contain already the given
   * token.
   * 
   * @param token
   * @throws RuntimeException if the given token is null or blank
   */
  void addToken(String token);

  /**
   * Removes the given token from the current {@link IMutableMultiTokenHolder} if
   * the current {@link IMutableMultiTokenHolder} contains the given token.
   * 
   * @param token
   */
  void removeToken(String token);

  /**
   * Removes all tokens from the current {@link IMutableMultiTokenHolder}.
   */
  void removeTokens();
}
