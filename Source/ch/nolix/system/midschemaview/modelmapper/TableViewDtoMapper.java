package ch.nolix.system.midschemaview.modelmapper;

import ch.nolix.systemapi.midschema.databasestructure.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;
import ch.nolix.systemapi.midschemaview.modelmapper.IColumnViewDtoMapper;
import ch.nolix.systemapi.midschemaview.modelmapper.ITableViewDtoMapper;

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
