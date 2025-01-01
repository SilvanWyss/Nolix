package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.systemapi.rawschemaapi.dto.TableDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasedto.TableTableRecordDto;

public final class TableTableRecordMapper {

  public TableTableRecordDto createTableSystemTableRecordFrom(final TableDto table) {
    return //
    new TableTableRecordDto(
      "'" + table.id() + "'",
      "'" + table.name() + "'");
  }
}
