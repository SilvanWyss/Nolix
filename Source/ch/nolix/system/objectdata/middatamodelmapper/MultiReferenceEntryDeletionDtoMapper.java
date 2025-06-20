package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.objectdataapi.middatamodelmapperapi.IMultiReferenceEntryDeletionDtoMapper;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReferenceEntry;

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
    final var multiReferenceColumnName = multiReferenceColumn.getName();
    final var referencedEntityId = multiReferenceEntry.getReferencedEntityId();

    return new MultiReferenceEntryDeletionDto(tableName, entityId, multiReferenceColumnName, referencedEntityId);
  }
}
