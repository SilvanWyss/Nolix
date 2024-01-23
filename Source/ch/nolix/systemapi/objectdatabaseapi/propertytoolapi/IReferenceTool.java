//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertytoolapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//interface
public interface IReferenceTool extends IPropertyTool {

  //method declaration
  boolean canSetGivenEntity(final IReference<?> reference, IEntity entity);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForSetEntity(IReference<?> reference, IEntity entity);

  //method declaration
  IProperty getStoredBackReferencingPropertyOrNull(IReference<?> reference);
}
