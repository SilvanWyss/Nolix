package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.sqlrawdata.schemainfo.TableInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

public final class TableDefinitionMapper {

  private static final ColumnDefinitionMapper COLUMN_DEFINITION_MAPPER = new ColumnDefinitionMapper();

  public ITableInfo createTableDefinitionFrom(final ITableDto table) {
    return new TableInfo(
      table.getId(),
      table.getName(),
      table.getColumns().to(COLUMN_DEFINITION_MAPPER::createColumnDefinitionFrom));
  }
}
