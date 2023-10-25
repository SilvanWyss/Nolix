//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalTokenHolder} can have a token.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 */
public interface IOptionalTokenHolder {

  //method declaration
  /**
   * @return the token of the current {@link IOptionalTokenHolder}.
   */
  String getToken();

  //method declaration
  /**
   * @return true if the current {@link IOptionalTokenHolder} has a token.
   */
  boolean hasToken();

  //method declaration
  /**
   * @param token
   * @return true if the current {@link IOptionalTokenHolder} has the given token.
   */
  boolean hasToken(String token);
}
