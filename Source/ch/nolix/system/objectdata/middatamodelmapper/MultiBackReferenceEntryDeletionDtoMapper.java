package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middataapi.modelapi.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.objectdataapi.middatamodelmapperapi.IMultiBackReferenceEntryDeletionDtoMapper;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiBackReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public final class MultiBackReferenceEntryDeletionDtoMapper implements IMultiBackReferenceEntryDeletionDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public MultiBackReferenceEntryDeletionDto mapMultiBackReferenceEntryToMultiBackReferenceEntryDeletionDto(
    final IMultiBackReferenceEntry<? extends IEntity> multiBackReferenceEntry) {

    final var multiBackReference = multiBackReferenceEntry.getStoredParentMultiBackReference();
    final var entity = multiBackReference.getStoredParentEntity();
    final var tableName = entity.getParentTableName();
    final var entityId = entity.getId();
    final var multiBackReferenceColumn = multiBackReference.getStoredParentColumn();
    final var multiBackReferenceColumnId = multiBackReferenceColumn.getId();
    final var backReferencedEntityId = multiBackReferenceEntry.getBackReferencedEntityId();

    return //
    new MultiBackReferenceEntryDeletionDto(tableName, entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }
}
