package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.sqlrawdata.schemaview.TableView;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;

public final class TableDefinitionMapper {

  private static final ColumnDefinitionMapper COLUMN_DEFINITION_MAPPER = new ColumnDefinitionMapper();

  public ITableView createTableDefinitionFrom(final TableDto table) {
    return new TableView(
      table.id(),
      table.name(),
      table.columns().to(COLUMN_DEFINITION_MAPPER::createColumnDefinitionFrom));
  }
}
