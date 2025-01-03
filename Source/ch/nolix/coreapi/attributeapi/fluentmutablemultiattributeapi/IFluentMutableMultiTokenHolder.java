package ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi;

import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiTokenHolder;

/**
 * A {@link IFluentMutableMultiTokenHolder} is a {@link IMultiTokenHolder} whose
 * tokens can be added and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2023-06-16
 * @param <H> is the type of a {@link IFluentMutableMultiTokenHolder}.
 */
public interface IFluentMutableMultiTokenHolder<H extends IFluentMutableMultiTokenHolder<H>> extends IMultiTokenHolder {

  /**
   * Adds the given token to the current {@link IFluentMutableMultiTokenHolder}.
   * 
   * @param token
   * @return the current {@link IFluentMutableMultiTokenHolder}.
   * @throws RuntimeException if the given token is null or blank.
   */
  H addToken(String token);

  /**
   * Removes the token that equals the given token from the current
   * {@link IFluentMutableMultiTokenHolder} if the current
   * {@link IFluentMutableMultiTokenHolder} contains such a token.
   * 
   * @param token
   * @return the current {@link IFluentMutableMultiTokenHolder}.
   */
  H removeToken(String token);

  /**
   * Removes all tokens from the current {@link IFluentMutableMultiTokenHolder}.
   * 
   * @return the current {@link IFluentMutableMultiTokenHolder}.
   */
  H removeTokens();
}
