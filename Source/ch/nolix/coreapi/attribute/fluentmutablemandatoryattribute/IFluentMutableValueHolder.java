/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IValueHolder;

/**
 * A {@link IFluentMutableValueHolder} is a {@link IValueHolder} whose value can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableValueHolder}.
 * @param <V> is the type of the value of a {@link IFluentMutableValueHolder}.
 */
public interface IFluentMutableValueHolder<H extends IFluentMutableValueHolder<H, V>, V> extends IValueHolder<V> {
  /**
   * Sets the value of the current {@link IFluentMutableValueHolder}.
   * 
   * @param value
   * @return the current {@link IFluentMutableValueHolder}.
   * @throws RuntimeException if the given value is null.
   */
  H setValue(V value);
}
