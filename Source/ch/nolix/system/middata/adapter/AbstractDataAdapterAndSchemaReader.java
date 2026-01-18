package ch.nolix.system.middata.adapter;

import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.adapter.IDataAdapter;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;
import ch.nolix.systemapi.midschema.model.TableDto;

/**
 * @author Silvan Wyss
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
  public final int getTableCount() {
    return schemaReader.getTableCount();
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
}
