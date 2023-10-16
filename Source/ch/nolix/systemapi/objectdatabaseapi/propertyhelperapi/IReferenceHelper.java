//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//interface
public interface IReferenceHelper extends IPropertyHelper {

  //method declaration
  boolean canSetGivenEntity(final IReference<?> reference, IEntity entity);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForSetEntity(IReference<?> reference, IEntity entity);

  //method declaration
  IProperty getStoredBackReferencingPropertyOrNull(IReference<?> reference);
}
