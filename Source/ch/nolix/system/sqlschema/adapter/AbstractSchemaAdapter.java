package ch.nolix.system.sqlschema.adapter;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaAdapter;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

public abstract class AbstractSchemaAdapter implements ISchemaAdapter {

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISqlConnection sqlConnection;

  private final ISchemaReader schemaReader;

  private final ISchemaWriter schemaWriter;

  protected AbstractSchemaAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IQueryCreator queryCreator) {

    sqlConnection = sqlConnectionPool.borrowResource();

    schemaReader = SchemaReader.forDatabaseNameAndSqlConnectionAndQueryCreator(
      databaseName,
      sqlConnection,
      queryCreator);

    schemaWriter = SchemaWriter.forDatabasNameAndSqlConnection(
      databaseName,
      sqlConnection);

    getStoredCloseController().createCloseDependencyTo(schemaReader);
    getStoredCloseController().createCloseDependencyTo(schemaWriter);
  }

  @Override
  public final void addColumn(final String tableName, final ColumnDto column) {
    schemaWriter.addColumn(tableName, column);
  }

  @Override
  public final void addTable(final TableDto table) {
    schemaWriter.addTable(table);
  }

  @Override
  public final boolean columnIsEmpty(final String tableName, final String columnName) {
    return schemaReader.columnIsEmpty(tableName, columnName);
  }

  @Override
  public final void deleteColumn(final String tableName, final String columnName) {
    schemaWriter.deleteColumn(tableName, columnName);
  }

  @Override
  public final void deleteTable(final String tableName) {
    schemaWriter.deleteTable(tableName);
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final int getSaveCount() {
    return schemaWriter.getSaveCount();
  }

  @Override
  public final IContainer<String> getSqlStatements() {
    return schemaWriter.getSqlStatements();
  }

  @Override
  public final boolean hasChanges() {
    return schemaWriter.hasChanges();
  }

  @Override
  public final TableDto loadTable(final String tableName) {
    return schemaReader.loadTable(tableName);
  }

  @Override
  public final IContainer<TableDto> loadTables() {
    return schemaReader.loadTables();
  }

  @Override
  public final void noteClose() {
    sqlConnection.close();
  }

  @Override
  public final void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    schemaWriter.renameColumn(tableName, columnName, newColumnName);
  }

  @Override
  public final void renameTable(final String tableName, final String newTableName) {
    schemaWriter.renameTable(tableName, newTableName);
  }

  @Override
  public final void reset() {
    schemaWriter.reset();
  }

  @Override
  public final void saveChanges() {
    schemaWriter.saveChanges();
  }

  @Override
  public final boolean tableExist() {
    return schemaReader.tableExist();
  }

  @Override
  public final boolean tableExists(final String tableName) {
    return schemaReader.tableExists(tableName);
  }
}
