package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.objectdataapi.middatamodelmapperapi.IMultiReferenceEntryDtoMapper;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public final class MultiReferenceEntryDtoMapper implements IMultiReferenceEntryDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public MultiReferenceEntryDto mapMultiReferenceEntryToMultiReferenceEntryDto(
    final IMultiReferenceEntry<? extends IEntity> multiReferenceEntry) {

    final var multiReference = multiReferenceEntry.getStoredParentMultiReference();
    final var entity = multiReference.getStoredParentEntity();
    final var tableName = entity.getParentTableName();
    final var entityId = entity.getId();
    final var multiReferenceColumn = multiReference.getStoredParentColumn();
    final var multiReferenceColumnName = multiReferenceColumn.getName();
    final var referencedEntityId = multiReferenceEntry.getReferencedEntityId();
    final var referencedEntity = multiReference.getStoredParentEntity();
    final var referencedEntityTableName = referencedEntity.getParentTableName();

    return //
    new MultiReferenceEntryDto(
      tableName,
      entityId,
      multiReferenceColumnName,
      referencedEntityId,
      referencedEntityTableName);
  }
}
