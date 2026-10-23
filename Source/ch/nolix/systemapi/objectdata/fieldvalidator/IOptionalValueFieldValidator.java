/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

/**
 * @author Silvan Wyss
 */
public interface IOptionalValueFieldValidator extends IFieldValidator<IOptionalValueField<?>> {
  /**
   * @param optionalValueField
   * @param value
   * @param <V>                the type of the value of the given
   *                           optionalValueField
   * @throws RuntimeException if the given value cannot be set to the given
   *                          optionalValueField
   */
  <V> void assertCanSetValue(IOptionalValueField<V> optionalValueField, V value);
}
