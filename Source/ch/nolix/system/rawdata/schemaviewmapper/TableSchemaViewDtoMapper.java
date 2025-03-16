package ch.nolix.system.rawdata.schemaviewmapper;

import ch.nolix.systemapi.midschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmapperapi.IColumnSchemaViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmapperapi.ITableSchemaViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-24
 */
public final class TableSchemaViewDtoMapper implements ITableSchemaViewDtoMapper {

  private static final IColumnSchemaViewDtoMapper COLUMN_VIEW_DTO_MAPPER = new ColumnSchemaViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableSchemaViewDto mapTableDtoToTableSchemaViewDto(final TableDto table) {

    final var id = table.id();
    final var name = table.name();
    final var columns = table.columns();

    final var columnViews = //
    columns
      .toWithOneBasedIndex((i, c) -> //
      COLUMN_VIEW_DTO_MAPPER.mapColumnDtoToColumnSchemaViewDto(
        c,
        FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + i));

    return new TableSchemaViewDto(id, name, columnViews);
  }
}
