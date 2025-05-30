package ch.nolix.system.sqlmidschema.schemawriter;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlmidschema.sqlschemamodelmapper.SqlSchemaColumnDtoMapper;
import ch.nolix.system.sqlmidschema.sqlschemamodelmapper.SqlSchemaTableDtoMapper;
import ch.nolix.system.time.moment.IncrementalCurrentTimeCreator;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaWriter;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlmidschemaapi.sqlschemamodelmapperapi.ISqlSchemaColumnDtoMapper;
import ch.nolix.systemapi.sqlmidschemaapi.sqlschemamodelmapperapi.ISqlSchemaTableDtoMapper;
import ch.nolix.systemapi.timeapi.momentapi.IIncrementalCurrentTimeCreator;

public final class SchemaWriter implements ISchemaWriter {

  private static final ISqlSchemaTableDtoMapper SQL_SCHEMA_TABLE_DTO_MAPPER = new SqlSchemaTableDtoMapper();

  private static final ISqlSchemaColumnDtoMapper SQL_SCHEMA_COLUMN_DTO_MAPPER = new SqlSchemaColumnDtoMapper();

  private static final IIncrementalCurrentTimeCreator INCREMENTAL_CURRENT_TIME_CREATOR = new IncrementalCurrentTimeCreator();

  private final ICloseController closeController = CloseController.forElement(this);

  private final MetaDataWriter metaDataWriter;

  private final ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaWriter sqlSchemaWriter;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  private int saveCount;

  public SchemaWriter(final String databaseName, final ISqlConnection sqlConnection) {

    this.sqlConnection = sqlConnection;
    this.metaDataWriter = MetaDataWriter.forSqlCollector(sqlCollector);

    this.sqlSchemaWriter = //
    ch.nolix.system.sqlschema.adapter.SchemaWriter.forDatabasNameAndSqlConnection(databaseName, sqlConnection);

    createCloseDependencyTo(this.sqlConnection);
    createCloseDependencyTo(this.sqlSchemaWriter);
  }

  public static SchemaWriter forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new SchemaWriter(databaseName, sqlConnection);
  }

  @Override
  public void addColumn(final String tableName, final ColumnDto column) {
    metaDataWriter.addColumn(tableName, column);
    sqlSchemaWriter.addColumn(tableName, SQL_SCHEMA_COLUMN_DTO_MAPPER.mapColumnDtoToSqlSchemaColumnDto(column));
  }

  @Override
  public void addTable(final TableDto table) {
    metaDataWriter.addTable(table);
    sqlSchemaWriter.addTable(SQL_SCHEMA_TABLE_DTO_MAPPER.mapTableDtoSqlSchemaTableDto(table));
  }

  @Override
  public void deleteColumn(final String tableName, final String columnName) {
    metaDataWriter.deleteColumn(tableName, columnName);
    sqlSchemaWriter.deleteColumn(tableName, columnName);
  }

  @Override
  public void deleteTable(final String tableName) {
    metaDataWriter.deleteTable(tableName);
    sqlSchemaWriter.deleteTable(tableName);
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
    return sqlCollector.containsAny();
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    metaDataWriter.renameColumn(tableName, columnName, newColumnName);
    sqlSchemaWriter.renameColumn(tableName, columnName, newColumnName);
  }

  @Override
  public void renameTable(final String tableName, final String newTableName) {
    metaDataWriter.renameTable(tableName, newTableName);
    sqlSchemaWriter.renameTable(tableName, newTableName);
  }

  @Override
  public void reset() {
    sqlCollector.clear();
  }

  @Override
  public void saveChanges() {
    try {
      metaDataWriter.setSchemaTimestamp(INCREMENTAL_CURRENT_TIME_CREATOR.getCurrentTime());
      sqlSchemaWriter.addAdditionalSqlStatements(sqlCollector.getSqlStatements());
      sqlCollector.executeAndClearUsingConnection(sqlConnection);
      saveCount++;
    } finally {
      reset();
    }
  }

  @Override
  public void setContentModel(final String tableName, final String columnName, final IContentModelDto contentModel) {
    metaDataWriter.setContentModel(tableName, columnName, contentModel);
  }
}
