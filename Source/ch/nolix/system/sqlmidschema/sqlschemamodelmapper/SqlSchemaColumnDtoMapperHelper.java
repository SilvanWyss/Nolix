package ch.nolix.system.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlmidschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ConstraintDto;

public final class SqlSchemaColumnDtoMapperHelper {

  private SqlSchemaColumnDtoMapperHelper() {
  }

  public static ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto mapColumnDtoToMainSqlSchemaColumnDto(
    final ColumnDto columnDto) {

    final var columnName = columnDto.name();
    final var dataType = DataTypeTypeCatalog.TEXT;
    final IContainer<ConstraintDto> constraints = ImmutableList.createEmpty();

    return new ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto(columnName, dataType, constraints);
  }

  public static ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto mapColumnDtoToReferenceSqlSchemaColumnDto(
    final ColumnDto columnDto) {

    final var columnName = columnDto.name() + StringCatalog.DOLLAR + "ReferencedTableId";
    final var dataType = DataTypeTypeCatalog.TEXT;
    final IContainer<ConstraintDto> constraints = ImmutableList.createEmpty();

    return new ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto(columnName, dataType, constraints);
  }
}
