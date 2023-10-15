//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//interface
public interface IOptionalReferenceHelper {

  // method declaration
  boolean canClear(IOptionalReference<?> optionalReference);

  // method declaration
  boolean canSetGivenEntity(final IOptionalReference<?> optionalReference, IEntity entity);

  // method
  IEntityUpdateDto createEntityUpdateDtoForClear(IOptionalReference<?> optionalReference);

  // method declaration
  IEntityUpdateDto createEntityUpdateDtoForSetEntity(IOptionalReference<?> optionalReference, IEntity entity);

  // method declaration
  IProperty getStoredBackReferencingPropertyOrNull(IOptionalReference<?> optionalReference);
}
