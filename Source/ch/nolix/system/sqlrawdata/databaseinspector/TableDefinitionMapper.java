package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.rawdata.schemaviewmapper.ColumnViewDtoMapper;
import ch.nolix.system.sqlrawdata.schemaview.TableView;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;
import ch.nolix.systemapi.rawdataapi.schemaviewmapperapi.IColumnViewDtoMapper;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;

public final class TableDefinitionMapper {

  private static final IColumnViewDtoMapper COLUMN_VIEW_DTO_MAPPER = new ColumnViewDtoMapper();

  public ITableView createTableDefinitionFrom(final TableDto table) {
    return new TableView(
      table.id(),
      table.name(),
      table.columns().toWithOneBasedIndex((i, c) -> COLUMN_VIEW_DTO_MAPPER.mapColumnDtoToColumnViewDto(c, i)));
  }
}
