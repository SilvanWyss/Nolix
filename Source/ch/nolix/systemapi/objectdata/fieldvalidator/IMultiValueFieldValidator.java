/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IMultiValueField;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueFieldValidator extends IFieldValidator {
  <V> void assertCanAddValue(IMultiValueField<V> multiValueField, V value);

  void assertCanBeCleared(IMultiValueField<?> multiValueField);

  <V> void assertCanRemoveValue(IMultiValueField<V> multiValueField, V value);
}
