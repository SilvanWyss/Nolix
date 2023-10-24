//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Tokened;

//interface
/**
 * A {@link IFluentMutableTokenHolder} is a {@link Tokened} whose token can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-06
 * @param <FMTH> is the type of a {@link IFluentMutableTokenHolder}.
 */
public interface IFluentMutableTokenHolder<FMTH extends IFluentMutableTokenHolder<FMTH>> extends Tokened {

  //method declaration
  /**
   * Sets the token of the current {@link IFluentMutableTokenHolder}.
   * 
   * @param token
   * @return the current {@link IFluentMutableTokenHolder}.
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  FMTH setToken(String token);
}
