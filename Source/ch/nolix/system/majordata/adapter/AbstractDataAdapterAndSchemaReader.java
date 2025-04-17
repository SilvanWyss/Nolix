package ch.nolix.system.majordata.adapter;

import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.majordataapi.adapterapi.IDataAdapter;
import ch.nolix.systemapi.majordataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.majorschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.majorschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public abstract class AbstractDataAdapterAndSchemaReader
extends AbstractDataAdapter
implements IDataAdapterAndSchemaReader {

  private final ISchemaReader schemaReader;

  protected AbstractDataAdapterAndSchemaReader(final IDataAdapter dataAdapter, final ISchemaReader schemaReader) {

    super(dataAdapter, dataAdapter);

    ResourceValidator.assertIsOpen(schemaReader);

    this.schemaReader = schemaReader;

    createCloseDependencyTo(this.schemaReader);
  }

  @Override
  public final boolean columnIsEmpty(final String tableName, final String columnName) {
    return schemaReader.columnIsEmpty(tableName, columnName);
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
  public final ITime loadSchemaTimestamp() {
    return schemaReader.loadSchemaTimestamp();
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
}
