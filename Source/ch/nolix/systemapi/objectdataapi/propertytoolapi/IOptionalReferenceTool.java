//package declaration
package ch.nolix.systemapi.objectdataapi.propertytoolapi;

//Java imports
import java.util.Optional;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//interface
public interface IOptionalReferenceTool {

  //method declaration
  boolean canClear(IOptionalReference<?> optionalReference);

  //method declaration
  boolean canSetGivenEntity(IOptionalReference<?> optionalReference, IEntity entity);

  //method
  IEntityUpdateDto createEntityUpdateDtoForClear(IOptionalReference<?> optionalReference);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForSetEntity(IOptionalReference<?> optionalReference, IEntity entity);

  //method declaration
  Optional<? extends IProperty> getOptionalStoredBackReferencingProperty(IOptionalReference<?> optionalReference);
}
