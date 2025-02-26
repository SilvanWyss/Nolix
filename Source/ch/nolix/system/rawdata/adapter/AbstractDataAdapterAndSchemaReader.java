package ch.nolix.system.rawdata.adapter;

import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapter;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2022-02-25
 */
public abstract class AbstractDataAdapterAndSchemaReader
extends AbstractDataAdapter
implements IDataAdapterAndSchemaReader {

  private static final IResourceValidator RESOURCE_VALIDATOR = new ResourceValidator();

  private final ISchemaReader schemaReader;

  protected AbstractDataAdapterAndSchemaReader(final IDataAdapter dataAdapter, final ISchemaReader schemaReader) {

    super(dataAdapter, dataAdapter);

    RESOURCE_VALIDATOR.assertIsOpen(schemaReader);

    this.schemaReader = schemaReader;

    createCloseDependencyTo(schemaReader);
  }

  @Override
  public final boolean columnIsEmpty(final String tableName, final String columnName) {
    return schemaReader.columnIsEmpty(tableName, columnName);
  }

  @Override
  public final int getTableCount() {
    return schemaReader.getTableCount();
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
  public final FlatTableDto loadFlatTableById(String id) {
    return schemaReader.loadFlatTableById(id);
  }

  @Override
  public final FlatTableDto loadFlatTableByName(final String name) {
    return schemaReader.loadFlatTableByName(name);
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
}
