//package declaration
package ch.nolix.system.sqlrawschema.schemawriter;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;
import ch.nolix.system.sqlrawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.system.sqlrawschema.tabletype.TableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
final class SchemaDtoMapper {

  //constant
  private static final //
  ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto SQL_ID_COLUMN_DTO = //
  new ColumnDto(PascalCaseCatalogue.ID, SqlDatatypeCatalogue.TEXT);

  //constant
  private static final //
  ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto SQL_SAVE_STAMP_COLUMN_DTO = //
  new ColumnDto(PascalCaseCatalogue.SAVE_STAMP, SqlDatatypeCatalogue.INTEGER);

  //method
  public ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto createQslColumnDtoFrom(
    final IColumnDto column) {
    return new ColumnDto(column.getName(), SqlDatatypeCatalogue.TEXT);
  }

  //method
  public ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto createQslTableDtoFrom(
    final ITableDto table) {
    return new TableDto(TableType.ENTITY_TABLE.getQualifyingPrefix() + table.getName(), createQslColumnDtosFrom(table));
  }

  //method
  private IContainer<ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto> createQslColumnDtosFrom(
    final ITableDto table) {

    final var columns = new LinkedList<ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto>();

    columns.addAtEnd(SQL_ID_COLUMN_DTO);

    for (final var c : table.getColumns()) {
      columns.addAtEnd(createQslColumnDtoFrom(c));
    }

    columns.addAtEnd(SQL_SAVE_STAMP_COLUMN_DTO);

    return columns;
  }
}
