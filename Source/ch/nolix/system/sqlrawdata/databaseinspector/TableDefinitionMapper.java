package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.sqlrawdata.schemaview.TableInfo;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;

public final class TableDefinitionMapper {

  private static final ColumnDefinitionMapper COLUMN_DEFINITION_MAPPER = new ColumnDefinitionMapper();

  public ITableInfo createTableDefinitionFrom(final TableDto table) {
    return new TableInfo(
      table.id(),
      table.name(),
      table.columns().to(COLUMN_DEFINITION_MAPPER::createColumnDefinitionFrom));
  }
}
