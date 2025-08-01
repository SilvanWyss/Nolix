package ch.nolix.system.sqlmidschema.schemawriter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.sql.sqltool.ISqlCollector;
import ch.nolix.system.sqlmidschema.statementcreator.DatabasePropertiesStatementCreator;
import ch.nolix.system.sqlmidschema.statementcreator.MetaDataStatementCreator;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.sqlmidschema.statementcreator.IDatabasePropertiesStatementCreator;
import ch.nolix.systemapi.sqlmidschema.statementcreator.IMetaDataStatementCreator;
import ch.nolix.systemapi.time.moment.ITime;

public final class MetaDataWriter {

  private static final IDatabasePropertiesStatementCreator DATABASE_PROPERTIES_STATEMENT_CREATOR = //
  new DatabasePropertiesStatementCreator();

  private static final IMetaDataStatementCreator META_DATA_STATEMENT_CREATOR = new MetaDataStatementCreator();

  private final ISqlCollector sqlCollector;

  private MetaDataWriter(final ISqlCollector sqlCollector) {

    Validator.assertThat(sqlCollector).thatIsNamed(SqlCollector.class).isNotNull();

    this.sqlCollector = sqlCollector;
  }

  public static MetaDataWriter forSqlCollector(final ISqlCollector sqlCollector) {
    return new MetaDataWriter(sqlCollector);
  }

  public void addColumn(final String tableName, final ColumnDto column) {

    final var statement = META_DATA_STATEMENT_CREATOR.createStatementToAddColumn(tableName, column);

    sqlCollector.addSqlStatement(statement);
  }

  public void deleteColumn(String tableName, String columnName) {

    final var statement = META_DATA_STATEMENT_CREATOR.createStatementToDeleteColumn(tableName, columnName);

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

  public void setContentModel(final String tableName, final String columnName, final IContentModelDto contentModel) {

    final var statement = //
    META_DATA_STATEMENT_CREATOR.createStatementToSetContentModel(tableName, columnName, contentModel);

    sqlCollector.addSqlStatement(statement);
  }

  public void setSchemaTimestamp(ITime schemaTimestamp) {

    final var statement = DATABASE_PROPERTIES_STATEMENT_CREATOR.createStatementToSetSchemaTimestamp(schemaTimestamp);

    sqlCollector.addSqlStatement(statement);
  }
}
