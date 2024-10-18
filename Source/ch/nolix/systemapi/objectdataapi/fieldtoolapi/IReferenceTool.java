package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import java.util.Optional;

import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

public interface IReferenceTool extends IFieldTool {

  IEntityUpdateDto forReferenceCreateEntityUpdateDtoForSetEntity(IReference<?> reference, IEntity entity);

  Optional<IBaseBackReference<IEntity>> getOptionalStoredBaseBackReferenceForReference(IReference<IEntity> reference);

  boolean toReferenceCanSetEntity(final IReference<?> reference, IEntity entity);
}
