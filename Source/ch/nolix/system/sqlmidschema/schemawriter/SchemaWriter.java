/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.schemawriter;

import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.sql.sqltool.SqlCollector;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlmidschema.sqlschemamodelmapper.SqlSchemaColumnDtoMapper;
import ch.nolix.system.sqlmidschema.sqlschemamodelmapper.SqlSchemaTableDtoMapper;
import ch.nolix.system.time.timetool.IncrementalCurrentTimeCreator;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.adapter.ISchemaWriter;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;

/**
 * @author Silvan Wyss
 */
public final class SchemaWriter implements ISchemaWriter {
  private static final SqlSchemaTableDtoMapper SQL_SCHEMA_TABLE_DTO_MAPPER = new SqlSchemaTableDtoMapper();

  private static final SqlSchemaColumnDtoMapper SQL_SCHEMA_COLUMN_DTO_MAPPER = new SqlSchemaColumnDtoMapper();

  private static final IncrementalCurrentTimeCreator INCREMENTAL_CURRENT_TIME_CREATOR = //
  new IncrementalCurrentTimeCreator();

  private final ICloseController closeController = CloseController.forElement(this);

  private final SchemaDataWriter schemaDataWriter;

  private final ch.nolix.systemapi.sqlschema.adapter.ISchemaWriter sqlSchemaWriter;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  private int saveCount;

  private SchemaWriter(final String databaseName, final ISqlConnection sqlConnection) {
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void addColumn(final TableIdentification table, final ColumnDto column) {
    schemaDataWriter.addColumn(table, column);
    sqlSchemaWriter.addColumns(table, SQL_SCHEMA_COLUMN_DTO_MAPPER.mapColumnDtoToSqlSchemaColumnDtos(column));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addTable(final TableDto table) {
    schemaDataWriter.addTable(table);
    sqlSchemaWriter.addTable(SQL_SCHEMA_TABLE_DTO_MAPPER.mapTableDtoSqlSchemaTableDto(table));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteColumn(final TableIdentification table, final String columnName) {
    final var referencedTableColumnName = columnName + StringCatalog.DOLLAR + "ReferencedTable";

    schemaDataWriter.deleteColumn(table, columnName);
    sqlSchemaWriter.deleteColumn(table.tableName(), columnName);
    sqlSchemaWriter.deleteColumnIfExists(table.tableName(), referencedTableColumnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteTable(final String tableName) {
    schemaDataWriter.deleteTable(tableName);
    sqlSchemaWriter.deleteTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getSaveCount() {
    return saveCount;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    final var referencedTableColumnName = columnName + StringCatalog.DOLLAR + "ReferencedTable";

    schemaDataWriter.renameColumn(tableName, columnName, newColumnName);
    sqlSchemaWriter.renameColumn(tableName, columnName, newColumnName);
    sqlSchemaWriter.renameColumnIfExists(tableName, columnName, referencedTableColumnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void renameTable(final String tableName, final String newTableName) {
    schemaDataWriter.renameTable(tableName, newTableName);
    sqlSchemaWriter.renameTable(tableName, newTableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    sqlCollector.clear();
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void setColumnModel(
    final TableIdentification table,
    final ColumnIdentification column,
    final FieldType fieldType,
    final DataType dataType,
    final ExtendedIterable<String> referenceableTableIds,
    final ExtendedIterable<String> backReferenceableColumnIds) {
    schemaDataWriter.setContentModel(
      table,
      column,
      fieldType,
      dataType,
      referenceableTableIds,
      backReferenceableColumnIds);
  }
}
