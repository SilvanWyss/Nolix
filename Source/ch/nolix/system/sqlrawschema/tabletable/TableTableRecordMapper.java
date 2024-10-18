package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

public final class TableTableRecordMapper {

  public TableTableRecord createTableSystemTableRecordFrom(final ITableDto table) {
    return new TableTableRecord(
      "'" + table.getId() + "'",
      "'" + table.getName() + "'");
  }
}
