package ch.nolix.coreapi.attributeapi.mutablemultiattributeapi;

import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiTokenHolder;

/**
 * A {@link IMutableMultiTokenHolder} is a {@link IMultiTokenHolder} whose
 * tokens can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-07-16
 */
public interface IMutableMultiTokenHolder extends IMultiTokenHolder {

  /**
   * Adds the given token to the current {@link IMutableMultiTokenHolder}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null or blank.
   */
  void addToken(String token);

  /**
   * Removes the token that equals the given token from the current
   * {@link IMutableMultiTokenHolder} if the current
   * {@link IMutableMultiTokenHolder} contains such a token.
   * 
   * @param token
   */
  void removeToken(String token);

  /**
   * Removes all tokens from the current {@link IMutableMultiTokenHolder}.
   */
  void removeTokens();
}
