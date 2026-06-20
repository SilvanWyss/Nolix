/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

/**
 * @author Silvan Wyss
 */
public interface IDatabase extends IDatabaseObject, NameHolder {
  IDatabase addTable(ITable table);

  IDatabase createTableWithName(String name);

  IWellOrderContainer<ITable> getStoredTables();

  int getTableCount();
}
