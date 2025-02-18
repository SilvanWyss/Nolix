package ch.nolix.system.sqlrawschema.sqlschemamodelmapper;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.system.sqlrawschema.sqlschemadtocatalog.EntitySqlSchemaColumnDtoCatalog;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableNameQualifyingPrefix;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemamodelmapperapi.ISqlSchemaColumnDtoMapper;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemamodelmapperapi.ISqlSchemaTableDtoMapper;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-02
 */
public final class SqlSchemaTableDtoMapper implements ISqlSchemaTableDtoMapper {

  private static final ImmutableList<ColumnDto> META_COLUMN_SQL_SCHEMA_COLUMNS = //
  ImmutableList.withElement(
    EntitySqlSchemaColumnDtoCatalog.ID_COLUMN_DTO,
    EntitySqlSchemaColumnDtoCatalog.SAVE_STAMP_COLUMN_DTO);

  private static final ISqlSchemaColumnDtoMapper SQL_SCHEMA_COLUMN_DTO_MAPPER = new SqlSchemaColumnDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto mapTableDtoSqlSchemaTableDto(final TableDto tableDto) {

    final var tableName = TableNameQualifyingPrefix.E + tableDto.name();
    final var contentColumns = tableDto.columns().to(SQL_SCHEMA_COLUMN_DTO_MAPPER::mapColumnDtoToSqlSchemaColumnDto);
    final var columns = ContainerView.forIterable(META_COLUMN_SQL_SCHEMA_COLUMNS, contentColumns);

    return new ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto(tableName, columns);
  }
}
