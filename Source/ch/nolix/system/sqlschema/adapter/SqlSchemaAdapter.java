/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlschema.adapter;

import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.baseapi.resourcecontrol.resourcepool.ResourcePool;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.sqlschema.adapter.ISchemaReader;
import ch.nolix.systemapi.sqlschema.adapter.ISchemaWriter;
import ch.nolix.systemapi.sqlschema.adapter.SchemaAdapter;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class SqlSchemaAdapter implements SchemaAdapter {
  private final ICloseController closeController = CloseController.forElement(this);

  private final ISqlConnection sqlConnection;

  private final ISchemaReader schemaReader;

  private final ISchemaWriter schemaWriter;

  private SqlSchemaAdapter(final String databaseName, final ResourcePool<ISqlConnection> sqlConnectionPool) {
    sqlConnection = sqlConnectionPool.borrowResource();

    schemaReader = SchemaReader.forDatabaseNameAndSqlConnection(
      databaseName,
      sqlConnection);

    schemaWriter = SchemaWriter.forDatabasNameAndSqlConnection(
      databaseName,
      sqlConnection);

    getStoredCloseController().createCloseDependencyTo(schemaReader);
    getStoredCloseController().createCloseDependencyTo(schemaWriter);
  }

  public static SqlSchemaAdapter forDatabaseNameAndWithSqlConnectionPool(
    final String databaseName,
    final ResourcePool<ISqlConnection> sqlConnectionPool) {
    return new SqlSchemaAdapter(databaseName, sqlConnectionPool);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addAdditionalSqlStatements(final ExtendedIterable<String> additionalSqlStatements) {
    schemaWriter.addAdditionalSqlStatements(additionalSqlStatements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addColumn(final TableIdentification table, final ColumnDto column) {
    schemaWriter.addColumn(table, column);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addColumns(final TableIdentification table, final ExtendedIterable<ColumnDto> columns) {
    for (final var c : columns) {
      addColumn(table, c);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addTable(final TableDto table) {
    schemaWriter.addTable(table);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addTables(final ExtendedIterable<TableDto> tables) {
    for (final var t : tables) {
      addTable(t);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addTables(final TableDto... tables) {
    for (final var t : tables) {
      addTable(t);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean columnIsEmpty(final String tableName, final String columnName) {
    return schemaReader.columnIsEmpty(tableName, columnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteColumn(final String tableName, final String columnName) {
    schemaWriter.deleteColumn(tableName, columnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteColumnIfExists(final String tableName, final String columnName) {
    schemaWriter.deleteColumnIfExists(tableName, columnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteTable(final String tableName) {
    schemaWriter.deleteTable(tableName);
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
    return schemaWriter.getSaveCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChanges() {
    return schemaWriter.hasChanges();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableDto loadTable(final String tableName) {
    return schemaReader.loadTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTableCount() {
    return schemaReader.getTableCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<TableDto> loadTables() {
    return schemaReader.loadTables();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    sqlConnection.close();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    schemaWriter.renameColumn(tableName, columnName, newColumnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void renameColumnIfExists(final String tableName, final String columnName, final String newColumnName) {
    schemaWriter.renameColumnIfExists(tableName, columnName, newColumnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void renameTable(final String tableName, final String newTableName) {
    schemaWriter.renameTable(tableName, newTableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    schemaWriter.reset();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveChanges() {
    schemaWriter.saveChanges();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean tableExist() {
    return schemaReader.tableExist();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean tableExists(final String tableName) {
    return schemaReader.tableExists(tableName);
  }
}
