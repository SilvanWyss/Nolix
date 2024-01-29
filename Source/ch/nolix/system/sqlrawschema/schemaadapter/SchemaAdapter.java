//package declaration
package ch.nolix.system.sqlrawschema.schemaadapter;

//own imports
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
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
public abstract class SchemaAdapter implements ISchemaAdapter {

  //constant
  private static final DatabaseInitializer DATABASE_INITIALIZER = new DatabaseInitializer();

  //attribute
  private final SchemaReader rawSchemaReader;

  //attribute
  private final SchemaWriter rawSchemaWriter;

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //constructor
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

  //method
  @Override
  public final boolean columnIsEmpty(final String tableName, final String columnName) {
    return rawSchemaReader.columnIsEmpty(tableName, columnName);
  }

  //method
  @Override
  public final void addColumn(final String tableName, IColumnDto column) {
    rawSchemaWriter.addColumn(tableName, column);
  }

  //method
  @Override
  public final void addTable(final ITableDto table) {
    rawSchemaWriter.addTable(table);
  }

  //method
  @Override
  public final void deleteColumn(final String tableName, final String columnName) {
    rawSchemaWriter.deleteColumn(tableName, columnName);
  }

  //method
  @Override
  public final void deleteTable(final String tableName) {
    rawSchemaWriter.deleteTable(tableName);
  }

  //method
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public final int getSaveCount() {
    return rawSchemaWriter.getSaveCount();
  }

  //method
  @Override
  public final int getTableCount() {
    return rawSchemaReader.getTableCount();
  }

  //method
  @Override
  public final boolean hasChanges() {
    return rawSchemaWriter.hasChanges();
  }

  //method
  @Override
  public final IContainer<IColumnDto> loadColumnsByTableId(final String tableId) {
    return rawSchemaReader.loadColumnsByTableId(tableId);
  }

  //method
  @Override
  public final IContainer<IColumnDto> loadColumnsByTableName(final String tableName) {
    return rawSchemaReader.loadColumnsByTableName(tableName);
  }

  //method
  @Override
  public final IFlatTableDto loadFlatTableById(final String id) {
    return rawSchemaReader.loadFlatTableById(id);
  }

  //method
  @Override
  public IFlatTableDto loadFlatTableByName(final String name) {
    return rawSchemaReader.loadFlatTableByName(name);
  }

  //method
  @Override
  public final IContainer<IFlatTableDto> loadFlatTables() {
    return rawSchemaReader.loadFlatTables();
  }

  //method
  @Override
  public final Time loadSchemaTimestamp() {
    return rawSchemaReader.loadSchemaTimestamp();
  }

  //method
  @Override
  public final ITableDto loadTableById(final String id) {
    return rawSchemaReader.loadTableById(id);
  }

  //method
  @Override
  public final ITableDto loadTableByName(final String name) {
    return rawSchemaReader.loadTableByName(name);
  }

  //method
  @Override
  public final IContainer<ITableDto> loadTables() {
    return rawSchemaReader.loadTables();
  }

  //method
  @Override
  public final void noteClose() {
  }

  //method
  @Override
  public final void reset() {
    rawSchemaWriter.reset();
  }

  //method
  @Override
  public final void saveChanges() {
    rawSchemaWriter.saveChanges();
  }

  //method
  @Override
  public final void setColumnName(final String tableName, final String columnName, final String newColumnName) {
    rawSchemaWriter.setColumnName(tableName, columnName, newColumnName);
  }

  //method
  @Override
  public final void setColumnParameterizedPropertyType(
    final String columnId,
    final IParameterizedPropertyTypeDto parameterizedPropertyType) {
    rawSchemaWriter.setColumnParameterizedPropertyType(columnId, parameterizedPropertyType);
  }

  //method
  @Override
  public final void setTableName(final String tableName, final String newTableName) {
    rawSchemaWriter.setTableName(tableName, newTableName);
  }
}
