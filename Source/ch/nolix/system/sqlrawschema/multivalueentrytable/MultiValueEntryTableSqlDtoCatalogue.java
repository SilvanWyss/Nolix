package ch.nolix.system.sqlrawschema.multivalueentrytable;

import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.system.sqlrawschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

public final class MultiValueEntryTableSqlDtoCatalogue {

  private static final IColumnDto MULTI_VALUE_COLUMN_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  private static final IColumnDto ENTITY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiReferenceEntryTableColumn.ENTITY_ID.getName(), DatatypeTypeCatalogue.TEXT);

  private static final IColumnDto VALUE_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiValueEntryTableColumn.VALUE.getName(),
    DatatypeTypeCatalogue.TEXT);

  public static final ITableDto MULTI_VALUE_ENTRY_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName(),
    MULTI_VALUE_COLUMN_COLUMN_SQL_DTO,
    ENTITY_COLUMN_SQL_DTO,
    VALUE_COLUMN_SQL_DTO);

  private MultiValueEntryTableSqlDtoCatalogue() {
  }
}
