package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.midschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.statementcreatorapi.IDatabasePropertiesStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DatabasePropertiesStatementCreator implements IDatabasePropertiesStatementCreator {

  @Override
  public String createStatementToSetSchemaTimestamp(final ITime schemaTimestamp) {
    return //
    "UPDATE "
    + FixTable.DATABASE_PROPERTY.getName()
    + SpaceEnclosedSqlKeywordCatalog.SET
    + DatabasePropertyTableColumn.VALUE.getName()
    + " = '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "' WHERE "
    + DatabasePropertyTableColumn.KEY.getName()
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getNameInQuotes();
  }
}
