//package declaration
package ch.nolix.system.noderawschema.schemaadapter;

//own imports
import ch.nolix.core.document.node.FileNode;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.noderawschema.schemareader.SchemaReader;
import ch.nolix.system.noderawschema.schemawriter.SchemaWriter;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
public final class SchemaAdapter implements ISchemaAdapter {

  //constant
  private static final DatabaseInitializer DATABASE_INITIALIZER = new DatabaseInitializer();

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //attribute
  private final SchemaReader schemaReader;

  //attribute
  private final SchemaWriter schemaWriter;

  //constructor
  private SchemaAdapter(final IMutableNode<?> databaseNode) {

    DATABASE_INITIALIZER.initializeDatabaseIfNotInitialized(databaseNode);

    schemaReader = new SchemaReader(databaseNode);
    schemaWriter = new SchemaWriter(databaseNode);

    createCloseDependencyTo(schemaReader);
    createCloseDependencyTo(schemaWriter);
  }

  //static method
  public static SchemaAdapter forDatabaseNode(final IMutableNode<?> databaseNode) {
    return new SchemaAdapter(databaseNode);
  }

  //static method
  public static SchemaAdapter forDatabaseNodeInFile(final String filePath) {
    return new SchemaAdapter(new FileNode(filePath));
  }

  //method
  @Override
  public boolean columnIsEmpty(final String tableName, final String columnName) {
    return schemaReader.columnIsEmpty(tableName, columnName);
  }

  //method
  @Override
  public void addColumn(final String tableName, IColumnDto column) {
    schemaWriter.addColumn(tableName, column);
  }

  //method
  @Override
  public void addTable(final ITableDto table) {
    schemaWriter.addTable(table);
  }

  //method
  @Override
  public void deleteColumn(final String tableName, final String columnName) {
    schemaWriter.deleteColumn(tableName, columnName);
  }

  //method
  @Override
  public void deleteTable(final String tableName) {
    schemaWriter.deleteTable(tableName);
  }

  //method
  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public int getSaveCount() {
    return schemaWriter.getSaveCount();
  }

  //method
  @Override
  public int getTableCount() {
    return schemaReader.getTableCount();
  }

  //method
  @Override
  public boolean hasChanges() {
    return schemaWriter.hasChanges();
  }

  //method
  @Override
  public IContainer<IColumnDto> loadColumnsByTableId(final String tableId) {
    return schemaReader.loadColumnsByTableId(tableId);
  }

  //method
  @Override
  public IContainer<IColumnDto> loadColumnsByTableName(final String tableName) {
    return schemaReader.loadColumnsByTableName(tableName);
  }

  //method
  @Override
  public IFlatTableDto loadFlatTableById(final String id) {
    return schemaReader.loadFlatTableById(id);
  }

  //method
  @Override
  public IFlatTableDto loadFlatTableByName(final String name) {
    return schemaReader.loadFlatTableById(name);
  }

  //method
  @Override
  public IContainer<IFlatTableDto> loadFlatTables() {
    return schemaReader.loadFlatTables();
  }

  //method
  @Override
  public Time loadSchemaTimestamp() {
    return schemaReader.loadSchemaTimestamp();
  }

  //method
  @Override
  public ITableDto loadTableById(final String id) {
    return schemaReader.loadTableById(id);
  }

  //method
  @Override
  public ITableDto loadTableByName(final String name) {
    return schemaReader.loadTableByName(name);
  }

  //method
  @Override
  public IContainer<ITableDto> loadTables() {
    return schemaReader.loadTables();
  }

  //method
  @Override
  public void noteClose() {
    //Does nothing.
  }

  //method
  @Override
  public void reset() {
    schemaWriter.reset();
  }

  //method
  @Override
  public void saveChanges() {
    schemaWriter.saveChanges();
  }

  //method
  @Override
  public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
    schemaWriter.setColumnName(tableName, columnName, newColumnName);
  }

  //method
  @Override
  public void setColumnParameterizedPropertyType(
    final String columnId,
    final IParameterizedPropertyTypeDto parameterizedPropertyType) {
    schemaWriter.setColumnParameterizedPropertyType(columnId, parameterizedPropertyType);
  }

  //method
  @Override
  public void setTableName(final String tableName, final String newTableName) {
    schemaWriter.setTableName(tableName, newTableName);
  }
}
