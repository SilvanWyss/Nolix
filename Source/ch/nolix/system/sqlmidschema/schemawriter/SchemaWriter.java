package ch.nolix.system.sqlmidschema.schemawriter;

import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlmidschema.sqlschemamodelmapper.SqlSchemaColumnDtoMapper;
import ch.nolix.system.sqlmidschema.sqlschemamodelmapper.SqlSchemaTableDtoMapper;
import ch.nolix.system.time.moment.IncrementalCurrentTimeCreator;
import ch.nolix.systemapi.midschema.adapter.ISchemaWriter;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.sqlmidschema.sqlschemamodelmapper.ISqlSchemaColumnDtoMapper;
import ch.nolix.systemapi.sqlmidschema.sqlschemamodelmapper.ISqlSchemaTableDtoMapper;
import ch.nolix.systemapi.time.moment.IIncrementalCurrentTimeCreator;

/**
 * @author Silvan Wyss
 */
public final class SchemaWriter implements ISchemaWriter {
  private static final ISqlSchemaTableDtoMapper SQL_SCHEMA_TABLE_DTO_MAPPER = new SqlSchemaTableDtoMapper();

  private static final ISqlSchemaColumnDtoMapper SQL_SCHEMA_COLUMN_DTO_MAPPER = new SqlSchemaColumnDtoMapper();

  private static final IIncrementalCurrentTimeCreator INCREMENTAL_CURRENT_TIME_CREATOR = //
  new IncrementalCurrentTimeCreator();

  private final ICloseController closeController = CloseController.forElement(this);

  private final SchemaDataWriter schemaDataWriter;

  private final ch.nolix.systemapi.sqlschema.adapter.ISchemaWriter sqlSchemaWriter;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  private int saveCount;

  public SchemaWriter(final String databaseName, final ISqlConnection sqlConnection) {
    this.sqlConnection = sqlConnection;
    this.schemaDataWriter = SchemaDataWriter.forSqlCollector(sqlCollector);

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
  public void addColumn(final TableIdentification table, final ColumnDto column) {
    schemaDataWriter.addColumn(table, column);
    sqlSchemaWriter.addColumns(table, SQL_SCHEMA_COLUMN_DTO_MAPPER.mapColumnDtoToSqlSchemaColumnDtos(column));
  }

  @Override
  public void addTable(final TableDto table) {
    schemaDataWriter.addTable(table);
    sqlSchemaWriter.addTable(SQL_SCHEMA_TABLE_DTO_MAPPER.mapTableDtoSqlSchemaTableDto(table));
  }

  @Override
  public void deleteColumn(final TableIdentification table, final String columnName) {
    final var referencedTableColumnName = columnName + StringCatalog.DOLLAR + "ReferencedTable";

    schemaDataWriter.deleteColumn(table, columnName);
    sqlSchemaWriter.deleteColumn(table.tableName(), columnName);
    sqlSchemaWriter.deleteColumnIfExists(table.tableName(), referencedTableColumnName);
  }

  @Override
  public void deleteTable(final String tableName) {
    schemaDataWriter.deleteTable(tableName);
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
    final var referencedTableColumnName = columnName + StringCatalog.DOLLAR + "ReferencedTable";

    schemaDataWriter.renameColumn(tableName, columnName, newColumnName);
    sqlSchemaWriter.renameColumn(tableName, columnName, newColumnName);
    sqlSchemaWriter.renameColumnIfExists(tableName, columnName, referencedTableColumnName);
  }

  @Override
  public void renameTable(final String tableName, final String newTableName) {
    schemaDataWriter.renameTable(tableName, newTableName);
    sqlSchemaWriter.renameTable(tableName, newTableName);
  }

  @Override
  public void reset() {
    sqlCollector.clear();
  }

  @Override
  public void saveChanges() {
    try {
      schemaDataWriter.setSchemaTimestamp(INCREMENTAL_CURRENT_TIME_CREATOR.getCurrentTime());
      sqlSchemaWriter.addAdditionalSqlStatements(sqlCollector.getSqlStatements());
      sqlCollector.executeAndClearUsingConnection(sqlConnection);
      saveCount++;
    } finally {
      reset();
    }
  }

  @Override
  public void setColumnModel(
    final TableIdentification table,
    final ColumnIdentification column,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<String> referenceableTableIds,
    final IContainer<String> backReferenceableColumnIds) {
    schemaDataWriter.setContentModel(
      table,
      column,
      fieldType,
      dataType,
      referenceableTableIds,
      backReferenceableColumnIds);
  }
}
