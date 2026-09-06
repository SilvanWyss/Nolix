/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IValueField;

/**
 * @author Silvan Wyss
 */
public interface IValueFieldValidator extends IFieldValidator<IValueField<?>> {
  void assertCanSetValue(IValueField<?> valueField, Object value);
}
