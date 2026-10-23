/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IMultiValueField;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueFieldExaminer extends IFieldExaminer<IMultiValueField<?>> {
  /**
   * @param multiValueField
   * @return true if the given multiValueField can add a value, false otherwise
   */
  boolean canAddValue(IMultiValueField<?> multiValueField);

  /**
   * @param multiValueField
   * @param value
   * @return true if the given multiValueField can add the given value, false
   *         otherwise
   */
  boolean canAddValue(IMultiValueField<?> multiValueField, Object value);

  /**
   * @param multiValueField
   * @return true if the given multiValueField can be cleared, false otherwise
   */
  boolean canBeCleared(IMultiValueField<?> multiValueField);

  /**
   * @param multiValueField
   * @return true if the given multiValueField can remove a value, false otherwise
   */
  boolean canRemoveValue(IMultiValueField<?> multiValueField);

  /**
   * @param multiValueField
   * @param value
   * @return true if the given multiValueField can remove the given value, false
   *         otherwise
   */
  boolean canRemoveValue(IMultiValueField<?> multiValueField, Object value);
}
