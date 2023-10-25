//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiTokenHolder;

//interface
/**
 * A {@link IFluentMutableMultiTokenHolder} is a {@link IMultiTokenHolder} whose
 * tokens can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-06-16
 * @param <FMMTH> is the type of a {@link IFluentMutableMultiTokenHolder}.
 */
public interface IFluentMutableMultiTokenHolder<FMMTH extends IFluentMutableMultiTokenHolder<FMMTH>>
    extends IMultiTokenHolder {

  //method declaration
  /**
   * Adds the given token to the current {@link IFluentMutableMultiTokenHolder}.
   * 
   * @param token
   * @return the current {@link IFluentMutableMultiTokenHolder}.
   * @throws RuntimeException if the given token is null or blank.
   */
  FMMTH addToken(String token);

  //method declaration
  /**
   * Removes the token that equals the given token from the current
   * {@link IFluentMutableMultiTokenHolder} if the current
   * {@link IFluentMutableMultiTokenHolder} contains such a token.
   * 
   * @param token
   * @return the current {@link IFluentMutableMultiTokenHolder}.
   */
  FMMTH removeToken(String token);

  //method declaration
  /**
   * Removes all tokens from the current {@link IFluentMutableMultiTokenHolder}.
   * 
   * @return the current {@link IFluentMutableMultiTokenHolder}.
   */
  FMMTH removeTokens();
}
