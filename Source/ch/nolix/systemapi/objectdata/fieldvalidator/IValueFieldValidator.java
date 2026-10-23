/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IValueField;

/**
 * @author Silvan Wyss
 */
public interface IValueFieldValidator extends IFieldValidator<IValueField<?>> {
  /**
   * @param valueField
   * @param value
   * @throws RuntimeException if the given value cannot be set to the given
   *                          valueField
   */
  void assertCanSetValue(IValueField<?> valueField, Object value);
}
