/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.statementcreator;

import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public interface IDatabasePropertiesStatementCreator {
  String createStatementToSetSchemaTimestamp(ITime schemaTimestamp);
}
