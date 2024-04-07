//package declaration
package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//interface
public interface IValueTool extends IFieldTool {

  //method declaration
  boolean canSetGivenValue(IValue<?> value, Object valueToSet);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForSetValue(IValue<?> value, Object valueToSet);
}
