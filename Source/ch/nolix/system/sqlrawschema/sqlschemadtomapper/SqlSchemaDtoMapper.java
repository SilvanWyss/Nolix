package ch.nolix.system.sqlrawschema.sqlschemadtomapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.system.sqlrawschema.sqlschemamodelmapper.SqlSchemaColumnDtoMapper;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableNameQualifyingPrefix;
import ch.nolix.systemapi.sqlrawschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemamodelmapperapi.ISqlSchemaColumnDtoMapper;

public final class SqlSchemaDtoMapper {

  private static final ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto SQL_ID_COLUMN_DTO = //
  ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto.withNameAndDataType(
    PascalCaseVariableCatalog.ID,
    DataTypeTypeCatalog.TEXT);

  private static final ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto SQL_SAVE_STAMP_COLUMN_DTO = //
  ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto.withNameAndDataType(
    PascalCaseVariableCatalog.SAVE_STAMP,
    DataTypeTypeCatalog.INTEGER);

  private static final ISqlSchemaColumnDtoMapper SQL_SCHEMA_COLUMN_DTO_MAPPER = new SqlSchemaColumnDtoMapper();

  public ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto mapTableDtoToSqlSchemaTableDto(final TableDto table) {
    return //
    new ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto(
      TableNameQualifyingPrefix.E + table.name(),
      createSqlColumnDtosFrom(table));
  }

  private IContainer<ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto> createSqlColumnDtosFrom(final TableDto table) {

    final ILinkedList<ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto> columns = LinkedList.createEmpty();

    columns.addAtEnd(SQL_ID_COLUMN_DTO);

    for (final var c : table.columns()) {

      final var column = SQL_SCHEMA_COLUMN_DTO_MAPPER.mapColumnDtoToSqlSchemaColumnDto(c);

      columns.addAtEnd(column);
    }

    columns.addAtEnd(SQL_SAVE_STAMP_COLUMN_DTO);

    return columns;
  }
}
