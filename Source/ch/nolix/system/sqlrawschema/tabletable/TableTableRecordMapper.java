package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.systemapi.rawschemaapi.dto.TableDto;

public final class TableTableRecordMapper {

  public TableTableRecordDto createTableSystemTableRecordFrom(final TableDto table) {
    return //
    new TableTableRecordDto(
      "'" + table.id() + "'",
      "'" + table.name() + "'");
  }
}
