//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalTokened;

//interface
/**
 * A {@link OptionalTokenable} is a {@link OptionalTokened} whose token can be
 * set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface OptionalTokenable extends OptionalTokened {

  // method declaration
  /**
   * Removes the token of the current {@link OptionalTokenable}.
   */
  void removeToken();

  // method declaration
  /**
   * Sets the token of the current {@link OptionalTokenable}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  void setToken(String token);
}
