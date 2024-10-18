package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IValueHolder;

/**
 * A {@link IFluentMutableValueHolder} is a {@link IValueHolder} whose value can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2018-09-06
 * @param <FVH> is the type of a {@link IFluentMutableValueHolder}.
 * @param <V>   is the type of the value of a {@link IFluentMutableValueHolder}.
 */
public interface IFluentMutableValueHolder<FVH extends IFluentMutableValueHolder<FVH, V>, V>
extends IValueHolder<V> {

  /**
   * Sets the value of the current {@link IFluentMutableValueHolder}.
   * 
   * @param value
   * @return the current {@link IFluentMutableValueHolder}.
   * @throws RuntimeException if the given value is null.
   */
  FVH setValue(V value);
}
