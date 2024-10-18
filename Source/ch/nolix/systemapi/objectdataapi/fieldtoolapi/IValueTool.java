package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

public interface IValueTool extends IFieldTool {

  boolean canSetValue(IValue<?> value, Object valueToSet);

  IEntityUpdateDto createEntityUpdateDtoForSetValue(IValue<?> value, Object valueToSet);
}
