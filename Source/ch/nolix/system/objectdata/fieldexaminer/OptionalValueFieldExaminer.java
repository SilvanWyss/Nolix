package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.fieldexaminer.IOptionalValueFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

/**
 * @author Silvan Wyss
 */
public final class OptionalValueFieldExaminer extends FieldExaminer implements IOptionalValueFieldExaminer {
  @Override
  public boolean canSetValue(final IOptionalValueField<?> optionalValueField) {
    return //
    optionalValueField != null
    && optionalValueField.isOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSetValue(final IOptionalValueField<?> optionalValueField, final Object value) {
    return //
    canSetValue(optionalValueField)
    && value != null;
  }
}
