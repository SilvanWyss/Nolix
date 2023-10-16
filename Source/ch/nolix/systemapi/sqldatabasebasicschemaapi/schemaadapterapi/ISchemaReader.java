//package declaration
package ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDto;

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
