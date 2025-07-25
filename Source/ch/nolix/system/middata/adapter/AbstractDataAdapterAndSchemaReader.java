package ch.nolix.system.middata.adapter;

import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middataapi.adapterapi.IDataAdapter;
import ch.nolix.systemapi.middataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;

/**
 * @author Silvan Wyss
 * @version 2022-02-25
 */
public abstract class AbstractDataAdapterAndSchemaReader
extends AbstractDataAdapter
implements IDataAdapterAndSchemaReader {

  private final ISchemaReader schemaReader;

  protected AbstractDataAdapterAndSchemaReader(final IDataAdapter dataAdapter, final ISchemaReader schemaReader) {

    super(dataAdapter, dataAdapter);

    ResourceValidator.assertIsOpen(schemaReader);

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
  public final TableDto loadTable(final String tableName) {
    return schemaReader.loadTable(tableName);
  }

  @Override
  public final IContainer<TableDto> loadTables() {
    return schemaReader.loadTables();
  }
}
