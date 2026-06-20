/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalNameHolder;

/**
 * A {@link FluentMutableOptionalNameHolder} is a {@link OptionalNameHolder}
 * whose name can be set programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalNameHolder}
 */
public interface FluentMutableOptionalNameHolder<H extends FluentMutableOptionalNameHolder<H>>
extends OptionalNameHolder {
  /**
   * Removes the name of the current {@link FluentMutableOptionalNameHolder}.
   */
  void removeName();

  /**
   * Sets the name of the current {@link FluentMutableOptionalNameHolder}.
   * 
   * @param name
   * @return the current {@link FluentMutableOptionalNameHolder}
   * @throws RuntimeException if the given name is null or blank
   */
  H setName(String name);
}
