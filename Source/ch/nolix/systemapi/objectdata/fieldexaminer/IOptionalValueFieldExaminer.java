/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

/**
 * @author Silvan Wyss
 */
public interface IOptionalValueFieldExaminer extends IFieldExaminer {
  boolean canSetValue(IOptionalValueField<?> optionalValueField);

  boolean canSetValue(IOptionalValueField<?> optionalValueField, Object value);
}
