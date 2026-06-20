/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ValueHolder;

/**
 * A {@link FluentMutableValueHolder} is a {@link ValueHolder} whose value can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableValueHolder}
 * @param <V> the type of the value of a {@link FluentMutableValueHolder}
 */
public interface FluentMutableValueHolder<H extends FluentMutableValueHolder<H, V>, V> extends ValueHolder<V> {
  /**
   * Sets the value of the current {@link FluentMutableValueHolder}.
   * 
   * @param value
   * @return the current {@link FluentMutableValueHolder}
   * @throws RuntimeException if the given value is null
   */
  H setValue(V value);
}
