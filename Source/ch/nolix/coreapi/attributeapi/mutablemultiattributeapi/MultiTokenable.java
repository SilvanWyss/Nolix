//package declaration
package ch.nolix.coreapi.attributeapi.mutablemultiattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiTokenHolder;

//interface
/**
 * A {@link MultiTokenable} is a {@link IMultiTokenHolder} whose tokens can be added
 * and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-07-16
 */
public interface MultiTokenable extends IMultiTokenHolder {

  //method declaration
  /**
   * Adds the given token to the current {@link MultiTokenable}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null or blank.
   */
  void addToken(String token);

  //method declaration
  /**
   * Removes the token that equals the given token from the current
   * {@link MultiTokenable} if the current {@link MultiTokenable} contains such a
   * token.
   * 
   * @param token
   */
  void removeToken(String token);

  //method declaration
  /**
   * Removes all tokens from the current {@link MultiTokenable}.
   */
  void removeTokens();
}
