//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalTokened;

//interface
/**
 * A {@link FluentOptionalTokenable} is a {@link OptionalTokened} whose token
 * can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <FOT> is the type of a {@link FluentOptionalTokenable}.
 */
public interface FluentOptionalTokenable<FOT extends FluentOptionalTokenable<FOT>> extends OptionalTokened {

  //method declaration
  /**
   * Removes the token of the current {@link FluentOptionalTokenable}.
   * 
   * @return the current {@link FluentOptionalTokenable}.
   */
  FOT removeToken();

  //method declaration
  /**
   * Sets the token of the current {@link FluentOptionalTokenable}.
   * 
   * @param token
   * @return the current {@link FluentOptionalTokenable}.
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  FOT setToken(String token);
}
