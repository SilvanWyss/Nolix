package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiBackReferenceEntryDtoMapper;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public final class MultiBackReferenceEntryDtoMapper implements IMultiBackReferenceEntryDtoMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public MultiBackReferenceEntryDto mapMultiBackReferenceEntryToMultiBackReferenceEntryDto(
    final IMultiBackReferenceEntry<? extends IEntity> multiBackReferenceEntry) {
    final var multiBackReference = multiBackReferenceEntry.getStoredParentMultiBackReference();
    final var entity = multiBackReference.getStoredParentEntity();
    final var tableName = entity.getParentTableName();
    final var entityId = entity.getId();
    final var multiBackReferenceColumn = multiBackReference.getStoredParentColumn();
    final var multiBackReferenceColumnId = multiBackReferenceColumn.getId();
    final var backReferencedEntity = multiBackReferenceEntry.getStoredBackReferencedEntity();
    final var backReferencedEntityId = backReferencedEntity.getId();
    final var backReferencedEntityTable = backReferencedEntity.getStoredParentTable();
    final var backReferencedEntityTableId = backReferencedEntityTable.getId();

    return //
    new MultiBackReferenceEntryDto(
      tableName,
      entityId,
      multiBackReferenceColumnId,
      backReferencedEntityId,
      backReferencedEntityTableId);
  }
}
