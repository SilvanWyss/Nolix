//package declaration
package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//interface
public interface IMultiValueTool extends IFieldTool {

  //method declaration
  boolean canAddGivenValue(IMultiValue<?> multiValue, Object value);

  //method declaration
  boolean canClear(IMultiValue<?> multiValue);

  //method declaration
  <V> boolean canRemoveValue(IMultiValue<V> multiValue, V value);

  //method declaration
  <V> IEntityUpdateDto createEntityUpdateDtoForAddedValue(IMultiValue<V> multiValue, V addedValue);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForClear(IMultiValue<?> multiValue);
}
