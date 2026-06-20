/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.HeaderHolder;

/**
 * A {@link FluentMutableHeaderHolder} is a {@link HeaderHolder} whose header
 * can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableHeaderHolder}
 */
public interface FluentMutableHeaderHolder<H extends HeaderHolder> extends HeaderHolder {
  /**
   * Sets the header of the current {@link FluentMutableHeaderHolder}.
   * 
   * @param header
   * @return the current {@link FluentMutableHeaderHolder}
   * @throws RuntimeException if the given header is null or blank
   */
  H setHeader(String header);
}
