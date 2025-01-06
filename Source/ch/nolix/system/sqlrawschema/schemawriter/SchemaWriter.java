package ch.nolix.system.sqlrawschema.schemawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.time.moment.IncrementalCurrentTimeCreator;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.dto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaAdapter;
import ch.nolix.systemapi.timeapi.momentapi.IIncrementalCurrentTimeCreator;

public final class SchemaWriter implements ISchemaWriter {

  private static final IIncrementalCurrentTimeCreator INCREMENTAL_CURRENT_TIME_CREATOR = //
  new IncrementalCurrentTimeCreator();

  private final ICloseController closeController = CloseController.forElement(this);

  private int saveCount;

  private final SystemDataWriter systemDataWriter;

  private final InternalSchemaWriter internalSchemaWriter;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  public SchemaWriter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaWriter schemaWriter) {

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
  public void addColumn(final String tableName, final ColumnDto column) {
    systemDataWriter.addColumn(tableName, column);
    internalSchemaWriter.addColumn(tableName, column);
  }

  @Override
  public void addTable(final TableDto table) {
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
  public ICloseController getStoredCloseController() {
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

      systemDataWriter.setSchemaTimestamp(INCREMENTAL_CURRENT_TIME_CREATOR.getCurrentTime());
      sqlCollector.addSqlStatements(internalSchemaWriter.getSqlStatements());
      sqlCollector.executeAndClearUsingConnection(sqlConnection);

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
  public void setColumnContentModel(
    final String columnId,
    final IContentModelDto contentModel) {
    systemDataWriter.setColumnContentModel(columnId, contentModel);
  }

  @Override
  public void setTableName(final String tableName, final String newTableName) {
    systemDataWriter.setTableName(tableName, newTableName);
    internalSchemaWriter.setTableName(tableName, newTableName);
  }
}
