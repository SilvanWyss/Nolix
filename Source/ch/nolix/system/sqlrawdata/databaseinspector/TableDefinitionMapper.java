package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.sqlrawdata.schemainfo.TableInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;

public final class TableDefinitionMapper {

  private static final ColumnDefinitionMapper COLUMN_DEFINITION_MAPPER = new ColumnDefinitionMapper();

  public ITableInfo createTableDefinitionFrom(final TableDto table) {
    return new TableInfo(
      table.id(),
      table.name(),
      table.columns().to(COLUMN_DEFINITION_MAPPER::createColumnDefinitionFrom));
  }
}
