//package declaration
package ch.nolix.system.sqlrawdata.databaseinspector;

//own imports
import ch.nolix.system.sqlrawdata.schemainfo.TableInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
public final class TableDefinitionMapper {

  //constant
  private static final ColumnDefinitionMapper COLUMN_DEFINITION_MAPPER = new ColumnDefinitionMapper();

  //method
  public ITableInfo createTableDefinitionFrom(final ITableDto table) {
    return new TableInfo(
      table.getId(),
      table.getName(),
      table.getColumns().to(COLUMN_DEFINITION_MAPPER::createColumnDefinitionFrom));
  }
}
