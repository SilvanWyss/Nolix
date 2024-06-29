//package declaration
package ch.nolix.coreapi.attributeapi.mutablemultiattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiTokenHolder;

//interface
/**
 * A {@link IMutableMultiTokenHolder} is a {@link IMultiTokenHolder} whose
 * tokens can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-07-16
 */
public interface IMutableMultiTokenHolder extends IMultiTokenHolder {

  //method declaration
  /**
   * Adds the given token to the current {@link IMutableMultiTokenHolder}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null or blank.
   */
  void addToken(String token);

  //method declaration
  /**
   * Removes the token that equals the given token from the current
   * {@link IMutableMultiTokenHolder} if the current
   * {@link IMutableMultiTokenHolder} contains such a token.
   * 
   * @param token
   */
  void removeToken(String token);

  //method declaration
  /**
   * Removes all tokens from the current {@link IMutableMultiTokenHolder}.
   */
  void removeTokens();
}
