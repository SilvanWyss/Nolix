package ch.nolix.systemapi.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

public interface IReferenceTool extends IFieldTool {

  Optional<IBaseBackReference<IEntity>> getOptionalStoredBaseBackReferenceForReference(
    IReference<IEntity> reference);

  boolean toReferenceCanSetEntity(final IReference<?> reference, IEntity entity);
}
