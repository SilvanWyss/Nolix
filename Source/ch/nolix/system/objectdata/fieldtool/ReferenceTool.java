package ch.nolix.system.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IReferenceTool;
import ch.nolix.systemapi.rawdataapi.datadto.StringContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;

public final class ReferenceTool extends FieldTool implements IReferenceTool {

  @Override
  public EntityUpdateDto forReferenceCreateEntityUpdateDtoForSetEntity(
    final IReference<?> reference,
    final IEntity entity) {

    final var parentEntity = reference.getStoredParentEntity();

    return //
    new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      StringContentFieldDto.withColumnNameAndContent(reference.getName(), entity.getId()));
  }

  @Override
  public Optional<IBaseBackReference<IEntity>> getOptionalStoredBaseBackReferenceForReference(
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
  private Optional<IBaseBackReference<IEntity>> getOptionalStoredBaseBackReferenceOfReferenceWhenContainsAny(
    final IReference<IEntity> reference) {

    final var referencedEntity = reference.getStoredReferencedEntity();

    final var backReference = //
    referencedEntity.internalGetStoredFields().getOptionalStoredFirst(p -> p.referencesBackField(reference));

    return backReference.map(br -> (IBaseBackReference<IEntity>) br);
  }

  private boolean isOpen(final IDatabaseObject databaseObject) {
    return //
    databaseObject != null
    && databaseObject.isOpen();
  }
}
