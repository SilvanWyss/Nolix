package ch.nolix.system.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.objectdata.fieldtool.IReferenceTool;
import ch.nolix.systemapi.objectdata.model.IAbstractBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

public final class ReferenceTool extends FieldTool implements IReferenceTool {

  @Override
  public Optional<IAbstractBackReference<IEntity>> getOptionalStoredBaseBackReferenceForReference(
    final IReference<IEntity> reference) {

    if (reference.isEmpty()) {
      return Optional.empty();
    }

    return getOptionalStoredBaseBackReferenceOfReferenceWhenContainsAny(reference);
  }

  @Override
  public boolean toReferenceCanSetEntity(final IReference<?> reference, final IEntity entity) {
    return //
    canSetEntity(reference)
    && isOpen(reference)
    && reference.getReferencedTableName().equals(entity.getParentTableName())
    && !reference.referencesEntity(entity); //Important: When a Reference is set new data records could be created.
  }

  private boolean canSetEntity(final IReference<?> reference) {
    return //
    isOpen(reference)
    && reference.belongsToEntity();
  }

  @SuppressWarnings("unchecked")
  private Optional<IAbstractBackReference<IEntity>> getOptionalStoredBaseBackReferenceOfReferenceWhenContainsAny(
    final IReference<IEntity> reference) {

    final var referencedEntity = reference.getStoredReferencedEntity();

    final var backReference = //
    referencedEntity.internalGetStoredFields().getOptionalStoredFirst(p -> p.referencesBackField(reference));

    return backReference.map(br -> (IAbstractBackReference<IEntity>) br);
  }

  private boolean isOpen(final IDatabaseObject databaseObject) {
    return //
    databaseObject != null
    && databaseObject.isOpen();
  }
}
