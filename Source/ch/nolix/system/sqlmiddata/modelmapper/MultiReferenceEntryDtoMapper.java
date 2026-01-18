/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.modelmapper;

import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.system.midschemaview.modelsearcher.DatabaseViewSearcher;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;
import ch.nolix.systemapi.midschemaview.modelsearcher.IDatabaseViewSearcher;
import ch.nolix.systemapi.sqlmiddata.modelmapper.IMultiReferenceEntryDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class MultiReferenceEntryDtoMapper implements IMultiReferenceEntryDtoMapper {
  private static final IDatabaseViewSearcher DATABASE_VIEW_SEARCHER = new DatabaseViewSearcher();

  /**
   * {@inheritDoc}
   */
  @Override
  public MultiReferenceEntryDto mapMultiReferenceEntrySqlRecordToMultiReferenceEntryDto(
    final ISqlRecord multiReferenceSqlRecord,
    final DatabaseViewDto databaseView) {
    final var tableId = multiReferenceSqlRecord.getStoredAtOneBasedIndex(2);
    final var table = DATABASE_VIEW_SEARCHER.getTableViewByTableId(databaseView, tableId);
    final var tableName = table.name();
    final var entityId = multiReferenceSqlRecord.getStoredAtOneBasedIndex(1);
    final var multiReferenceColumnId = multiReferenceSqlRecord.getStoredAtOneBasedIndex(3);
    final var referencedEntityId = multiReferenceSqlRecord.getStoredAtOneBasedIndex(4);
    final var referencedEntityTableId = multiReferenceSqlRecord.getStoredAtOneBasedIndex(5);

    return //
    new MultiReferenceEntryDto(
      tableName,
      entityId,
      multiReferenceColumnId,
      referencedEntityId,
      referencedEntityTableId);
  }
}
