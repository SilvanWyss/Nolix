package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

public interface IDatabase extends IDatabaseObject, INameHolder {

  IDatabase addTable(ITable table);

  IDatabase createTableWithName(String name);

  IContainer<ITable> getStoredTables();

  int getTableCount();
}
