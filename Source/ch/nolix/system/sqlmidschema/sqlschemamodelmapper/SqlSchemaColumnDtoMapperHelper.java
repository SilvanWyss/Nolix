package ch.nolix.system.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnConstraintDto;

public final class SqlSchemaColumnDtoMapperHelper {
  private SqlSchemaColumnDtoMapperHelper() {
  }

  public static ch.nolix.systemapi.sqlschema.model.ColumnDto mapColumnDtoToMainSqlSchemaColumnDto(
    final ColumnDto columnDto) {
    final var columnName = columnDto.name();
    final var dataType = DataTypeTypeCatalog.TEXT;
    final IContainer<ColumnConstraintDto> constraints = ImmutableList.createEmpty();

    return new ch.nolix.systemapi.sqlschema.model.ColumnDto(columnName, dataType, constraints);
  }

  public static ch.nolix.systemapi.sqlschema.model.ColumnDto mapColumnDtoToTableSqlSchemaColumnDto(
    final ColumnDto columnDto) {
    final var columnName = columnDto.name() + "Table";
    final var dataType = DataTypeTypeCatalog.TEXT;
    final IContainer<ColumnConstraintDto> constraints = ImmutableList.createEmpty();

    return new ch.nolix.systemapi.sqlschema.model.ColumnDto(columnName, dataType, constraints);
  }
}
