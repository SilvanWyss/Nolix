package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

public interface IOptionalValueTool extends IFieldTool {

  boolean canSetGivenValue(IOptionalValue<?> optionalValue, Object value);

  IEntityUpdateDto createEntityUpdateDtoForSetValue(IOptionalValue<?> optionalValue, Object value);
}
