package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;

public final class TableTableRecordMapper {

  public TableTableRecord createTableSystemTableRecordFrom(final TableDto table) {
    return //
    new TableTableRecord(
      "'" + table.id() + "'",
      "'" + table.name() + "'");
  }
}
