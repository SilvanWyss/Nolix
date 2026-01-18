/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ITokenHolder;

/**
 * A {@link IFluentMutableTokenHolder} is a {@link ITokenHolder} whose token can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableTokenHolder}.
 */
public interface IFluentMutableTokenHolder<H extends IFluentMutableTokenHolder<H>> extends ITokenHolder {
  /**
   * Sets the token of the current {@link IFluentMutableTokenHolder}.
   * 
   * @param token
   * @return the current {@link IFluentMutableTokenHolder}.
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  H setToken(String token);
}
