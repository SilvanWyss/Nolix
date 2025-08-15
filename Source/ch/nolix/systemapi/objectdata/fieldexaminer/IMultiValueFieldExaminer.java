package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public interface IMultiValueFieldExaminer extends IFieldExaminer {

  boolean canAddValue(IMultiValueField<?> multiValueField);

  boolean canAddValue(IMultiValueField<?> multiValueField, Object value);

  boolean canClear(IMultiValueField<?> multiValueField);

  boolean canRemoveValue(IMultiValueField<?> multiValueField);

  boolean canRemoveValue(IMultiValueField<?> multiValueField, Object value);
}
