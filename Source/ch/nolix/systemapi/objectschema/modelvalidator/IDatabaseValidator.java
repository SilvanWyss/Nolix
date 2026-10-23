/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelvalidator;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.database.databaseobjectvalidator.DatabaseObjectValidator;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseValidator extends DatabaseObjectValidator<IDatabase> {
  void assertAllBackReferencesAreValid(IDatabase database);

  void assertCanAddGivenTable(IDatabase database, ITable table);

  void assertCanSetGivenNameToDatabase(String name);

  void assertContainsTable(IDatabase database, ITable tables);

  void assertContainsTableReferencedByGivenColumn(IDatabase database, IColumn column);

  void assertContainsTables(IDatabase database, ExtendedIterable<ITable> tables);

  void assertContainsTableWithColumnBackReferencedByGivenColumn(IDatabase database, IColumn column);

  void assertContainsTableWithGivenColumn(IDatabase database, IColumn column);

  void assertDoesNotContainTableWithGivenName(IDatabase database, String name);
}
