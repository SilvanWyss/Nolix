//package declaration
package ch.nolix.system.sqldatabaserawschema.multireferenceentrytable;

import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDto;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDto;
import ch.nolix.system.sqldatabaserawschema.structure.MultiEntryTableType;
import ch.nolix.system.sqldatabaserawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

//class
public final class MultiReferenceEntryTableSqlDtoCatalogue {

  //constant
  private static final IColumnDto MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO = new ColumnDto(
    MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto ENTITY_COLUMN_SQL_DTO = new ColumnDto(
    MultiReferenceEntryTableColumn.ENTITY_ID.getName(), SqlDatatypeCatalogue.TEXT);

  //constant
  private static final IColumnDto REFERENCED_ENTITY_COLUMN_SQL_DTO = new ColumnDto(
    MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName(),
    SqlDatatypeCatalogue.TEXT);

  //constant
  public static final ITableDto MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO = new TableDto(
    MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName(),
    MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO,
    ENTITY_COLUMN_SQL_DTO,
    REFERENCED_ENTITY_COLUMN_SQL_DTO);

  //constructor
  private MultiReferenceEntryTableSqlDtoCatalogue() {
  }
}
