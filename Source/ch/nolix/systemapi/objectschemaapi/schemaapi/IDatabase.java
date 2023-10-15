//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//interface
public interface IDatabase extends IDatabaseObject, Named {

  // method declaration
  IDatabase addTable(ITable table);

  // method declaration
  IDatabase createTableWithName(String name);

  // method declaration
  IContainer<ITable> getStoredTables();

  // method declaration
  int getTableCount();

  // method declaration
  void setRawSchemaAdapter(ISchemaAdapter rawSchemaAdapter);
}
