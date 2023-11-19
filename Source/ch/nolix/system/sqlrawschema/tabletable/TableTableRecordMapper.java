//package declaration
package ch.nolix.system.sqlrawschema.tabletable;

//own imports
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
public final class TableTableRecordMapper {

  //method
  public TableTableRecord createTableSystemTableRecordFrom(final ITableDto table) {
    return new TableTableRecord(
      "'" + table.getId() + "'",
      "'" + table.getName() + "'");
  }
}
