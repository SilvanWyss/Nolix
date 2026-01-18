/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.databaseinitializer;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.BackReferenceableColumnTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.ColumnTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.DatabasePropertyTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.EntityIndexTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.MultiBackReferenceEntryTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.MultiReferenceEntryTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.MultiValueEntryTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.ReferenceableTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.sqlschemadtocatalog.TableTableSqlSchemaDtoCatalog;
import ch.nolix.system.sqlmidschema.statementcreator.DatabaseInitializationStatementCreator;
import ch.nolix.systemapi.sqlmidschema.databaseinitializer.IDatabaseInitializerTool;
import ch.nolix.systemapi.sqlmidschema.statementcreator.IDatabaseInitializationStatementCreator;
import ch.nolix.systemapi.sqlschema.adapter.ISchemaWriter;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * A {@link DatabaseInitializerTool} is not mutable.
 * 
 * @author Silvan Wyss
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
    schemaWriter.addTables(
      TableTableSqlSchemaDtoCatalog.TABLE_TABLE_SQL_DTO,
      ColumnTableSqlSchemaDtoCatalog.COLUMN_TABLE_SQL_DTO,
      ReferenceableTableSqlSchemaDtoCatalog.REFERENCEABLE_TABLE_TABLE_SQL_DTO,
      BackReferenceableColumnTableSqlSchemaDtoCatalog.BACK_REFERENCEABLE_COLUMN_TABLE_SQL_DTO);

    //Adds entity index table.
    schemaWriter.addTable(EntityIndexTableSqlSchemaDtoCatalog.ENTITY_INDEX_SQL_SCHEMA_TABLE_DTO);

    //Adds multi-entry tables.
    schemaWriter.addTables(
      MultiValueEntryTableSqlSchemaDtoCatalog.MULTI_VALUE_ENTRY_TABLE_SQL_DTO,
      MultiReferenceEntryTableSqlSchemaDtoCatalog.MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO,
      MultiBackReferenceEntryTableSqlSchemaDtoCatalog.MULTI_BACK_REFERENCE_ENTRY_TABLE_SQL_DTO);

    //Saves the changes to the database.
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

    sqlConnection.executeStatement("USE " + databaseName + ";");

    sqlConnection.executeStatement(query);
  }
}
