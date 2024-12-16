package ch.nolix.system.sqlrawschema.schemawriter;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

final class SchemaDtoMapper {

  private static final //
  ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto SQL_ID_COLUMN_DTO = //
  ColumnDto.withNameAndDataType(PascalCaseVariableCatalogue.ID, DatatypeTypeCatalogue.TEXT);

  private static final //
  ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto SQL_SAVE_STAMP_COLUMN_DTO = //
  ColumnDto.withNameAndDataType(PascalCaseVariableCatalogue.SAVE_STAMP, DatatypeTypeCatalogue.INTEGER);

  public ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto createSqlColumnDtoFrom(
    final ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto column) {
    return ColumnDto.withNameAndDataType(column.name(), DatatypeTypeCatalogue.TEXT);
  }

  public ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto createSqlTableDtoFrom(
    final ITableDto table) {
    return //
    TableDto.withNameAndColumns(
      TableType.ENTITY_TABLE.getQualifyingPrefix() + table.getName(),
      createSqlColumnDtosFrom(table));
  }

  private IContainer<ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto> createSqlColumnDtosFrom(
    final ITableDto table) {

    final ILinkedList<ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto> columns = LinkedList.createEmpty();

    columns.addAtEnd(SQL_ID_COLUMN_DTO);

    for (final var c : table.getColumns()) {
      columns.addAtEnd(createSqlColumnDtoFrom(c));
    }

    columns.addAtEnd(SQL_SAVE_STAMP_COLUMN_DTO);

    return columns;
  }
}
