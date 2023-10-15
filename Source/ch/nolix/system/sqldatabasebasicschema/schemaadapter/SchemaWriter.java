//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemaadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SqlCollector;
import ch.nolix.core.sql.SqlConnection;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.sqlsyntaxapi.ISchemaStatementCreator;

//class
public final class SchemaWriter implements ISchemaWriter {

  // static method
  public static SchemaWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaStatementCreator(
      final String databaseName,
      final SqlConnectionPool sqlConnectionPool,
      final ISchemaStatementCreator schemaStatementCreator) {
    return new SchemaWriter(databaseName, sqlConnectionPool.borrowSqlConnection(), schemaStatementCreator);
  }

  // attribute
  private int saveCount;

  // attribute
  private final SqlCollector sqlCollector = new SqlCollector();

  // attribute
  private final SqlConnection sqlConnection;

  // attribute
  private final ISchemaStatementCreator schemaStatementCreator;

  // attribute
  private final CloseController closeController = CloseController.forElement(this);

  // constructor
  private SchemaWriter(
      final String databaseName,
      final SqlConnection sqlConnection,
      final ISchemaStatementCreator schemaStatementCreator) {

    GlobalValidator.assertThat(schemaStatementCreator).thatIsNamed(ISchemaStatementCreator.class).isNotNull();

    this.sqlConnection = sqlConnection;
    this.schemaStatementCreator = schemaStatementCreator;

    getStoredCloseController().createCloseDependencyTo(sqlConnection);
    sqlCollector.addSqlStatement("USE " + databaseName);
  }

  // method
  @Override
  public void addColumn(final String tableName, final IColumnDto column) {
    sqlCollector.addSqlStatement(schemaStatementCreator.createStatementToAddColumn(tableName, column));
  }

  // method
  @Override
  public void addTable(final ITableDto table) {
    sqlCollector.addSqlStatement(schemaStatementCreator.createStatementToAddTable(table));
  }

  // method
  @Override
  public void deleteColumn(final String tableName, final String columnName) {
    sqlCollector.addSqlStatement(schemaStatementCreator.createStatementToDeleteColumn(tableName, columnName));
  }

  // method
  @Override
  public void deleteTable(final String tableName) {
    sqlCollector.addSqlStatement(schemaStatementCreator.createStatementToDeleteTable(tableName));
  }

  // method
  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  // method
  @Override
  public int getSaveCount() {
    return saveCount;
  }

  // method
  @Override
  public IContainer<String> getSqlStatements() {
    return sqlCollector.getSqlStatements();
  }

  // method
  @Override
  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  // method
  @Override
  public void noteClose() {
    // Does nothing.
  }

  // method
  @Override
  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    sqlCollector.addSqlStatement(
        schemaStatementCreator.createStatementToRenameColumn(tableName, columnName, newColumnName));
  }

  // method
  @Override
  public void renameTable(final String tableName, final String newTableName) {
    sqlCollector.addSqlStatement(schemaStatementCreator.createStatementToRenameTable(tableName, newTableName));
  }

  // method
  @Override
  public void reset() {
    sqlCollector.clear();
  }

  // method
  @Override
  public void saveChanges() {
    try {
      sqlCollector.executeUsingConnection(sqlConnection);
      saveCount++;
    } finally {
      reset();
    }
  }
}
