package ch.nolix.system.sqlrawschema.adapter;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.sqlrawschema.databaseinitializer.TempDatabaseInitializer;
import ch.nolix.system.sqlrawschema.schemareader.SchemaReader;
import ch.nolix.system.sqlrawschema.schemawriter.SchemaWriter;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public abstract class AbstractSchemaAdapter implements ISchemaAdapter {

  private static final TempDatabaseInitializer DATABASE_INITIALIZER = new TempDatabaseInitializer();

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISchemaReader rawSchemaReader;

  private final ISchemaWriter rawSchemaWriter;

  protected AbstractSchemaAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaAdapter sqlSchemaAdapter,
    final IQueryCreator queryCreator) {

    DATABASE_INITIALIZER.initializeDatabaseIfNotInitialized(
      databaseName,
      sqlSchemaAdapter,
      sqlConnectionPool,
      queryCreator);

    rawSchemaReader = //
    SchemaReader.forDatabaseNameAndSqlConnectionAndSqlSchemaQueryCreator(
      databaseName,
      sqlConnectionPool.borrowResource(),
      queryCreator);

    rawSchemaWriter = SchemaWriter.forDatabaseNameAndSqlConnection(databaseName, sqlConnectionPool.borrowResource());

    getStoredCloseController().createCloseDependencyTo(rawSchemaReader);
    getStoredCloseController().createCloseDependencyTo(rawSchemaWriter);
  }

  @Override
  public final boolean columnIsEmpty(final String tableName, final String columnName) {
    return rawSchemaReader.columnIsEmpty(tableName, columnName);
  }

  @Override
  public final void addColumn(final String tableName, ColumnDto column) {
    rawSchemaWriter.addColumn(tableName, column);
  }

  @Override
  public final void addTable(final TableDto table) {
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
  public final ICloseController getStoredCloseController() {
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
  public final IContainer<ColumnDto> loadColumnsByTableId(final String tableId) {
    return rawSchemaReader.loadColumnsByTableId(tableId);
  }

  @Override
  public final IContainer<ColumnDto> loadColumnsByTableName(final String tableName) {
    return rawSchemaReader.loadColumnsByTableName(tableName);
  }

  @Override
  public final FlatTableDto loadFlatTableById(final String id) {
    return rawSchemaReader.loadFlatTableById(id);
  }

  @Override
  public FlatTableDto loadFlatTableByName(final String name) {
    return rawSchemaReader.loadFlatTableByName(name);
  }

  @Override
  public final IContainer<FlatTableDto> loadFlatTables() {
    return rawSchemaReader.loadFlatTables();
  }

  @Override
  public final ITime loadSchemaTimestamp() {
    return rawSchemaReader.loadSchemaTimestamp();
  }

  @Override
  public final TableDto loadTableById(final String id) {
    return rawSchemaReader.loadTableById(id);
  }

  @Override
  public final TableDto loadTableByName(final String name) {
    return rawSchemaReader.loadTableByName(name);
  }

  @Override
  public final IContainer<TableDto> loadTables() {
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
  public final void setColumnContentModel(
    final String columnId,
    final IContentModelDto contentModel) {
    rawSchemaWriter.setColumnContentModel(columnId, contentModel);
  }

  @Override
  public final void setTableName(final String tableName, final String newTableName) {
    rawSchemaWriter.setTableName(tableName, newTableName);
  }
}
