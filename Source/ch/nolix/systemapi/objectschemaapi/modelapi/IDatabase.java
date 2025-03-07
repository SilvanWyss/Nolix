package ch.nolix.systemapi.objectschemaapi.modelapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IDatabase extends IDatabaseObject, INameHolder {

  IDatabase addTable(ITable table);

  IDatabase createTableWithName(String name);

  IContainer<ITable> getStoredTables();

  int getTableCount();
}
