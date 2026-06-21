/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.sqlmidschema.sqlschemamodelmapper.ISqlSchemaColumnDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class SqlSchemaColumnDtoMapper implements ISqlSchemaColumnDtoMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<ch.nolix.systemapi.sqlschema.model.ColumnDto> mapColumnDtoToSqlSchemaColumnDtos(
    final ColumnDto columnDto) {
    final IArrayList<ch.nolix.systemapi.sqlschema.model.ColumnDto> sqlSchemaColumnDtos = //
    ArrayList.withInitialCapacity(2);

    final var mainSqlSchemaColumnDto = SqlSchemaColumnDtoMapperHelper.mapColumnDtoToMainSqlSchemaColumnDto(columnDto);
    final var fieldType = columnDto.fieldType();

    sqlSchemaColumnDtos.addAtEnd(mainSqlSchemaColumnDto);

    switch (fieldType) {
      case REFERENCE, OPTIONAL_REFERENCE, BACK_REFERENCE, OPTIONAL_BACK_REFERENCE:
        final var tableSqlSchemaColumnDto = //
        SqlSchemaColumnDtoMapperHelper.mapColumnDtoToTableSqlSchemaColumnDto(columnDto);

        sqlSchemaColumnDtos.addAtEnd(tableSqlSchemaColumnDto);
        break;
      default:
    }

    return sqlSchemaColumnDtos;
  }
}
