/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.IMultiTokenHolder;

/**
 * A {@link IFluentMutableMultiTokenHolder} is a {@link IMultiTokenHolder} whose
 * tokens can be added programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableMultiTokenHolder}
 */
public interface IFluentMutableMultiTokenHolder<H extends IFluentMutableMultiTokenHolder<H>> extends IMultiTokenHolder {
  /**
   * Adds the given token to the current {@link IFluentMutableMultiTokenHolder} if
   * the current {@link IFluentMutableMultiTokenHolder} does not contain already
   * the given token.
   * 
   * @param token
   * @return the current {@link IFluentMutableMultiTokenHolder}
   * @throws RuntimeException if the given token is null or blank
   */
  H addToken(String token);

  /**
   * Removes the given token from the current
   * {@link IFluentMutableMultiTokenHolder} if the current
   * {@link IFluentMutableMultiTokenHolder} contains the given token.
   * 
   * @param token
   */
  void removeToken(String token);

  /**
   * Removes all tokens from the current {@link IFluentMutableMultiTokenHolder}.
   */
  void removeTokens();
}
