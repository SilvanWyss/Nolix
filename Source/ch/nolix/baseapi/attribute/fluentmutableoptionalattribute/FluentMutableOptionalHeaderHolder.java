/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalHeaderHolder;

/**
 * A {@link FluentMutableOptionalHeaderHolder} is a
 * {@link OptionalHeaderHolder} whose header can be set programmatically and
 * fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalHeaderHolder}
 */
public interface FluentMutableOptionalHeaderHolder<H extends FluentMutableOptionalHeaderHolder<H>>
extends OptionalHeaderHolder {
  /**
   * Removes the header of current {@link FluentMutableOptionalHeaderHolder}.
   */
  void removeHeader();

  /**
   * Sets the header of the current {@link FluentMutableOptionalHeaderHolder}.
   * 
   * @param header
   * @return the current {@link FluentMutableOptionalHeaderHolder}
   * @throws RuntimeException if the given header is null or blank
   */
  H setHeader(String header);
}
