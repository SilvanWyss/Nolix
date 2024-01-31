//package declaration
package ch.nolix.system.sqlrawschema.schemawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.SqlCollector;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class SchemaWriter implements ISchemaWriter {

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //attribute
  private int saveCount;

  //attribute
  private final SystemDataWriter systemDataWriter;

  //attribute
  private final InternalSchemaWriter internalSchemaWriter;

  //attribute
  private final SqlCollector sqlCollector = new SqlCollector();

  //attribute
  private final SqlConnection sqlConnection;

  //constructor
  public SchemaWriter(
    final String databaseName,
    final SqlConnection sqlConnection,
    final ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaWriter schemaWriter) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;
    systemDataWriter = new SystemDataWriter(sqlCollector);
    internalSchemaWriter = new InternalSchemaWriter(schemaWriter);

    createCloseDependencyTo(sqlConnection);
    sqlConnection.executeStatement("USE " + databaseName);
  }

  //static method
  public static SchemaWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaAdapter schemaAdapter) {
    return new SchemaWriter(
      databaseName,
      sqlConnectionPool.borrowResource().getStoredSqlConnection(),
      schemaAdapter);
  }

  //method
  @Override
  public void addColumn(final String tableName, final IColumnDto column) {
    systemDataWriter.addColumn(tableName, column);
    internalSchemaWriter.addColumn(tableName, column);
  }

  //method
  @Override
  public void addTable(final ITableDto table) {
    systemDataWriter.addTable(table);
    internalSchemaWriter.addTable(table);
  }

  //method
  @Override
  public void deleteColumn(final String tableName, final String columnName) {
    systemDataWriter.deleteColumn(tableName, columnName);
    internalSchemaWriter.deleteColumn(tableName, columnName);
  }

  //method
  @Override
  public void deleteTable(final String tableName) {
    systemDataWriter.deleteTable(tableName);
    internalSchemaWriter.deleteTable(tableName);
  }

  //method
  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public int getSaveCount() {
    return saveCount;
  }

  //method
  @Override
  public boolean hasChanges() {
    return (systemDataWriter.hasChanges() || internalSchemaWriter.hasChanges());
  }

  //method
  @Override
  public void noteClose() {
    //Does nothing.
  }

  //method
  @Override
  public void reset() {
    sqlCollector.clear();
    internalSchemaWriter.reset();
  }

  //method
  @Override
  public void saveChanges() {
    try {

      systemDataWriter.setSchemaTimestamp(Time.ofNow());
      sqlCollector.addSqlStatements(internalSchemaWriter.getSqlStatements());
      sqlCollector.executeUsingConnection(sqlConnection);

      saveCount++;
    } finally {
      reset();
    }
  }

  //method
  @Override
  public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
    systemDataWriter.setColumnName(tableName, columnName, newColumnName);
    internalSchemaWriter.setColumnName(tableName, columnName, newColumnName);
  }

  //method
  @Override
  public void setColumnParameterizedPropertyType(
    final String columnId,
    final IParameterizedPropertyTypeDto parameterizedPropertyType) {
    systemDataWriter.setColumnParameterizedPropertyType(columnId, parameterizedPropertyType);
  }

  //method
  @Override
  public void setTableName(final String tableName, final String newTableName) {
    systemDataWriter.setTableName(tableName, newTableName);
    internalSchemaWriter.setTableName(tableName, newTableName);
  }
}
