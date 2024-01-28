//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertytoolapi;

//Java imports
import java.util.Optional;

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
  Optional<? extends IProperty> getOptionalStoredBackReferencingProperty(IReference<?> reference);
}
