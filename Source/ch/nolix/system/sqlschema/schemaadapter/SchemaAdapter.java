package ch.nolix.system.sqlschema.schemaadapter;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi.ISchemaQueryCreator;
import ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi.ISchemaStatementCreator;

public abstract class SchemaAdapter implements ISchemaAdapter {

  private final ISchemaReader schemaReader;

  private final ISchemaWriter schemaWriter;

  private final CloseController closeController = CloseController.forElement(this);

  protected SchemaAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaQueryCreator schemaQueryCreator,
    final ISchemaStatementCreator schemaStatementCreator) {

    schemaReader = SchemaReader.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaQueryCreator(
      databaseName,
      sqlConnectionPool,
      schemaQueryCreator);

    schemaWriter = SchemaWriter.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaStatementCreator(
      databaseName,
      sqlConnectionPool,
      schemaStatementCreator);

    getStoredCloseController().createCloseDependencyTo(schemaReader);
    getStoredCloseController().createCloseDependencyTo(schemaWriter);
  }

  @Override
  public final void addColumn(final String tableName, final IColumnDto column) {
    schemaWriter.addColumn(tableName, column);
  }

  @Override
  public final void addTable(final ITableDto table) {
    schemaWriter.addTable(table);
  }

  @Override
  public final boolean columnsIsEmpty(final String tableName, final String columnName) {
    return schemaReader.columnsIsEmpty(tableName, columnName);
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
  public final CloseController getStoredCloseController() {
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
  public final IContainer<IColumnDto> loadColumns(final String tableName) {
    return schemaReader.loadColumns(tableName);
  }

  @Override
  public final IContainer<IFlatTableDto> loadFlatTables() {
    return schemaReader.loadFlatTables();
  }

  @Override
  public final IContainer<ITableDto> loadTables() {
    return schemaReader.loadTables();
  }

  @Override
  public final void noteClose() {
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
  public final boolean tableExists(final String tableName) {
    return schemaReader.tableExists(tableName);
  }
}
