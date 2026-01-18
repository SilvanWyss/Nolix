/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.sqlmiddata.modelmapper.IMultiBackReferenceEntryDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class MultiBackReferenceEntryDtoMapper implements IMultiBackReferenceEntryDtoMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<MultiBackReferenceEntryDto> mapMultiBackReferenceEntrySqlRecordsToMultiBackReferenceEntryDtos(
    final IContainer<ISqlRecord> multiBackReferenceEntrySqlRecords,
    final String tableName) {
    return //
    multiBackReferenceEntrySqlRecords.to(
      e -> mapMultiBackReferenceEntrySqlRecordToMultiBackReferenceEntryDto(e, tableName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MultiBackReferenceEntryDto mapMultiBackReferenceEntrySqlRecordToMultiBackReferenceEntryDto(
    final ISqlRecord multiBackReferenceEntrySqlRecord,
    final String tableName) {
    final var entityId = multiBackReferenceEntrySqlRecord.getStoredAtOneBasedIndex(1);
    final var multiBackReferenceColumnId = multiBackReferenceEntrySqlRecord.getStoredAtOneBasedIndex(2);
    final var backReferencedEntityId = multiBackReferenceEntrySqlRecord.getStoredAtOneBasedIndex(3);
    final var backReferencedEntityTableId = multiBackReferenceEntrySqlRecord.getStoredAtOneBasedIndex(4);

    return //
    new MultiBackReferenceEntryDto(
      tableName,
      entityId,
      multiBackReferenceColumnId,
      backReferencedEntityId,
      backReferencedEntityTableId);
  }
}
