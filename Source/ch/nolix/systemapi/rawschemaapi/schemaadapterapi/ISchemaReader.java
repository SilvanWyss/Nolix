//package declaration
package ch.nolix.systemapi.rawschemaapi.schemaadapterapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface ISchemaReader extends GroupCloseable {

  //method declaration
  boolean columnIsEmpty(String tableName, String columnName);

  //method declaration
  int getTableCount();

  //method declaration
  IContainer<IColumnDto> loadColumnsByTableName(String tableName);

  //method declaration
  IContainer<IColumnDto> loadColumnsByTableId(String tableId);

  //method declaration
  IFlatTableDto loadFlatTableById(String id);

  //method declaration
  IFlatTableDto loadFlatTableByName(String name);

  //method declaration
  IContainer<IFlatTableDto> loadFlatTables();

  //method declaration
  ITableDto loadTableById(String id);

  //method declaration
  ITableDto loadTableByName(String name);

  //method declaration
  IContainer<ITableDto> loadTables();

  //method declaration
  ITime loadSchemaTimestamp();
}
