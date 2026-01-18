/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelmutationexaminer;

import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableMutationExaminer {
  /**
   * @param table
   * @return true if the given table can be added to a {@link IDatabase}, false
   *         otherwise.
   */
  boolean canBeAddedToDatabase(ITable table);
}
