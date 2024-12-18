package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;

public interface IValueTool extends IFieldTool {

  boolean canSetValue(IValue<?> value, Object valueToSet);

  EntityUpdateDto createEntityUpdateDtoForSetValue(IValue<?> value, Object valueToSet);
}
