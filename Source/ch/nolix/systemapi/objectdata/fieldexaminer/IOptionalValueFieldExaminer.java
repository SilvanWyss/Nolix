/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

/**
 * @author Silvan Wyss
 */
public interface IOptionalValueFieldExaminer extends IFieldExaminer<IOptionalValueField<?>> {
  /**
   * @param optionalValueField
   * @return true if a value can be set to the given optionalValueField, false
   *         otherwise
   */
  boolean canSetValue(IOptionalValueField<?> optionalValueField);

  /**
   * @param optionalValueField
   * @param value
   * @return true if the given value can be set to the given optionalValueField,
   *         false otherwise
   */
  boolean canSetValue(IOptionalValueField<?> optionalValueField, Object value);
}
