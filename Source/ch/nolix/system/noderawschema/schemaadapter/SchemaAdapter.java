package ch.nolix.system.noderawschema.schemaadapter;

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
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

public final class SchemaAdapter implements ISchemaAdapter {

  private static final DatabaseInitializer DATABASE_INITIALIZER = new DatabaseInitializer();

  private final CloseController closeController = CloseController.forElement(this);

  private final SchemaReader schemaReader;

  private final SchemaWriter schemaWriter;

  private SchemaAdapter(final IMutableNode<?> nodeDatabase) {

    DATABASE_INITIALIZER.initializeDatabaseIfNotInitialized(nodeDatabase);

    schemaReader = SchemaReader.forNodeDatabase(nodeDatabase);
    schemaWriter = SchemaWriter.forNodeDatabase(nodeDatabase);

    createCloseDependencyTo(schemaReader);
    createCloseDependencyTo(schemaWriter);
  }

  public static SchemaAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new SchemaAdapter(nodeDatabase);
  }

  public static SchemaAdapter forFileNodeDatabase(final String filePath) {
    return new SchemaAdapter(new FileNode(filePath));
  }

  @Override
  public boolean columnIsEmpty(final String tableName, final String columnName) {
    return schemaReader.columnIsEmpty(tableName, columnName);
  }

  @Override
  public void addColumn(final String tableName, IColumnDto column) {
    schemaWriter.addColumn(tableName, column);
  }

  @Override
  public void addTable(final ITableDto table) {
    schemaWriter.addTable(table);
  }

  @Override
  public void deleteColumn(final String tableName, final String columnName) {
    schemaWriter.deleteColumn(tableName, columnName);
  }

  @Override
  public void deleteTable(final String tableName) {
    schemaWriter.deleteTable(tableName);
  }

  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getSaveCount() {
    return schemaWriter.getSaveCount();
  }

  @Override
  public int getTableCount() {
    return schemaReader.getTableCount();
  }

  @Override
  public boolean hasChanges() {
    return schemaWriter.hasChanges();
  }

  @Override
  public IContainer<IColumnDto> loadColumnsByTableId(final String tableId) {
    return schemaReader.loadColumnsByTableId(tableId);
  }

  @Override
  public IContainer<IColumnDto> loadColumnsByTableName(final String tableName) {
    return schemaReader.loadColumnsByTableName(tableName);
  }

  @Override
  public IFlatTableDto loadFlatTableById(final String id) {
    return schemaReader.loadFlatTableById(id);
  }

  @Override
  public IFlatTableDto loadFlatTableByName(final String name) {
    return schemaReader.loadFlatTableById(name);
  }

  @Override
  public IContainer<IFlatTableDto> loadFlatTables() {
    return schemaReader.loadFlatTables();
  }

  @Override
  public Time loadSchemaTimestamp() {
    return schemaReader.loadSchemaTimestamp();
  }

  @Override
  public ITableDto loadTableById(final String id) {
    return schemaReader.loadTableById(id);
  }

  @Override
  public ITableDto loadTableByName(final String name) {
    return schemaReader.loadTableByName(name);
  }

  @Override
  public IContainer<ITableDto> loadTables() {
    return schemaReader.loadTables();
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void reset() {
    schemaWriter.reset();
  }

  @Override
  public void saveChanges() {
    schemaWriter.saveChanges();
  }

  @Override
  public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
    schemaWriter.setColumnName(tableName, columnName, newColumnName);
  }

  @Override
  public void setColumnParameterizedFieldType(
    final String columnId,
    final IContentModelDto parameterizedFieldType) {
    schemaWriter.setColumnParameterizedFieldType(columnId, parameterizedFieldType);
  }

  @Override
  public void setTableName(final String tableName, final String newTableName) {
    schemaWriter.setTableName(tableName, newTableName);
  }
}
