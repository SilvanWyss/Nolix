package ch.nolix.system.sqlschema.schemaadapter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi.ISchemaStatementCreator;

public final class SchemaWriter implements ISchemaWriter {

  private int saveCount;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  private final ISchemaStatementCreator schemaStatementCreator;

  private final CloseController closeController = CloseController.forElement(this);

  private SchemaWriter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ISchemaStatementCreator schemaStatementCreator) {

    GlobalValidator.assertThat(schemaStatementCreator).thatIsNamed(ISchemaStatementCreator.class).isNotNull();

    this.sqlConnection = sqlConnection;
    this.schemaStatementCreator = schemaStatementCreator;

    getStoredCloseController().createCloseDependencyTo(sqlConnection);
    sqlCollector.addSqlStatement("USE " + databaseName);
  }

  public static SchemaWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaStatementCreator(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaStatementCreator schemaStatementCreator) {
    return new SchemaWriter(
      databaseName,
      sqlConnectionPool.borrowResource(),
      schemaStatementCreator);
  }

  @Override
  public void addColumn(final String tableName, final IColumnDto column) {
    sqlCollector.addSqlStatement(schemaStatementCreator.createStatementToAddColumn(tableName, column));
  }

  @Override
  public void addTable(final ITableDto table) {
    sqlCollector.addSqlStatement(schemaStatementCreator.createStatementToAddTable(table));
  }

  @Override
  public void deleteColumn(final String tableName, final String columnName) {
    sqlCollector.addSqlStatement(schemaStatementCreator.createStatementToDeleteColumn(tableName, columnName));
  }

  @Override
  public void deleteTable(final String tableName) {
    sqlCollector.addSqlStatement(schemaStatementCreator.createStatementToDeleteTable(tableName));
  }

  @Override
  public CloseController getStoredCloseController() {
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
      schemaStatementCreator.createStatementToRenameColumn(tableName, columnName, newColumnName));
  }

  @Override
  public void renameTable(final String tableName, final String newTableName) {
    sqlCollector.addSqlStatement(schemaStatementCreator.createStatementToRenameTable(tableName, newTableName));
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
