//package declaration
package ch.nolix.system.sqldatabaserawschema.schemawriter;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDto;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDto;
import ch.nolix.system.sqldatabaserawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.system.sqldatabaserawschema.structure.TableType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
final class SchemaDtoMapper {

  // constant
  private static final ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto SQL_ID_COLUMN_DTO = new ColumnDto(
      PascalCaseCatalogue.ID, SqlDatatypeCatalogue.TEXT);

  // constant
  private static final ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto SQL_SAVE_STAMP_COLUMN_DTO = new ColumnDto(
      PascalCaseCatalogue.SAVE_STAMP, SqlDatatypeCatalogue.INTEGER);

  // method
  public ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto createQslColumnDtoFrom(
      final IColumnDto column) {
    return new ColumnDto(column.getName(), SqlDatatypeCatalogue.TEXT);
  }

  // method
  public ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDto createQslTableDtoFrom(
      final ITableDto table) {
    return new TableDto(TableType.ENTITY_TABLE.getNamePrefix() + table.getName(), createQslColumnDtosFrom(table));
  }

  // method
  private IContainer<ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto> createQslColumnDtosFrom(
      final ITableDto table) {

    final var columns = new LinkedList<ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto>();

    columns.addAtEnd(SQL_ID_COLUMN_DTO);

    for (final var c : table.getColumns()) {
      columns.addAtEnd(createQslColumnDtoFrom(c));
    }

    columns.addAtEnd(SQL_SAVE_STAMP_COLUMN_DTO);

    return columns;
  }
}
