//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITokenHolder;

//interface
/**
 * A {@link IMutableTokenHolder} is a {@link ITokenHolder} whose token can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-09
 */
public interface IMutableTokenHolder extends ITokenHolder {

  //method declaration
  /**
   * Sets the token of the current {@link IMutableTokenHolder}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  void setToken(String token);
}
