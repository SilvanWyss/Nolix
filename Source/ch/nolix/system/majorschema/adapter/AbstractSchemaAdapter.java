package ch.nolix.system.majorschema.adapter;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.systemapi.majorschemaapi.adapterapi.ISchemaAdapter;
import ch.nolix.systemapi.majorschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.majorschemaapi.adapterapi.ISchemaWriter;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.majorschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public abstract class AbstractSchemaAdapter implements ISchemaAdapter {

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISchemaReader schemaReader;

  private final ISchemaWriter schemaWriter;

  protected AbstractSchemaAdapter(final ISchemaReader schemaReader, final ISchemaWriter schemaWriter) {

    this.schemaReader = schemaReader;
    this.schemaWriter = schemaWriter;

    createCloseDependencyTo(this.schemaReader);
    createCloseDependencyTo(this.schemaWriter);
  }

  @Override
  public final void addColumn(final String tableName, final ColumnDto column) {
    schemaWriter.addColumn(tableName, column);
  }

  @Override
  public final void addTable(TableDto table) {
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
  public final int getSaveCount() {
    return schemaWriter.getSaveCount();
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final boolean hasChanges() {
    return schemaWriter.hasChanges();
  }

  @Override
  public final IContainer<ColumnDto> loadColumns(final String tableName) {
    return schemaReader.loadColumns(tableName);
  }

  @Override
  public final FlatTableDto loadFlatTable(final String tableName) {
    return schemaReader.loadFlatTable(tableName);
  }

  @Override
  public final IContainer<FlatTableDto> loadFlatTables() {
    return schemaReader.loadFlatTables();
  }

  @Override
  public final TableDto loadTable(final String tableName) {
    return schemaReader.loadTable(tableName);
  }

  @Override
  public final int loadTableCount() {
    return schemaReader.loadTableCount();
  }

  @Override
  public final IContainer<TableDto> loadTables() {
    return schemaReader.loadTables();
  }

  @Override
  public final ITime loadSchemaTimestamp() {
    return schemaReader.loadSchemaTimestamp();
  }

  @Override
  public final void noteClose() {
    //Does nothing.
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
}
