package ch.nolix.system.sqlrawschema.schemaadapter;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.sqlrawschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.sqlrawschema.schemareader.SchemaReader;
import ch.nolix.system.sqlrawschema.schemawriter.SchemaWriter;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

public abstract class SchemaAdapter implements ISchemaAdapter {

  private static final DatabaseInitializer DATABASE_INITIALIZER = new DatabaseInitializer();

  private final SchemaReader rawSchemaReader;

  private final SchemaWriter rawSchemaWriter;

  private final CloseController closeController = CloseController.forElement(this);

  protected SchemaAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter sqlSchemaAdapter) {

    DATABASE_INITIALIZER.initializeDatabaseIfNotInitialized(databaseName, sqlSchemaAdapter, sqlConnectionPool);

    rawSchemaReader = SchemaReader.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaAdapter(
      databaseName,
      sqlConnectionPool,
      sqlSchemaAdapter);

    rawSchemaWriter = SchemaWriter.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaAdapter(
      databaseName,
      sqlConnectionPool,
      sqlSchemaAdapter);

    getStoredCloseController().createCloseDependencyTo(rawSchemaReader);
    getStoredCloseController().createCloseDependencyTo(rawSchemaWriter);
  }

  @Override
  public final boolean columnIsEmpty(final String tableName, final String columnName) {
    return rawSchemaReader.columnIsEmpty(tableName, columnName);
  }

  @Override
  public final void addColumn(final String tableName, IColumnDto column) {
    rawSchemaWriter.addColumn(tableName, column);
  }

  @Override
  public final void addTable(final ITableDto table) {
    rawSchemaWriter.addTable(table);
  }

  @Override
  public final void deleteColumn(final String tableName, final String columnName) {
    rawSchemaWriter.deleteColumn(tableName, columnName);
  }

  @Override
  public final void deleteTable(final String tableName) {
    rawSchemaWriter.deleteTable(tableName);
  }

  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final int getSaveCount() {
    return rawSchemaWriter.getSaveCount();
  }

  @Override
  public final int getTableCount() {
    return rawSchemaReader.getTableCount();
  }

  @Override
  public final boolean hasChanges() {
    return rawSchemaWriter.hasChanges();
  }

  @Override
  public final IContainer<IColumnDto> loadColumnsByTableId(final String tableId) {
    return rawSchemaReader.loadColumnsByTableId(tableId);
  }

  @Override
  public final IContainer<IColumnDto> loadColumnsByTableName(final String tableName) {
    return rawSchemaReader.loadColumnsByTableName(tableName);
  }

  @Override
  public final IFlatTableDto loadFlatTableById(final String id) {
    return rawSchemaReader.loadFlatTableById(id);
  }

  @Override
  public IFlatTableDto loadFlatTableByName(final String name) {
    return rawSchemaReader.loadFlatTableByName(name);
  }

  @Override
  public final IContainer<IFlatTableDto> loadFlatTables() {
    return rawSchemaReader.loadFlatTables();
  }

  @Override
  public final Time loadSchemaTimestamp() {
    return rawSchemaReader.loadSchemaTimestamp();
  }

  @Override
  public final ITableDto loadTableById(final String id) {
    return rawSchemaReader.loadTableById(id);
  }

  @Override
  public final ITableDto loadTableByName(final String name) {
    return rawSchemaReader.loadTableByName(name);
  }

  @Override
  public final IContainer<ITableDto> loadTables() {
    return rawSchemaReader.loadTables();
  }

  @Override
  public final void noteClose() {
  }

  @Override
  public final void reset() {
    rawSchemaWriter.reset();
  }

  @Override
  public final void saveChanges() {
    rawSchemaWriter.saveChanges();
  }

  @Override
  public final void setColumnName(final String tableName, final String columnName, final String newColumnName) {
    rawSchemaWriter.setColumnName(tableName, columnName, newColumnName);
  }

  @Override
  public final void setColumnParameterizedFieldType(
    final String columnId,
    final IParameterizedFieldTypeDto parameterizedFieldType) {
    rawSchemaWriter.setColumnParameterizedFieldType(columnId, parameterizedFieldType);
  }

  @Override
  public final void setTableName(final String tableName, final String newTableName) {
    rawSchemaWriter.setTableName(tableName, newTableName);
  }
}
