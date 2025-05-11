package ch.nolix.system.middata.midschemaviewmapper;

import ch.nolix.systemapi.middataapi.midschemaview.TableViewDto;
import ch.nolix.systemapi.middataapi.midschemaviewmapperapi.IColumnViewDtoMapper;
import ch.nolix.systemapi.middataapi.midschemaviewmapperapi.ITableViewDtoMapper;
import ch.nolix.systemapi.midschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;

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
  public TableViewDto mapMidSchemaTableDtoToTableViewDto(final TableDto table) {

    final var id = table.id();
    final var name = table.name();
    final var columns = table.columns();

    final var columnViews = //
    columns
      .toWithOneBasedIndex((i, c) -> //
      COLUMN_VIEW_DTO_MAPPER.mapMidSchemaColumnDtoToColumnViewDto(
        c,
        FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + i));

    return new TableViewDto(id, name, columnViews);
  }
}
