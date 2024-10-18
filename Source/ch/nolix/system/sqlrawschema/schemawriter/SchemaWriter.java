package ch.nolix.system.sqlrawschema.schemawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.SqlCollector;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

public final class SchemaWriter implements ISchemaWriter {

  private final CloseController closeController = CloseController.forElement(this);

  private int saveCount;

  private final SystemDataWriter systemDataWriter;

  private final InternalSchemaWriter internalSchemaWriter;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  public SchemaWriter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaWriter schemaWriter) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;
    systemDataWriter = new SystemDataWriter(sqlCollector);
    internalSchemaWriter = new InternalSchemaWriter(schemaWriter);

    createCloseDependencyTo(sqlConnection);
    sqlConnection.executeStatement("USE " + databaseName);
  }

  public static SchemaWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaAdapter schemaAdapter) {
    return new SchemaWriter(
      databaseName,
      sqlConnectionPool.borrowResource(),
      schemaAdapter);
  }

  @Override
  public void addColumn(final String tableName, final IColumnDto column) {
    systemDataWriter.addColumn(tableName, column);
    internalSchemaWriter.addColumn(tableName, column);
  }

  @Override
  public void addTable(final ITableDto table) {
    systemDataWriter.addTable(table);
    internalSchemaWriter.addTable(table);
  }

  @Override
  public void deleteColumn(final String tableName, final String columnName) {
    systemDataWriter.deleteColumn(tableName, columnName);
    internalSchemaWriter.deleteColumn(tableName, columnName);
  }

  @Override
  public void deleteTable(final String tableName) {
    systemDataWriter.deleteTable(tableName);
    internalSchemaWriter.deleteTable(tableName);
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
  public boolean hasChanges() {
    return (systemDataWriter.hasChanges() || internalSchemaWriter.hasChanges());
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void reset() {
    sqlCollector.clear();
    internalSchemaWriter.reset();
  }

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

  @Override
  public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
    systemDataWriter.setColumnName(tableName, columnName, newColumnName);
    internalSchemaWriter.setColumnName(tableName, columnName, newColumnName);
  }

  @Override
  public void setColumnParameterizedFieldType(
    final String columnId,
    final IParameterizedFieldTypeDto parameterizedFieldType) {
    systemDataWriter.setColumnParameterizedFieldType(columnId, parameterizedFieldType);
  }

  @Override
  public void setTableName(final String tableName, final String newTableName) {
    systemDataWriter.setTableName(tableName, newTableName);
    internalSchemaWriter.setTableName(tableName, newTableName);
  }
}
