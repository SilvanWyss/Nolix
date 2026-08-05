/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelmutationexaminer;

import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseMutationExaminer {
  /**
   * @param database
   * @return true if the given database can add a table, false otherwise
   */
  boolean canAddTable(IDatabase database);

  /**
   * 
   * @param database
   * @param table
   * @return true if the given database can add the given table, false otherwise
   */
  boolean canAddTable(IDatabase database, ITable table);
}
