//package declaration
package ch.nolix.systemapi.objectdataapi.propertytoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//interface
public interface IOptionalValueTool extends IPropertyTool {

  //method declaration
  boolean canSetGivenValue(IOptionalValue<?> optionalValue, Object value);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForSetValue(IOptionalValue<?> optionalValue, Object value);
}
