package ch.nolix.system.rawdata.schemaviewmapper;

import ch.nolix.systemapi.rawdataapi.schemaviewmapperapi.IColumnViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmapperapi.ITableViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.rawschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-24
 */
public final class TableViewDtoMapper implements ITableViewDtoMapper {

  private static final IColumnViewDtoMapper COLUMN_VIEW_DTO_MAPPER = new ColumnViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableSchemaViewDto mapTableDtoToTableViewDto(final TableDto table) {

    final var id = table.id();
    final var name = table.name();
    final var columns = table.columns();

    final var columnViews = //
    columns
      .toWithOneBasedIndex((i, c) -> //
      COLUMN_VIEW_DTO_MAPPER.mapColumnDtoToColumnViewDto(
        c,
        FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + i));

    return new TableSchemaViewDto(id, name, columnViews);
  }
}
