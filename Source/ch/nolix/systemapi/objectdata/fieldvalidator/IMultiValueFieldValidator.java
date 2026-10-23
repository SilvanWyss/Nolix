/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IMultiValueField;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueFieldValidator extends IFieldValidator<IMultiValueField<?>> {
  /**
   * @param multiValueField
   * @param value
   * @param <V>             the type of the values of the given multiValueField
   * @throws RuntimeException if the given multiValueField cannot add the given
   *                          value
   */
  <V> void assertCanAddValue(IMultiValueField<V> multiValueField, V value);

  /**
   * @param multiValueField
   * @throws RuntimeException if the given multiValueField cannot be cleared
   */
  void assertCanBeCleared(IMultiValueField<?> multiValueField);

  /**
   * @param multiValueField
   * @param value
   * @param <V>             the type of the values of the given multiValueField
   * @throws RuntimeException if the given multiValueField cannot remove the given
   *                          value
   */
  <V> void assertCanRemoveValue(IMultiValueField<V> multiValueField, V value);
}
