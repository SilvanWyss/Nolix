/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.statementcreator;

import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public interface IDatabasePropertiesStatementCreator {
  /**
   * @param schemaTimestamp
   * @return a statement to set the given schemaTimestamp
   */
  String createStatementToSetSchemaTimestamp(ITime schemaTimestamp);
}
