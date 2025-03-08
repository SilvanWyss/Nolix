package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IOptionalValueTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValue;

public final class OptionalValueTool extends FieldTool implements IOptionalValueTool {

  @Override
  public boolean canSetGivenValue(final IOptionalValue<?> optionalValue, final Object value) {
    return canSetValue(optionalValue)
    && value != null;
  }

  private boolean canSetValue(final IOptionalValue<?> optionalValue) {
    return optionalValue != null
    && optionalValue.isOpen();
  }
}
