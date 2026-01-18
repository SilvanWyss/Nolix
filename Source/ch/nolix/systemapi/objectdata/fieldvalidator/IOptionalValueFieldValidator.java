/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

/**
 * @author Silvan Wyss
 */
public interface IOptionalValueFieldValidator extends IFieldValidator {
  <V> void assertCanSetValue(IOptionalValueField<V> optionalValueField, V value);

  void assertIsNotEmpty(IOptionalValueField<?> optionalValueField);
}
