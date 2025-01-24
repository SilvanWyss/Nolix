package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.rawdata.schemaviewmapper.ColumnViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmapperapi.IColumnViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;
import ch.nolix.systemapi.rawschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

public final class TableDefinitionMapper {

  private static final IColumnViewDtoMapper COLUMN_VIEW_DTO_MAPPER = new ColumnViewDtoMapper();

  public TableViewDto createTableDefinitionFrom(final TableDto table) {

    final var id = table.id();
    final var name = table.name();

    final var columnViews = //
    table.columns().toWithOneBasedIndex((i, c) -> COLUMN_VIEW_DTO_MAPPER.mapColumnDtoToColumnViewDto(c,
      FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + i));

    return new TableViewDto(id, name, columnViews);
  }
}
