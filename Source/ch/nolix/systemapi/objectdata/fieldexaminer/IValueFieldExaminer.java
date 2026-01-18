/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IValueField;

/**
 * @author Silvan Wyss
 */
public interface IValueFieldExaminer extends IFieldExaminer {
  boolean canSetValue(IValueField<?> valueField);

  boolean canSetValue(IValueField<?> valueField, Object value);
}
