/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.MultiTokenHolder;

/**
 * A {@link FluentMutableMultiTokenHolder} is a {@link MultiTokenHolder} whose
 * tokens can be added programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableMultiTokenHolder}
 */
public interface FluentMutableMultiTokenHolder<H extends FluentMutableMultiTokenHolder<H>> extends MultiTokenHolder {
  /**
   * Adds the given token to the current {@link FluentMutableMultiTokenHolder} if
   * the current {@link FluentMutableMultiTokenHolder} does not contain already
   * the given token.
   * 
   * @param token
   * @return the current {@link FluentMutableMultiTokenHolder}
   * @throws RuntimeException if the given token is null or blank
   */
  H addToken(String token);

  /**
   * Removes the given token from the current
   * {@link FluentMutableMultiTokenHolder} if the current
   * {@link FluentMutableMultiTokenHolder} contains the given token.
   * 
   * @param token
   */
  void removeToken(String token);

  /**
   * Removes all tokens from the current {@link FluentMutableMultiTokenHolder}.
   */
  void removeTokens();
}
