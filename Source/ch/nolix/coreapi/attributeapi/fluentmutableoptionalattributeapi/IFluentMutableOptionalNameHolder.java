package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalNameHolder;

/**
 * A {@link IFluentMutableOptionalNameHolder} is a {@link IOptionalNameHolder}
 * whose name can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <H> is the type of a {@link IFluentMutableOptionalNameHolder}.
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
   * @return the current {@link IFluentMutableOptionalNameHolder}.
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  H setName(String name);
}
