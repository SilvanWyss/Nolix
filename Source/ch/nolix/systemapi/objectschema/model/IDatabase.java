package ch.nolix.systemapi.objectschema.model;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IDatabase extends IDatabaseObject, INameHolder {
  IDatabase addTable(ITable table);

  IDatabase createTableWithName(String name);

  IContainer<ITable> getStoredTables();

  int getTableCount();
}
