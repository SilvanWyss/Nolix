package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import java.util.Optional;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

public interface IOptionalReferenceTool {

  boolean canClear(IOptionalReference<?> optionalReference);

  boolean canSetGivenEntity(IOptionalReference<?> optionalReference, IEntity entity);

  IEntityUpdateDto createEntityUpdateDtoForClear(IOptionalReference<?> optionalReference);

  IEntityUpdateDto createEntityUpdateDtoForSetEntity(IOptionalReference<?> optionalReference, IEntity entity);

  Optional<? extends IField> getOptionalStoredBackReferencingField(IOptionalReference<?> optionalReference);
}
