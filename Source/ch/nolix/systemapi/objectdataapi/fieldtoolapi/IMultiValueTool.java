package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;

public interface IMultiValueTool extends IFieldTool {

  boolean canAddGivenValue(IMultiValue<?> multiValue, Object value);

  boolean canClear(IMultiValue<?> multiValue);

  <V> boolean canRemoveValue(IMultiValue<V> multiValue, V value);

  <V> EntityUpdateDto createEntityUpdateDtoForAddedValue(IMultiValue<V> multiValue, V addedValue);

  EntityUpdateDto createEntityUpdateDtoForClear(IMultiValue<?> multiValue);
}
