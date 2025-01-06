package ch.nolix.system.sqlrawschema.multibackreferenceentrytable;

import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiBackReferenceEntryTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiEntryTableType;
import ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.dto.TableDto;

public final class MultiBackReferenceEntryTableSqlDtoCatalogue {

  private static final ColumnDto ENTITY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiBackReferenceEntryTableColumn.ENTITY_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto MULTI_BACK_REFERENCE_COLUMN_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiBackReferenceEntryTableColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto BACK_REFERENCED_ENTITY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiBackReferenceEntryTableColumn.BACK_REFERENCED_ENTITY_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  public static final TableDto MULTI_BACK_REFERENCE_ENTRY_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    MultiEntryTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName(),
    ENTITY_COLUMN_SQL_DTO,
    MULTI_BACK_REFERENCE_COLUMN_COLUMN_SQL_DTO,
    BACK_REFERENCED_ENTITY_COLUMN_SQL_DTO);

  private MultiBackReferenceEntryTableSqlDtoCatalogue() {
  }
}
