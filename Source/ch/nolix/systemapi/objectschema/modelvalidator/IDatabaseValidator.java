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
  /**
   * @param database
   * @throws RuntimeException if not all back references of the given database are
   *                          valid
   */
  void assertAllBackReferencesAreValid(IDatabase database);

  /**
   * @param database
   * @param table
   * @throws RuntimeException if the given table cannot be added to the given
   *                          database
   */
  void assertCanAddGivenTable(IDatabase database, ITable table);

  /**
   * @param name
   * @throws RuntimeException if the given name cannot be set to a
   *                          {@link IDatabase}
   */
  void assertCanSetGivenNameToDatabase(String name);

  /**
   * @param database
   * @param tables
   * @throws RuntimeException if the given database does not contain the given
   *                          tables
   */
  void assertContainsTable(IDatabase database, ITable tables);

  /**
   * @param database
   * @param column
   * @throws RuntimeException if the given database does not contain a
   *                          {@link ITable} the given column references
   */
  void assertContainsTableReferencedByGivenColumn(IDatabase database, IColumn column);

  /**
   * @param database
   * @param tables
   * @throws RuntimeException if the given database does not contain all of the
   *                          given tables
   */
  void assertContainsTables(IDatabase database, ExtendedIterable<ITable> tables);

  /**
   * @param database
   * @param column
   * @throws RuntimeException if the given database does not contain a
   *                          {@link ITable} with the {@link IColumn} the given
   *                          column references back
   */
  void assertContainsTableWithColumnBackReferencedByGivenColumn(IDatabase database, IColumn column);

  /**
   * @param database
   * @param column
   * @throws RuntimeException if the given database does not contain a
   *                          {@link ITable} with the given column
   */
  void assertContainsTableWithGivenColumn(IDatabase database, IColumn column);

  /**
   * @param database
   * @param name
   * @throws RuntimeException if the given database contains a {@link ITable} with
   *                          the given name
   */
  void assertDoesNotContainTableWithGivenName(IDatabase database, String name);
}
