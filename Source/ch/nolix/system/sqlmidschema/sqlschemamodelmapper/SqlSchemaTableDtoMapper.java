/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.EntitySqlSchemaColumnDtoCatalog;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.sqlmidschema.sqlschemamodelmapper.ISqlSchemaColumnDtoMapper;
import ch.nolix.systemapi.sqlmidschema.sqlschemamodelmapper.ISqlSchemaTableDtoMapper;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;

/**
 * @author Silvan Wyss
 */
public final class SqlSchemaTableDtoMapper implements ISqlSchemaTableDtoMapper {
  private static final ImmutableList<ColumnDto> META_COLUMN_SQL_SCHEMA_COLUMNS = //
  ImmutableList.withElements(
    EntitySqlSchemaColumnDtoCatalog.ID_COLUMN_DTO,
    EntitySqlSchemaColumnDtoCatalog.SAVE_STAMP_COLUMN_DTO);

  private static final ISqlSchemaColumnDtoMapper SQL_SCHEMA_COLUMN_DTO_MAPPER = new SqlSchemaColumnDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public ch.nolix.systemapi.sqlschema.model.TableDto mapTableDtoSqlSchemaTableDto(final TableDto tableDto) {
    final var tableName = tableDto.name();

    final var contentColumns = //
    tableDto.columns().toMultiples(SQL_SCHEMA_COLUMN_DTO_MAPPER::mapColumnDtoToSqlSchemaColumnDtos);

    final var columns = ContainerView.forIterables(META_COLUMN_SQL_SCHEMA_COLUMNS, contentColumns);

    return new ch.nolix.systemapi.sqlschema.model.TableDto(tableName, columns);
  }
}
