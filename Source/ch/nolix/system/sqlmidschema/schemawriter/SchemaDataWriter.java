/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.schemawriter;

import ch.nolix.base.sql.sqltool.SqlCollector;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.sql.sqltool.ISqlCollector;
import ch.nolix.system.sqlmidschema.statementcreator.DatabasePropertiesStatementCreator;
import ch.nolix.system.sqlmidschema.statementcreator.SchemaDataStatementCreator;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class SchemaDataWriter {
  private static final DatabasePropertiesStatementCreator DATABASE_PROPERTIES_STATEMENT_CREATOR = //
  new DatabasePropertiesStatementCreator();

  private static final SchemaDataStatementCreator META_DATA_STATEMENT_CREATOR = new SchemaDataStatementCreator();

  private final ISqlCollector sqlCollector;

  private SchemaDataWriter(final ISqlCollector sqlCollector) {
    Validator.assertThat(sqlCollector).thatIsNamed(SqlCollector.class).isNotNull();

    this.sqlCollector = sqlCollector;
  }

  /**
   * @param sqlCollector
   * @return a new {@link SchemaDataWriter} that adds its statements to the given
   *         sqlCollector
   */
  public static SchemaDataWriter forSqlCollector(final ISqlCollector sqlCollector) {
    return new SchemaDataWriter(sqlCollector);
  }

  /**
   * Adds the statements to add the given column to the given table.
   * 
   * @param table
   * @param column
   */
  public void addColumn(final TableIdentification table, final ColumnDto column) {
    final var statements = META_DATA_STATEMENT_CREATOR.createStatementsToAddColumn(table, column);

    sqlCollector.addSqlStatements(statements);
  }

  /**
   * Adds the statements to add the given table.
   * 
   * @param table
   */
  public void addTable(final TableDto table) {
    final var statements = META_DATA_STATEMENT_CREATOR.createStatementsToAddTable(table);

    sqlCollector.addSqlStatements(statements);
  }

  /**
   * Adds the statement to delete the column with the given columnName from the
   * given table.
   * 
   * @param table
   * @param columnName
   */
  public void deleteColumn(final TableIdentification table, final String columnName) {
    final var statement = META_DATA_STATEMENT_CREATOR.createStatementToDeleteColumn(table, columnName);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds the statement to delete the table with the given tableName.
   * 
   * @param tableName
   */
  public void deleteTable(final String tableName) {
    final var statement = META_DATA_STATEMENT_CREATOR.createStatementToDeleteTable(tableName);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds the statement to rename the column with the given columnName of the
   * table with the given tableName to the given newColumnName.
   * 
   * @param tableName
   * @param columnName
   * @param newColumnName
   */
  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    final var statement = //
    META_DATA_STATEMENT_CREATOR.createStatementToRenameColumn(tableName, columnName, newColumnName);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds the statement to rename the table with the given tableName to the given
   * newTableName.
   * 
   * @param tableName
   * @param newTableName
   */
  public void renameTable(final String tableName, final String newTableName) {
    final var statement = //
    META_DATA_STATEMENT_CREATOR.createStatementToRenameTable(tableName, newTableName);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds the statements to set the given fieldType, dataType,
   * referenceableTableIds and backReferenceableColumnIds as content model of the
   * given column of the given table.
   * 
   * @param table
   * @param column
   * @param fieldType
   * @param dataType
   * @param referenceableTableIds
   * @param backReferenceableColumnIds
   */
  public void setContentModel(
    final TableIdentification table,
    final ColumnIdentification column,
    final FieldType fieldType,
    final DataType dataType,
    final ExtendedIterable<String> referenceableTableIds,
    final ExtendedIterable<String> backReferenceableColumnIds) {
    final var statements = //
    META_DATA_STATEMENT_CREATOR.createStatementsToSetContentModel(
      table,
      column,
      fieldType,
      dataType,
      referenceableTableIds,
      backReferenceableColumnIds);

    sqlCollector.addSqlStatements(statements);
  }

  /**
   * Adds the statement to set the given schemaTimestamp.
   * 
   * @param schemaTimestamp
   */
  public void setSchemaTimestamp(ITime schemaTimestamp) {
    final var statement = DATABASE_PROPERTIES_STATEMENT_CREATOR.createStatementToSetSchemaTimestamp(schemaTimestamp);

    sqlCollector.addSqlStatement(statement);
  }
}
