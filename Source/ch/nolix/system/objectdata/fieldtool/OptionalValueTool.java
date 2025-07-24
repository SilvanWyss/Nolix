package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IOptionalValueTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValueField;

public final class OptionalValueTool extends FieldTool implements IOptionalValueTool {

  @Override
  public boolean canSetGivenValue(final IOptionalValueField<?> optionalValue, final Object value) {
    return canSetValue(optionalValue)
    && value != null;
  }

  private boolean canSetValue(final IOptionalValueField<?> optionalValue) {
    return optionalValue != null
    && optionalValue.isOpen();
  }
}
