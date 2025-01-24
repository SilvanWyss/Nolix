package ch.nolix.system.sqlrawschema.rawschemadtomapper;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableReferenceDto;
import ch.nolix.systemapi.sqlrawschemaapi.rawschemadtomapperapi.ITableReferenceDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-01-25
 */
public final class TableReferenceDtoMapper implements ITableReferenceDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public TableReferenceDto mapTableReferenceTableSqlRecordToTableReferenceDto(
    final ISqlRecord tableReferenceTableSqlRecord) {

    final var referenceColumnId = tableReferenceTableSqlRecord.getStoredAt1BasedIndex(1);
    final var referencedTableId = tableReferenceTableSqlRecord.getStoredAt1BasedIndex(2);

    return new TableReferenceDto(referenceColumnId, referencedTableId);
  }
}
