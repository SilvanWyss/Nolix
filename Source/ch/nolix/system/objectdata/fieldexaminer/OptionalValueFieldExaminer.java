package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.fieldexaminer.IOptionalValueFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

public final class OptionalValueFieldExaminer extends FieldExaminer implements IOptionalValueFieldExaminer {

  @Override
  public boolean canSetValue(final IOptionalValueField<?> optionalValueField) {
    return //
    optionalValueField != null
    && optionalValueField.isOpen();
  }

  @Override
  public boolean canSetValue(final IOptionalValueField<?> optionalValueField, final Object value) {
    return //
    canSetValue(optionalValueField)
    && value != null;
  }
}
