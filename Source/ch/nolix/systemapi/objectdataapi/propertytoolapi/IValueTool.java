//package declaration
package ch.nolix.systemapi.objectdataapi.propertytoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//interface
public interface IValueTool extends IPropertyTool {

  //method declaration
  boolean canSetGivenValue(IValue<?> value, Object valueToSet);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForSetValue(IValue<?> value, Object valueToSet);
}
