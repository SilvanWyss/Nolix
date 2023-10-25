//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalTokened;

//interface
/**
 * A {@link IFluentOptionalTokenHolder} is a {@link OptionalTokened} whose token
 * can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <FOTH> is the type of a {@link IFluentOptionalTokenHolder}.
 */
public interface IFluentOptionalTokenHolder<FOTH extends IFluentOptionalTokenHolder<FOTH>> extends OptionalTokened {

  //method declaration
  /**
   * Removes the token of the current {@link IFluentOptionalTokenHolder}.
   * 
   * @return the current {@link IFluentOptionalTokenHolder}.
   */
  FOTH removeToken();

  //method declaration
  /**
   * Sets the token of the current {@link IFluentOptionalTokenHolder}.
   * 
   * @param token
   * @return the current {@link IFluentOptionalTokenHolder}.
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  FOTH setToken(String token);
}
