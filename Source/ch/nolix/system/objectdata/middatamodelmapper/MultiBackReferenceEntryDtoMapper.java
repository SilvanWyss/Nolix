package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middataapi.modelapi.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.objectdataapi.middatamodelmapperapi.IMultiBackReferenceEntryDtoMapper;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiBackReferenceEntry;

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
    final var multiBackReferenceColumnName = multiBackReferenceColumn.getName();
    final var backReferencedEntityId = multiBackReferenceEntry.getBackReferencedEntityId();
    final var backReferencedEntityTableName = multiBackReference.getBackReferencedTableName();

    return //
    new MultiBackReferenceEntryDto(
      tableName,
      entityId,
      multiBackReferenceColumnName,
      backReferencedEntityId,
      backReferencedEntityTableName);
  }
}
