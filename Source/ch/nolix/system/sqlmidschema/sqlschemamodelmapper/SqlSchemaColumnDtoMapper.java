package ch.nolix.system.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlmidschemaapi.datatypeapi.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlmidschemaapi.sqlschemamodelmapperapi.ISqlSchemaColumnDtoMapper;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ConstraintDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-01
 */
public final class SqlSchemaColumnDtoMapper implements ISqlSchemaColumnDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto mapColumnDtoToSqlSchemaColumnDto(
    final ColumnDto columnDto) {

    final var columnName = columnDto.name();
    final var dataType = DataTypeTypeCatalog.TEXT;
    final IContainer<ConstraintDto> constraints = ImmutableList.createEmpty();

    return new ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto(columnName, dataType, constraints);
  }
}
