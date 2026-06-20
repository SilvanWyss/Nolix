/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.TokenHolder;

/**
 * A {@link FluentMutableTokenHolder} is a {@link TokenHolder} whose token can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableTokenHolder}
 */
public interface FluentMutableTokenHolder<H extends FluentMutableTokenHolder<H>> extends TokenHolder {
  /**
   * Sets the token of the current {@link FluentMutableTokenHolder}.
   * 
   * @param token
   * @return the current {@link FluentMutableTokenHolder}
   * @throws RuntimeException if the given token is null or blank
   */
  H setToken(String token);
}
