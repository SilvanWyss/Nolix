package ch.nolix.system.sqlmidschema.databaseinitializer;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.ColumnTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.ContentModelTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.DatabasePropertyTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.EntityIndexTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.MultiBackReferenceEntryTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.MultiReferenceEntryTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.MultiValueEntryTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.TableTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.statementcreator.DatabaseInitializationStatementCreator;
import ch.nolix.systemapi.sqlmidschemaapi.databaseinitializerapi.IDatabaseInitializerTool;
import ch.nolix.systemapi.sqlmidschemaapi.statementcreatorapi.IDatabaseInitializationStatementCreator;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaWriter;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2025-01-18
 */
public final class DatabaseInitializerTool implements IDatabaseInitializerTool {

  private static final IDatabaseInitializationStatementCreator DATABASE_INITIALIZER_SQL_STATEMENT_CREATOR = //
  new DatabaseInitializationStatementCreator();

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeFixTables(final ISchemaWriter schemaWriter) {

    //Adds database property table.
    schemaWriter.addTable(DatabasePropertyTableSqlSchemaDtoCatalog.DATABASE_PROPERTY_TABLE_SQL_DTO);

    //Adds schema tables.
    schemaWriter.addTable(TableTableSqlSchemaDtoCatalog.TABLE_TABLE_SQL_DTO);
    schemaWriter.addTable(ColumnTableSqlSchemaDtoCatalog.COLUMN_TABLE_SQL_DTO);
    schemaWriter.addTable(ContentModelTableSqlSchemaDtoCatalog.CONTENT_MODEL_TABLE_SQL_DTO);

    //Adds entity index table.
    schemaWriter.addTable(EntityIndexTableSqlSchemaDtoCatalog.ENTITY_INDEX_SQL_SCHEMA_TABLE_DTO);

    //Adds multi-entry tables.
    schemaWriter.addTable(MultiValueEntryTableSqlSchemaDtoCatalog.MULTI_VALUE_ENTRY_TABLE_SQL_DTO);
    schemaWriter.addTable(MultiReferenceEntryTableSqlSchemaDtoCatalog.MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO);
    schemaWriter.addTable(MultiBackReferenceEntryTableSqlSchemaDtoCatalog.MULTI_BACK_REFERENCE_ENTRY_TABLE_SQL_DTO);

    //Save the changes to the database.
    schemaWriter.saveChanges();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveSchemaTimestamp(
    final ITime schemaTimestamp,
    final ISqlConnection sqlConnection,
    final String databaseName) {

    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalog.DATABASE_NAME).isNotBlank();

    final var query = //
    DATABASE_INITIALIZER_SQL_STATEMENT_CREATOR.createStatementToCreateSchemaTimestampEntry(schemaTimestamp);

    sqlConnection.executeStatement("USE " + databaseName);

    sqlConnection.executeStatement(query);
  }
}
