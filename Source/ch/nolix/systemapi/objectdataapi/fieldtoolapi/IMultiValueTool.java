package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

public interface IMultiValueTool extends IFieldTool {

  boolean canAddGivenValue(IMultiValue<?> multiValue, Object value);

  boolean canClear(IMultiValue<?> multiValue);

  <V> boolean canRemoveValue(IMultiValue<V> multiValue, V value);

  <V> IEntityUpdateDto createEntityUpdateDtoForAddedValue(IMultiValue<V> multiValue, V addedValue);

  IEntityUpdateDto createEntityUpdateDtoForClear(IMultiValue<?> multiValue);
}
