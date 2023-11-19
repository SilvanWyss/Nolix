//package declaration
package ch.nolix.system.sqlrawschema.multivalueentrytable;

import ch.nolix.system.sqlrawschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;
import ch.nolix.system.sqlrawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

//class
public final class MultiValueEntryTableSqlDtoCatalogue {

  //constant
  private static final IColumnDto MULTI_VALUE_COLUMN_COLUMN_SQL_DTO = new ColumnDto(
    MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto ENTITY_COLUMN_SQL_DTO = new ColumnDto(
    MultiReferenceEntryTableColumn.ENTITY_ID.getName(), SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto VALUE_COLUMN_SQL_DTO = new ColumnDto(
    MultiValueEntryTableColumn.VALUE.getName(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  public static final ITableDto MULTI_VALUE_ENTRY_TABLE_SQL_DTO = new TableDto(
    MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName(),
    MULTI_VALUE_COLUMN_COLUMN_SQL_DTO,
    ENTITY_COLUMN_SQL_DTO,
    VALUE_COLUMN_SQL_DTO);

  //constructor
  private MultiValueEntryTableSqlDtoCatalogue() {
  }
}
