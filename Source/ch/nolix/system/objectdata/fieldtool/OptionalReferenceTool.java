package ch.nolix.system.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IOptionalReferenceTool;
import ch.nolix.systemapi.rawdataapi.datadto.StringContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;

public final class OptionalReferenceTool extends FieldTool implements IOptionalReferenceTool {

  @Override
  public boolean canClear(final IOptionalReference<?> optionalReference) {
    return optionalReference != null
    && optionalReference.belongsToEntity()
    && optionalReference.getStoredParentEntity().isOpen();
  }

  @Override
  public boolean canSetGivenEntity(final IOptionalReference<?> optionalReference, final IEntity entity) {
    return canSetEntity(optionalReference)
    && entity != null
    && entity.isOpen()
    && optionalReference.getReferencedTableName().equals(entity.getParentTableName());
  }

  @Override
  public EntityUpdateDto createEntityUpdateDtoForClear(final IOptionalReference<?> optionalReference) {

    final var parentEntity = optionalReference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      StringContentFieldDto.withColumnName(optionalReference.getName()));
  }

  @Override
  public EntityUpdateDto createEntityUpdateDtoForSetEntity(
    final IOptionalReference<?> optionalReference,
    final IEntity entity) {

    final var parentEntity = optionalReference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      StringContentFieldDto.withColumnNameAndContent(optionalReference.getName(), entity.getId()));
  }

  @Override
  public Optional<? extends IField> getOptionalStoredBackReferencingField(
    final IOptionalReference<?> optionalReference) {
    return optionalReference
      .getReferencedEntity()
      .internalGetStoredFields()
      .getOptionalStoredFirst(p -> p.referencesBackField(optionalReference));
  }

  private boolean canSetEntity(final IOptionalReference<?> optionalReference) {
    return optionalReference != null
    && optionalReference.belongsToEntity()
    && optionalReference.getStoredParentEntity().isOpen();
  }
}
