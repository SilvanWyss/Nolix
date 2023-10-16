//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.multiattributeapi.MultiTokened;

//interface
/**
 * A {@link FluentMultiTokenable} is a {@link MultiTokened} whose tokens can be
 * added and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-06-16
 * @param <MT> is the type of a {@link FluentMultiTokenable}.
 */
public interface FluentMultiTokenable<MT extends FluentMultiTokenable<MT>> extends MultiTokened {

  //method declaration
  /**
   * Adds the given token to the current {@link FluentMultiTokenable}.
   * 
   * @param token
   * @return the current {@link FluentMultiTokenable}.
   * @throws RuntimeException if the given token is null or blank.
   */
  MT addToken(String token);

  //method declaration
  /**
   * Removes the token that equals the given token from the current
   * {@link FluentMultiTokenable} if the current {@link FluentMultiTokenable}
   * contains such a token.
   * 
   * @param token
   * @return the current {@link FluentMultiTokenable}.
   */
  MT removeToken(String token);

  //method declaration
  /**
   * Removes all tokens from the current {@link FluentMultiTokenable}.
   * 
   * @return the current {@link FluentMultiTokenable}.
   */
  MT removeTokens();
}
