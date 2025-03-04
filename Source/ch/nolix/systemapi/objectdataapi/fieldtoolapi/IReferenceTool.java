package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import java.util.Optional;

import ch.nolix.systemapi.objectdataapi.fieldapi.IReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

public interface IReferenceTool extends IFieldTool {

  Optional<IAbstractBackReference<IEntity>> getOptionalStoredBaseBackReferenceForReference(
    IReference<IEntity> reference);

  boolean toReferenceCanSetEntity(final IReference<?> reference, IEntity entity);
}
