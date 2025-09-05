package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link IValueHolder} has a value.
 * 
 * @author Silvan Wyss
 * @version 2018-09-09
 * @param <V> is the type of the value of a {@link IValueHolder}.
 */
public interface IValueHolder<V> {
  /**
   * @return the value of the current {@link IValueHolder}.
   */
  V getValue();
}
