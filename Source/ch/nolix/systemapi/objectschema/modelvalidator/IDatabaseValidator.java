/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelvalidator;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseValidator {
  void assertContainsTable(IDatabase database, ITable tables);

  void assertContainsTables(IDatabase database, IContainer<ITable> tables);

  void assertAllBackReferencesAreValid(IDatabase database);

  void assertCanAddGivenTable(IDatabase database, ITable table);

  void assertCanSetGivenNameToDatabase(String name);

  void assertContainsTableReferencedByGivenColumn(IDatabase database, IColumn column);

  void assertContainsTableWithColumnBackReferencedByGivenColumn(IDatabase database, IColumn column);

  void assertContainsTableWithGivenColumn(IDatabase database, IColumn column);

  void assertDoesNotContainTableWithGivenName(IDatabase database, String name);
}
