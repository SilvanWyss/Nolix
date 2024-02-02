//package declaration
package ch.nolix.systemapi.objectdataapi.propertytoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//interface
public interface IMultiValueTool extends IPropertyTool {

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
