package ch.nolix.system.sqlschema.schemaadapter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.statementcreatorapi.IStatementCreator;

public final class SchemaWriter implements ISchemaWriter {

  private int saveCount;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  private final IStatementCreator statementCreator;

  private final ICloseController closeController = CloseController.forElement(this);

  private SchemaWriter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IStatementCreator statementCreator) {

    GlobalValidator.assertThat(statementCreator).thatIsNamed(IStatementCreator.class).isNotNull();

    this.sqlConnection = sqlConnection;
    this.statementCreator = statementCreator;

    getStoredCloseController().createCloseDependencyTo(sqlConnection);
    sqlCollector.addSqlStatement("USE " + databaseName);
  }

  public static SchemaWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaStatementCreator(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IStatementCreator statementCreator) {
    return new SchemaWriter(
      databaseName,
      sqlConnectionPool.borrowResource(),
      statementCreator);
  }

  @Override
  public void addColumn(final String tableName, final ColumnDto column) {
    sqlCollector.addSqlStatement(statementCreator.createStatementToAddColumn(tableName, column));
  }

  @Override
  public void addTable(final TableDto table) {
    sqlCollector.addSqlStatement(statementCreator.createStatementToAddTable(table));
  }

  @Override
  public void deleteColumn(final String tableName, final String columnName) {
    sqlCollector.addSqlStatement(statementCreator.createStatementToDeleteColumn(tableName, columnName));
  }

  @Override
  public void deleteTable(final String tableName) {
    sqlCollector.addSqlStatement(statementCreator.createStatementToDeleteTable(tableName));
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getSaveCount() {
    return saveCount;
  }

  @Override
  public IContainer<String> getSqlStatements() {
    return sqlCollector.getSqlStatements();
  }

  @Override
  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    sqlCollector.addSqlStatement(
      statementCreator.createStatementToRenameColumn(tableName, columnName, newColumnName));
  }

  @Override
  public void renameTable(final String tableName, final String newTableName) {
    sqlCollector.addSqlStatement(statementCreator.createStatementToRenameTable(tableName, newTableName));
  }

  @Override
  public void reset() {
    sqlCollector.clear();
  }

  @Override
  public void saveChanges() {
    try {
      sqlCollector.executeAndClearUsingConnection(sqlConnection);
      saveCount++;
    } finally {
      reset();
    }
  }
}
