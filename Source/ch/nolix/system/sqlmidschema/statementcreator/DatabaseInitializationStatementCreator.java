/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.systemapi.midschema.databasestructure.DatabaseProperty;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.MetaDataTable;
import ch.nolix.systemapi.sqlmidschema.statementcreator.IDatabaseInitializationStatementCreator;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class DatabaseInitializationStatementCreator implements IDatabaseInitializationStatementCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToCreateSchemaTimestampEntry(final ITime schemaTimestamp) {
    return //
    "INSERT INTO "
    + MetaDataTable.DATABASE_PROPERTY
    + " ("
    + DatabasePropertyColumn.KEY
    + ", "
    + DatabasePropertyColumn.VALUE
    + ") VALUES ("
    + StringTool.getInSingleQuotes(DatabaseProperty.SCHEMA_TIMESTAMP)
    + ", '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "');";
  }
}
