package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiReferenceEntryDtoMapper;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

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
    final var multiReferenceColumnId = multiReferenceColumn.getId();
    final var referencedEntityId = multiReferenceEntry.getReferencedEntityId();
    final var referencedEntityTableId = multiReferenceEntry.getReferencedTableId();

    return //
    new MultiReferenceEntryDto(
      tableName,
      entityId,
      multiReferenceColumnId,
      referencedEntityId,
      referencedEntityTableId);
  }
}
