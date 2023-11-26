//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//interface
public interface IDatabase extends IDatabaseObject, INameHolder {

  //method declaration
  IDatabase addTable(ITable table);

  //method declaration
  IDatabase createTableWithName(String name);

  //method declaration
  IContainer<ITable> getStoredTables();

  //method declaration
  int getTableCount();

  //method declaration
  void setRawSchemaAdapter(ISchemaAdapter rawSchemaAdapter);
}
