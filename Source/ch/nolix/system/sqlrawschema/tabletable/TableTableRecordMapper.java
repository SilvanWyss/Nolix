package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.systemapi.rawschemaapi.dto.TableDto;
import ch.nolix.systemapi.sqlrawschemaapi.modelsqlrecord.TableTableSqlRecord;

public final class TableTableRecordMapper {

  public TableTableSqlRecord createTableSystemTableRecordFrom(final TableDto table) {
    return //
    new TableTableSqlRecord(
      "'" + table.id() + "'",
      "'" + table.name() + "'");
  }
}
