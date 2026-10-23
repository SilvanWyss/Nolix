/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelexaminer;

import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseExaminer {
  /**
   * @param database
   * @return true if all base back references of the given database are valid,
   *         false otherwise
   */
  boolean allBaseBackReferencesAreValid(IDatabase database);

  /**
   * @param name
   * @return true if the given name can be set to a {@link IDatabase}, false
   *         otherwise
   */
  boolean canSetName(String name);

  /**
   * @param database
   * @param column
   * @return true if the given database contains a {@link IColumn} the given
   *         column references back, false otherwise
   */
  boolean containsBackReferencededColumnByColumn(IDatabase database, IColumn column);

  /**
   * @param database
   * @param table
   * @return true if the given database contains the given table, false otherwise
   */
  boolean containsTable(IDatabase database, ITable table);

  /**
   * @param database
   * @param column
   * @return true if the given database contains a {@link ITable} the given column
   *         references, false otherwise
   */
  boolean containsTableReferencedByColumn(IDatabase database, IColumn column);

  /**
   * @param database
   * @param column
   * @return true if the given database contains a {@link ITable} with the given
   *         column, false otherwise
   */
  boolean containsTableWithColumn(IDatabase database, IColumn column);

  /**
   * @param database
   * @param name
   * @return true if the given database contains a {@link ITable} with the given
   *         name, false otherwise
   */
  boolean containsTableWithName(IDatabase database, String name);
}
