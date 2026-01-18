/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.fieldexaminer.IValueFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IValueField;

/**
 * @author Silvan Wyss
 */
public final class ValueFieldExaminer extends FieldExaminer implements IValueFieldExaminer {
  @Override
  public boolean canSetValue(final IValueField<?> valueField) {
    return //
    valueField != null
    && valueField.isOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSetValue(final IValueField<?> valueField, final Object value) {
    return //
    canSetValue(valueField)
    && value != null;
  }
}
