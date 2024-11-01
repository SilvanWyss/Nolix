package ch.nolix.system.sqlrawschema.multireferenceentrytable;

import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

public final class MultiReferenceEntryTableSqlDtoCatalogue {

  private static final IColumnDto MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final IColumnDto ENTITY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiReferenceEntryTableColumn.ENTITY_ID.getName(), DatatypeTypeCatalogue.TEXT);

  private static final IColumnDto REFERENCED_ENTITY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  public static final ITableDto MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName(),
    MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO,
    ENTITY_COLUMN_SQL_DTO,
    REFERENCED_ENTITY_COLUMN_SQL_DTO);

  private MultiReferenceEntryTableSqlDtoCatalogue() {
  }
}
