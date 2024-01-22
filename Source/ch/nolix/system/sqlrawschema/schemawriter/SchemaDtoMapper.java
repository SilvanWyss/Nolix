//package declaration
package ch.nolix.system.sqlrawschema.schemawriter;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
final class SchemaDtoMapper {

  //constant
  private static final //
  ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto SQL_ID_COLUMN_DTO = //
  new ColumnDto(PascalCaseVariableCatalogue.ID, DatatypeTypeCatalogue.TEXT);

  //constant
  private static final //
  ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto SQL_SAVE_STAMP_COLUMN_DTO = //
  new ColumnDto(PascalCaseVariableCatalogue.SAVE_STAMP, DatatypeTypeCatalogue.INTEGER);

  //method
  public ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto createSqlColumnDtoFrom(
    final IColumnDto column) {
    return new ColumnDto(column.getName(), DatatypeTypeCatalogue.TEXT);
  }

  //method
  public ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto createSqlTableDtoFrom(
    final ITableDto table) {
    return new TableDto(TableType.ENTITY_TABLE.getQualifyingPrefix() + table.getName(), createSqlColumnDtosFrom(table));
  }

  //method
  private IContainer<ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto> createSqlColumnDtosFrom(
    final ITableDto table) {

    final var columns = new LinkedList<ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto>();

    columns.addAtEnd(SQL_ID_COLUMN_DTO);

    for (final var c : table.getColumns()) {
      columns.addAtEnd(createSqlColumnDtoFrom(c));
    }

    columns.addAtEnd(SQL_SAVE_STAMP_COLUMN_DTO);

    return columns;
  }
}
