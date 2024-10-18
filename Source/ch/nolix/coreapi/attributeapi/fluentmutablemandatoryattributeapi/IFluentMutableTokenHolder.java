package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITokenHolder;

/**
 * A {@link IFluentMutableTokenHolder} is a {@link ITokenHolder} whose token can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2023-02-06
 * @param <FMTH> is the type of a {@link IFluentMutableTokenHolder}.
 */
public interface IFluentMutableTokenHolder<FMTH extends IFluentMutableTokenHolder<FMTH>> extends ITokenHolder {

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
