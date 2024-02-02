//package declaration
package ch.nolix.systemapi.objectdataapi.propertytoolapi;

//Java imports
import java.util.Optional;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
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
