package ch.nolix.system.rawschema.adapter;

import java.util.function.Supplier;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.databaseinitializerapi.IDatabaseInitializer;
import ch.nolix.systemapi.rawschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public abstract class AbstractRawSchemaAdapter implements ISchemaAdapter {

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISchemaReader schemaReader;

  private final ISchemaWriter schemaWriter;

  protected AbstractRawSchemaAdapter(
    final IDatabaseInitializer databaseInitializer,
    final Supplier<ISchemaReader> schemaReaderCreator,
    final Supplier<ISchemaWriter> schemaWriterCreator) {

    databaseInitializer.initializeDatabaseIfNotInitialized();

    schemaReader = schemaReaderCreator.get();
    schemaWriter = schemaWriterCreator.get();

    createCloseDependencyTo(schemaReader);
    createCloseDependencyTo(schemaWriter);
  }

  @Override
  public final void addColumn(final String tableName, ColumnDto column) {
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
  public final void createCloseDependencyTo(final GroupCloseable element) {
    ISchemaAdapter.super.createCloseDependencyTo(element);
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
  public final int getTableCount() {
    return schemaReader.getTableCount();
  }

  @Override
  public final boolean hasChanges() {
    return schemaWriter.hasChanges();
  }

  @Override
  public final IContainer<ColumnDto> loadColumnsByTableId(final String tableId) {
    return schemaReader.loadColumnsByTableId(tableId);
  }

  @Override
  public final IContainer<ColumnDto> loadColumnsByTableName(final String tableName) {
    return schemaReader.loadColumnsByTableName(tableName);
  }

  @Override
  public final FlatTableDto loadFlatTableById(final String id) {
    return schemaReader.loadFlatTableById(id);
  }

  @Override
  public final FlatTableDto loadFlatTableByName(final String name) {
    return schemaReader.loadFlatTableById(name);
  }

  @Override
  public final IContainer<FlatTableDto> loadFlatTables() {
    return schemaReader.loadFlatTables();
  }

  @Override
  public final ITime loadSchemaTimestamp() {
    return schemaReader.loadSchemaTimestamp();
  }

  @Override
  public final TableDto loadTableById(final String id) {
    return schemaReader.loadTableById(id);
  }

  @Override
  public final TableDto loadTableByName(final String name) {
    return schemaReader.loadTableByName(name);
  }

  @Override
  public final IContainer<TableDto> loadTables() {
    return schemaReader.loadTables();
  }

  @Override
  public final void noteClose() {
    //Does nothing.
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
  public final void setColumnName(final String tableName, final String columnName, final String newColumnName) {
    schemaWriter.setColumnName(tableName, columnName, newColumnName);
  }

  @Override
  public final void setColumnContentModel(final String columnId, final IContentModelDto contentModel) {
    schemaWriter.setColumnContentModel(columnId, contentModel);
  }

  @Override
  public final void setTableName(final String tableName, final String newTableName) {
    schemaWriter.setTableName(tableName, newTableName);
  }
}
