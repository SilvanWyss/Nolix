//package declaration
package ch.nolix.system.sqlrawschema.multibackreferenceentrytable;

//own imports
import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

//class
public final class MultiBackReferenceEntryTableSqlDtoCatalogue {

  //constant
  private static final IColumnDto ENTITY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiBackReferenceEntryTableColumn.ENTITY_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  //constant
  private static final IColumnDto MULTI_BACK_REFERENCE_COLUMN_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiBackReferenceEntryTableColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  //constant
  private static final IColumnDto BACK_REFERENCED_ENTITY_COLUMN_SQL_DTO = ColumnDto.withNameAndDataType(
    MultiBackReferenceEntryTableColumn.BACK_REFERENCED_ENTITY_ID.getName(),
    DatatypeTypeCatalogue.TEXT);

  //constant
  public static final ITableDto MULTI_BACK_REFERENCE_ENTRY_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    MultiEntryTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName(),
    ENTITY_COLUMN_SQL_DTO,
    MULTI_BACK_REFERENCE_COLUMN_COLUMN_SQL_DTO,
    BACK_REFERENCED_ENTITY_COLUMN_SQL_DTO);

  //constructor
  private MultiBackReferenceEntryTableSqlDtoCatalogue() {
  }
}
