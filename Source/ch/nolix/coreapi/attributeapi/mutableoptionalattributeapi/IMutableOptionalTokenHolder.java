//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalTokenHolder;

//interface
/**
 * A {@link IMutableOptionalTokenHolder} is a {@link IOptionalTokenHolder} whose
 * token can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface IMutableOptionalTokenHolder extends IOptionalTokenHolder {

  //method declaration
  /**
   * Removes the token of the current {@link IMutableOptionalTokenHolder}.
   */
  void removeToken();

  //method declaration
  /**
   * Sets the token of the current {@link IMutableOptionalTokenHolder}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  void setToken(String token);
}
