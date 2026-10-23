/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.statementcreator;

import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseInitializationStatementCreator {
  /**
   * @param schemaTimestamp
   * @return a statement to create the schema timestamp entry with the given
   *         schemaTimestamp
   */
  String createStatementToCreateSchemaTimestampEntry(ITime schemaTimestamp);
}
