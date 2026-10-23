/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IValueField;

/**
 * @author Silvan Wyss
 */
public interface IValueFieldExaminer extends IFieldExaminer<IValueField<?>> {
  /**
   * @param valueField
   * @return true if a value can be set to the given valueField, false otherwise
   */
  boolean canSetValue(IValueField<?> valueField);

  /**
   * @param valueField
   * @param value
   * @return true if the given value can be set to the given valueField, false
   *         otherwise
   */
  boolean canSetValue(IValueField<?> valueField, Object value);
}
