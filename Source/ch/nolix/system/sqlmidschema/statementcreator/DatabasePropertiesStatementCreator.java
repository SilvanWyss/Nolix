/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.systemapi.midschema.databasestructure.DatabaseProperty;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.MetaDataTable;
import ch.nolix.systemapi.sqlmidschema.statementcreator.IDatabasePropertiesStatementCreator;
import ch.nolix.systemapi.time.main.ITime;

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
    + MetaDataTable.DATABASE_PROPERTY
    + " SET "
    + DatabasePropertyColumn.VALUE
    + " = '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "' WHERE "
    + DatabasePropertyColumn.KEY
    + " = "
    + StringTool.getInSingleQuotes(DatabaseProperty.SCHEMA_TIMESTAMP);
  }
}
