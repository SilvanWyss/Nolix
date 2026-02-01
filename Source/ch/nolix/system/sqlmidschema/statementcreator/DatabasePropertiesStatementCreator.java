/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.systemapi.midschema.databasestructure.DatabaseProperty;
import ch.nolix.systemapi.sqlmidschema.databasestructure.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.statementcreator.IDatabasePropertiesStatementCreator;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class DatabasePropertiesStatementCreator implements IDatabasePropertiesStatementCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToSetSchemaTimestamp(final ITime schemaTimestamp) {
    return //
    "UPDATE "
    + FixTable.DATABASE_PROPERTY.getName()
    + " SET "
    + DatabasePropertyColumn.VALUE.getName()
    + " = '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "' WHERE "
    + DatabasePropertyColumn.KEY.getName()
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getNameInQuotes();
  }
}
