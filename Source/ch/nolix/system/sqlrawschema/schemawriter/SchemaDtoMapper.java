package ch.nolix.system.sqlrawschema.schemawriter;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.systemapi.sqlrawdataapi.databasestructure.TableType;
import ch.nolix.systemapi.sqlschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.TableDto;

final class SchemaDtoMapper {

  private static final //
  ColumnDto SQL_ID_COLUMN_DTO = //
  ColumnDto.withNameAndDataType(PascalCaseVariableCatalogue.ID, DatatypeTypeCatalogue.TEXT);

  private static final //
  ColumnDto SQL_SAVE_STAMP_COLUMN_DTO = //
  ColumnDto.withNameAndDataType(PascalCaseVariableCatalogue.SAVE_STAMP, DatatypeTypeCatalogue.INTEGER);

  public ColumnDto createSqlColumnDtoFrom(
    final ch.nolix.systemapi.rawschemaapi.dto.ColumnDto column) {
    return ColumnDto.withNameAndDataType(column.name(), DatatypeTypeCatalogue.TEXT);
  }

  public TableDto createSqlTableDtoFrom(
    final ch.nolix.systemapi.rawschemaapi.dto.TableDto table) {
    return //
    new TableDto(TableType.ENTITY_TABLE.getQualifyingPrefix() + table.name(), createSqlColumnDtosFrom(table));
  }

  private IContainer<ColumnDto> createSqlColumnDtosFrom(
    final ch.nolix.systemapi.rawschemaapi.dto.TableDto table) {

    final ILinkedList<ColumnDto> columns = LinkedList.createEmpty();

    columns.addAtEnd(SQL_ID_COLUMN_DTO);

    for (final var c : table.columns()) {
      columns.addAtEnd(createSqlColumnDtoFrom(c));
    }

    columns.addAtEnd(SQL_SAVE_STAMP_COLUMN_DTO);

    return columns;
  }
}
