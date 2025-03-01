package ch.nolix.system.sqlrawschema.schemawriter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.time.moment.IncrementalCurrentTimeCreator;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.timeapi.momentapi.IIncrementalCurrentTimeCreator;

public final class SchemaWriter implements ISchemaWriter {

  private static final IResourceValidator RESOURCE_VALIDATOR = new ResourceValidator();

  private static final IIncrementalCurrentTimeCreator INCREMENTAL_CURRENT_TIME_CREATOR = //
  new IncrementalCurrentTimeCreator();

  private final ICloseController closeController = CloseController.forElement(this);

  private final String databaseName;

  private int saveCount;

  private final SystemDataWriter systemDataWriter;

  private final InternalSchemaWriter internalSchemaWriter;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  public SchemaWriter(final String databaseName, final ISqlConnection sqlConnection) {

    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalog.DATABASE_NAME).isNotBlank();
    RESOURCE_VALIDATOR.assertIsOpen(sqlConnection);

    this.databaseName = databaseName;
    this.sqlConnection = sqlConnection;
    this.systemDataWriter = new SystemDataWriter(sqlCollector);

    final var sqlSchemaWriter = //
    ch.nolix.system.sqlschema.adapter.SchemaWriter.forDatabasNameAndSqlConnection(databaseName, sqlConnection);

    this.internalSchemaWriter = new InternalSchemaWriter(sqlSchemaWriter);

    createCloseDependencyTo(sqlConnection);
  }

  public static SchemaWriter forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new SchemaWriter(databaseName, sqlConnection);
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

      sqlConnection.executeStatement("USE " + databaseName);
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
