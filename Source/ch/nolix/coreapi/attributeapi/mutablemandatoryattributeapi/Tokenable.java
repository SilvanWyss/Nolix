//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITokenHolder;

//interface
/**
 * A {@link Tokenable} is a {@link ITokenHolder} whose token can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface Tokenable extends ITokenHolder {

  //method declaration
  /**
   * Sets the token of the current {@link Tokenable}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  void setToken(String token);
}
