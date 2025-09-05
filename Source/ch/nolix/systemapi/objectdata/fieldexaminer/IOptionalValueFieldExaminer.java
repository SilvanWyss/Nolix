package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

public interface IOptionalValueFieldExaminer extends IFieldExaminer {
  boolean canSetValue(IOptionalValueField<?> optionalValueField);

  boolean canSetValue(IOptionalValueField<?> optionalValueField, Object value);
}
