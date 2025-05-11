package ch.nolix.system.sqlmidschema.schemawriter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.sqlapi.sqltoolapi.ISqlCollector;
import ch.nolix.system.sqlmidschema.statementcreator.DatabasePropertiesStatementCreator;
import ch.nolix.system.sqlmidschema.statementcreator.SchemaStatementCreator;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlmidschemaapi.statementcreatorapi.IDatabasePropertiesStatementCreator;
import ch.nolix.systemapi.sqlmidschemaapi.statementcreatorapi.ISchemaStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class MetaDataWriter {

  private static final IDatabasePropertiesStatementCreator DATABASE_PROPERTIES_STATEMENT_CREATOR = //
  new DatabasePropertiesStatementCreator();

  private static final ISchemaStatementCreator SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR = new SchemaStatementCreator();

  private final ISqlCollector sqlCollector;

  private MetaDataWriter(final ISqlCollector sqlCollector) {

    Validator.assertThat(sqlCollector).thatIsNamed(SqlCollector.class).isNotNull();

    this.sqlCollector = sqlCollector;
  }

  public static MetaDataWriter forSqlCollector(final ISqlCollector sqlCollector) {
    return new MetaDataWriter(sqlCollector);
  }

  public void addColumn(final String tableName, final ColumnDto column) {

    final var statement = SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToAddColumn(tableName, column);

    sqlCollector.addSqlStatement(statement);
  }

  public void deleteColumn(String tableName, String columnName) {

    final var statement = SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToDeleteColumn(tableName, columnName);

    sqlCollector.addSqlStatement(statement);
  }

  public void addTable(final TableDto table) {

    final var statements = SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementsToAddTable(table);

    sqlCollector.addSqlStatements(statements);
  }

  public void deleteTable(final String tableName) {

    final var statement = SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToDeleteTable(tableName);

    sqlCollector.addSqlStatement(statement);
  }

  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {

    final var statement = //
    SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToRenameColumn(tableName, columnName, newColumnName);

    sqlCollector.addSqlStatement(statement);
  }

  public void renameTable(final String tableName, final String newTableName) {

    final var statement = //
    SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToRenameTable(tableName, newTableName);

    sqlCollector.addSqlStatement(statement);
  }

  public void setContentModel(final String tableName, final String columnName, final IContentModelDto contentModel) {

    final var statement = //
    SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToSetContentModel(tableName, columnName, contentModel);

    sqlCollector.addSqlStatement(statement);
  }

  public void setSchemaTimestamp(ITime schemaTimestamp) {

    final var statement = DATABASE_PROPERTIES_STATEMENT_CREATOR.createStatementToSetSchemaTimestamp(schemaTimestamp);

    sqlCollector.addSqlStatement(statement);
  }
}
