package ch.nolix.system.sqlmidschema.schemawriter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.sql.sqltool.ISqlCollector;
import ch.nolix.system.sqlmidschema.statementcreator.DatabasePropertiesStatementCreator;
import ch.nolix.system.sqlmidschema.statementcreator.SchemaDataStatementCreator;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.sqlmidschema.statementcreator.IDatabasePropertiesStatementCreator;
import ch.nolix.systemapi.sqlmidschema.statementcreator.ISchemaDataStatementCreator;
import ch.nolix.systemapi.time.moment.ITime;

public final class SchemaDataWriter {
  private static final IDatabasePropertiesStatementCreator DATABASE_PROPERTIES_STATEMENT_CREATOR = //
  new DatabasePropertiesStatementCreator();

  private static final ISchemaDataStatementCreator META_DATA_STATEMENT_CREATOR = new SchemaDataStatementCreator();

  private final ISqlCollector sqlCollector;

  private SchemaDataWriter(final ISqlCollector sqlCollector) {
    Validator.assertThat(sqlCollector).thatIsNamed(SqlCollector.class).isNotNull();

    this.sqlCollector = sqlCollector;
  }

  public static SchemaDataWriter forSqlCollector(final ISqlCollector sqlCollector) {
    return new SchemaDataWriter(sqlCollector);
  }

  public void addColumn(final TableIdentification table, final ColumnDto column) {
    final var statements = META_DATA_STATEMENT_CREATOR.createStatementsToAddColumn(table, column);

    sqlCollector.addSqlStatements(statements);
  }

  public void deleteColumn(final TableIdentification table, final String columnName) {
    final var statement = META_DATA_STATEMENT_CREATOR.createStatementToDeleteColumn(table, columnName);

    sqlCollector.addSqlStatement(statement);
  }

  public void addTable(final TableDto table) {
    final var statements = META_DATA_STATEMENT_CREATOR.createStatementsToAddTable(table);

    sqlCollector.addSqlStatements(statements);
  }

  public void deleteTable(final String tableName) {
    final var statement = META_DATA_STATEMENT_CREATOR.createStatementToDeleteTable(tableName);

    sqlCollector.addSqlStatement(statement);
  }

  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    final var statement = //
    META_DATA_STATEMENT_CREATOR.createStatementToRenameColumn(tableName, columnName, newColumnName);

    sqlCollector.addSqlStatement(statement);
  }

  public void renameTable(final String tableName, final String newTableName) {
    final var statement = //
    META_DATA_STATEMENT_CREATOR.createStatementToRenameTable(tableName, newTableName);

    sqlCollector.addSqlStatement(statement);
  }

  public void setContentModel(
    final TableIdentification table,
    final ColumnIdentification column,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<String> referenceableTableIds,
    final IContainer<String> backReferenceableColumnIds) {
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

  public void setSchemaTimestamp(ITime schemaTimestamp) {
    final var statement = DATABASE_PROPERTIES_STATEMENT_CREATOR.createStatementToSetSchemaTimestamp(schemaTimestamp);

    sqlCollector.addSqlStatement(statement);
  }
}
