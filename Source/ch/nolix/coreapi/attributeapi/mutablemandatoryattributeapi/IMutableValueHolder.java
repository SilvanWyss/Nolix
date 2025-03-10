package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IValueHolder;

/**
 * A {@link IMutableValueHolder} is a {@link IValueHolder} whose value can be
 * set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-07
 * @param <V> is the value of a {@link IMutableValueHolder}.
 */
public interface IMutableValueHolder<V> extends IValueHolder<V> {

  /**
   * Sets the value of the current {@link IMutableValueHolder}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null.
   */
  void setValue(V value);
}
