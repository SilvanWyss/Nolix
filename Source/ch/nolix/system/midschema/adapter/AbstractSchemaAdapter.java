package ch.nolix.system.midschema.adapter;

import java.util.function.Supplier;

import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.systemapi.midschema.adapter.ISchemaAdapter;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;
import ch.nolix.systemapi.midschema.adapter.ISchemaWriter;
import ch.nolix.systemapi.midschema.databaseinitializer.IDatabaseInitializer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
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

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addColumn(final TableIdentification table, final ColumnDto column) {
    schemaWriter.addColumn(table, column);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addTable(final TableDto table) {
    schemaWriter.addTable(table);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean columnIsEmpty(final String tableName, final String columnName) {
    return schemaReader.columnIsEmpty(tableName, columnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void createCloseDependencyTo(final GroupCloseable element) {
    ISchemaAdapter.super.createCloseDependencyTo(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void deleteColumn(final TableIdentification table, final String columnName) {
    schemaWriter.deleteColumn(table, columnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void deleteTable(final String tableName) {
    schemaWriter.deleteTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getSaveCount() {
    return schemaWriter.getSaveCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getTableCount() {
    return schemaReader.getTableCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasChanges() {
    return schemaWriter.hasChanges();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ITime getSchemaTimestamp() {
    return schemaReader.getSchemaTimestamp();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final TableDto loadTable(final String tableName) {
    return schemaReader.loadTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<TableDto> loadTables() {
    return schemaReader.loadTables();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void reset() {
    schemaWriter.reset();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void saveChanges() {
    schemaWriter.saveChanges();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    schemaWriter.renameColumn(tableName, columnName, newColumnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void renameTable(final String tableName, final String newTableName) {
    schemaWriter.renameTable(tableName, newTableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setColumnModel(
    final TableIdentification table,
    final ColumnIdentification column,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<String> referenceableTableIds,
    final IContainer<String> backReferenceableColumnIds) {
    schemaWriter.setColumnModel(
      table,
      column,
      fieldType,
      dataType,
      referenceableTableIds,
      backReferenceableColumnIds);
  }
}
