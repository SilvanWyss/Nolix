package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiReferenceEntryDeletionDtoMapper;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public final class MultiReferenceEntryDeletionDtoMapper implements IMultiReferenceEntryDeletionDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public MultiReferenceEntryDeletionDto mapMultiReferenceEntryToMultiReferenceEntryDeletionDto(
    final IMultiReferenceEntry<? extends IEntity> multiReferenceEntry) {

    final var multiReference = multiReferenceEntry.getStoredParentMultiReference();
    final var entity = multiReference.getStoredParentEntity();
    final var tableName = entity.getParentTableName();
    final var entityId = entity.getId();
    final var multiReferenceColumn = multiReference.getStoredParentColumn();
    final var multiReferenceColumnId = multiReferenceColumn.getId();
    final var referencedEntityId = multiReferenceEntry.getReferencedEntityId();

    return new MultiReferenceEntryDeletionDto(tableName, entityId, multiReferenceColumnId, referencedEntityId);
  }
}
