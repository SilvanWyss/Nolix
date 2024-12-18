package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;

public interface IOptionalValueTool extends IFieldTool {

  boolean canSetGivenValue(IOptionalValue<?> optionalValue, Object value);

  EntityUpdateDto createEntityUpdateDtoForSetValue(IOptionalValue<?> optionalValue, Object value);
}
