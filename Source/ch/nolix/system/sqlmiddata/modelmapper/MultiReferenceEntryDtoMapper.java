package ch.nolix.system.sqlmiddata.modelmapper;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.system.midschemaview.modelsearcher.DatabaseViewSearcher;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelsearcherapi.IDatabaseViewSearcher;
import ch.nolix.systemapi.sqlmiddataapi.modelmapperapi.IMultiReferenceEntryDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
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

    final var multiReferenceColumn = //
    DATABASE_VIEW_SEARCHER.getColumnViewByColumnId(databaseView, multiReferenceColumnId);

    final var multiReferenceColumnName = multiReferenceColumn.name();
    final var referencedEntityId = multiReferenceSqlRecord.getStoredAtOneBasedIndex(4);
    final var referencedEntityTableId = multiReferenceSqlRecord.getStoredAtOneBasedIndex(5);

    final var referencedEntityTable = //
    DATABASE_VIEW_SEARCHER.getTableViewByTableId(databaseView, referencedEntityTableId);

    final var referencedEntityTableName = referencedEntityTable.name();

    return //
    new MultiReferenceEntryDto(
      tableName,
      entityId,
      multiReferenceColumnName,
      referencedEntityId,
      referencedEntityTableName);
  }
}
