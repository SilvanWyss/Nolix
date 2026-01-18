/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IMultiValueField;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueFieldExaminer extends IFieldExaminer {
  boolean canAddValue(IMultiValueField<?> multiValueField);

  boolean canAddValue(IMultiValueField<?> multiValueField, Object value);

  boolean canBeCleared(IMultiValueField<?> multiValueField);

  boolean canRemoveValue(IMultiValueField<?> multiValueField);

  boolean canRemoveValue(IMultiValueField<?> multiValueField, Object value);
}
