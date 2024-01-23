//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertytoolapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//interface
public interface IValueTool extends IPropertyTool {

  //method declaration
  boolean canSetGivenValue(IValue<?> value, Object valueToSet);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForSetValue(IValue<?> value, Object valueToSet);
}
