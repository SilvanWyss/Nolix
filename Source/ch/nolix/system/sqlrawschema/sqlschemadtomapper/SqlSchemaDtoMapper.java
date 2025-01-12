package ch.nolix.system.sqlrawschema.sqlschemadtomapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableType;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;

public final class SqlSchemaDtoMapper {

  private static final ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto SQL_ID_COLUMN_DTO = //
  ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto.withNameAndDataType(
    PascalCaseVariableCatalog.ID,
    DataTypeTypeCatalog.TEXT);

  private static final ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto SQL_SAVE_STAMP_COLUMN_DTO = //
  ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto.withNameAndDataType(
    PascalCaseVariableCatalog.SAVE_STAMP,
    DataTypeTypeCatalog.INTEGER);

  public ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto mapColumnDtoToSqlColumnDto(final ColumnDto column) {
    return ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto.withNameAndDataType(column.name(), DataTypeTypeCatalog.TEXT);
  }

  public ch.nolix.systemapi.sqlschemaapi.dto.TableDto mapTableDtoToSqlSchemaTableDto(final TableDto table) {
    return //
    new ch.nolix.systemapi.sqlschemaapi.dto.TableDto(
      TableType.ENTITY_TABLE.getQualifyingPrefix() + table.name(),
      createSqlColumnDtosFrom(table));
  }

  private IContainer<ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto> createSqlColumnDtosFrom(final TableDto table) {

    final ILinkedList<ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto> columns = LinkedList.createEmpty();

    columns.addAtEnd(SQL_ID_COLUMN_DTO);

    for (final var c : table.columns()) {
      columns.addAtEnd(mapColumnDtoToSqlColumnDto(c));
    }

    columns.addAtEnd(SQL_SAVE_STAMP_COLUMN_DTO);

    return columns;
  }
}
