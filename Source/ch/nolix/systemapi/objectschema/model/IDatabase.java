/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.objectcomposition.databasemanager.TableManager;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 */
public interface IDatabase extends DatabaseObject, NameHolder, TableManager<ITable> {
  IDatabase addTable(ITable table);

  IDatabase createTableWithName(String name);

  int getTableCount();
}
