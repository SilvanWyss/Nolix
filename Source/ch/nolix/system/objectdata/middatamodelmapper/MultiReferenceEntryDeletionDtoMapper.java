/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiReferenceEntryDeletionDtoMapper;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
 */
public final class MultiReferenceEntryDeletionDtoMapper implements IMultiReferenceEntryDeletionDtoMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public MultiReferenceEntryDeletionDto mapMultiReferenceEntryToMultiReferenceEntryDeletionDto(
    final IMultiReferenceEntry<? extends Entity> multiReferenceEntry) {
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
