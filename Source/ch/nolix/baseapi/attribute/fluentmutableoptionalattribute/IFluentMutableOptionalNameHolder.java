/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalNameHolder;

/**
 * A {@link IFluentMutableOptionalNameHolder} is a {@link IOptionalNameHolder}
 * whose name can be set programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableOptionalNameHolder}
 */
public interface IFluentMutableOptionalNameHolder<H extends IFluentMutableOptionalNameHolder<H>>
extends IOptionalNameHolder {
  /**
   * Removes the name of the current {@link IFluentMutableOptionalNameHolder}.
   */
  void removeName();

  /**
   * Sets the name of the current {@link IFluentMutableOptionalNameHolder}.
   * 
   * @param name
   * @return the current {@link IFluentMutableOptionalNameHolder}
   * @throws RuntimeException if the given name is null or blank
   */
  H setName(String name);
}
