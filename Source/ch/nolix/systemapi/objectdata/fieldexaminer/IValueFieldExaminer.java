package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IValueField;

public interface IValueFieldExaminer extends IFieldExaminer {
  boolean canSetValue(IValueField<?> valueField);

  boolean canSetValue(IValueField<?> valueField, Object value);
}
