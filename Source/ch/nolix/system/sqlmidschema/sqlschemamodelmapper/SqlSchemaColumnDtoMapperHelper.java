/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.sqlmidschema.datatype.DataTypeTypeCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnConstraintDto;

/**
 * @author Silvan Wyss
 */
public final class SqlSchemaColumnDtoMapperHelper {
  private SqlSchemaColumnDtoMapperHelper() {
  }

  /**
   * @param columnDto
   * @return a new main SQL schema column DTO from the given columnDto
   */
  public static ch.nolix.systemapi.sqlschema.model.ColumnDto mapColumnDtoToMainSqlSchemaColumnDto(
    final ColumnDto columnDto) {
    final var columnName = columnDto.name();
    final var dataType = DataTypeTypeCatalog.TEXT;
    final ExtendedIterable<ColumnConstraintDto> constraints = ImmutableList.createEmpty();

    return new ch.nolix.systemapi.sqlschema.model.ColumnDto(columnName, dataType, constraints);
  }

  /**
   * @param columnDto
   * @return a new table SQL schema column DTO from the given columnDto
   */
  public static ch.nolix.systemapi.sqlschema.model.ColumnDto mapColumnDtoToTableSqlSchemaColumnDto(
    final ColumnDto columnDto) {
    final var columnName = columnDto.name() + "Table";
    final var dataType = DataTypeTypeCatalog.TEXT;
    final ExtendedIterable<ColumnConstraintDto> constraints = ImmutableList.createEmpty();

    return new ch.nolix.systemapi.sqlschema.model.ColumnDto(columnName, dataType, constraints);
  }
}
