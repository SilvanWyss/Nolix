/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalHeaderHolder;

/**
 * A {@link IFluentMutableOptionalHeaderHolder} is a
 * {@link IOptionalHeaderHolder} whose header can be set and removed
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableOptionalHeaderHolder}.
 */
public interface IFluentMutableOptionalHeaderHolder<H extends IFluentMutableOptionalHeaderHolder<H>>
extends IOptionalHeaderHolder {
  /**
   * Removes the header of current {@link IFluentMutableOptionalHeaderHolder}.
   */
  void removeHeader();

  /**
   * Sets the header of the current {@link IFluentMutableOptionalHeaderHolder}.
   * 
   * @param header
   * @return the current {@link IFluentMutableOptionalHeaderHolder}.
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  H setHeader(String header);
}
