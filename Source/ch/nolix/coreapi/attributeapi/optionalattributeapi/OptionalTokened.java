//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link OptionalTokened} can have a token.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 */
public interface OptionalTokened {

  // method declaration
  /**
   * @return the token of the current {@link OptionalTokened}.
   */
  String getToken();

  // method declaration
  /**
   * @return true if the current {@link OptionalTokened} has a token.
   */
  boolean hasToken();

  // method declaration
  /**
   * @param token
   * @return true if the current {@link OptionalTokened} has the given token.
   */
  boolean hasToken(String token);
}
