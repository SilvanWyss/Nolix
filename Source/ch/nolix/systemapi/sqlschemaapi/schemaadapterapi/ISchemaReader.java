//package declaration
package ch.nolix.systemapi.sqlschemaapi.schemaadapterapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.sqlschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

//interface
public interface ISchemaReader extends GroupCloseable {

  //method declaration
  boolean columnsIsEmpty(String tableName, String columnName);

  //method declaration
  IContainer<IColumnDto> loadColumns(String tableName);

  //method declaration
  IContainer<IFlatTableDto> loadFlatTables();

  //method declaration
  IContainer<ITableDto> loadTables();

  //method declaration
  boolean tableExists(String tableName);
}
