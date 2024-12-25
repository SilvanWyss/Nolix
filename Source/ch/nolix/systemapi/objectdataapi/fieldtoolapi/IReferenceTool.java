package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import java.util.Optional;

import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;

public interface IReferenceTool extends IFieldTool {

  Optional<IAbstractBackReference<IEntity>> getOptionalStoredBaseBackReferenceForReference(
    IReference<IEntity> reference);

  boolean toReferenceCanSetEntity(final IReference<?> reference, IEntity entity);
}
