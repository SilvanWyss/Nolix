//package declaration
package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//interface
public interface IOptionalValueTool extends IFieldTool {

  //method declaration
  boolean canSetGivenValue(IOptionalValue<?> optionalValue, Object value);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForSetValue(IOptionalValue<?> optionalValue, Object value);
}
