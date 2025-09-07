package ch.nolix.system.midschema.adapter;

import java.util.function.Supplier;

import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.systemapi.midschema.adapter.ISchemaAdapter;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;
import ch.nolix.systemapi.midschema.adapter.ISchemaWriter;
import ch.nolix.systemapi.midschema.databaseinitializer.IDatabaseInitializer;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.time.moment.ITime;

public abstract class AbstractSchemaAdapter implements ISchemaAdapter {
  private final ICloseController closeController = CloseController.forElement(this);

  private final ISchemaReader schemaReader;

  private final ISchemaWriter schemaWriter;

  protected AbstractSchemaAdapter(
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
  public final ITime getSchemaTimestamp() {
    return schemaReader.getSchemaTimestamp();
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
  public final void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    schemaWriter.renameColumn(tableName, columnName, newColumnName);
  }

  @Override
  public final void setContentModel(
    final String tableName,
    final String columnName,
    final ContentModelDto contentModel) {
    schemaWriter.setContentModel(tableName, columnName, contentModel);
  }

  @Override
  public final void renameTable(final String tableName, final String newTableName) {
    schemaWriter.renameTable(tableName, newTableName);
  }
}
