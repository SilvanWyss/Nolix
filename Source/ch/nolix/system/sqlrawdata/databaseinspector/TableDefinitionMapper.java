package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.rawdata.schemaviewmapper.ColumnViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmapperapi.IColumnViewDtoMapper;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;

public final class TableDefinitionMapper {

  private static final IColumnViewDtoMapper COLUMN_VIEW_DTO_MAPPER = new ColumnViewDtoMapper();

  public TableViewDto createTableDefinitionFrom(final TableDto table) {
    return new TableViewDto(
      table.id(),
      table.name(),
      table.columns().toWithOneBasedIndex((i, c) -> COLUMN_VIEW_DTO_MAPPER.mapColumnDtoToColumnViewDto(c, i)));
  }
}
