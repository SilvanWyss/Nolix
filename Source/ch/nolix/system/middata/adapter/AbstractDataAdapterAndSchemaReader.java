/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.middata.adapter;

import ch.nolix.base.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.middata.adapter.DataAdapter;
import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;
import ch.nolix.systemapi.midschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractDataAdapterAndSchemaReader
extends AbstractDataAdapter
implements DataAdapterAndSchemaReader {
  private final ISchemaReader schemaReader;

  protected AbstractDataAdapterAndSchemaReader(final DataAdapter dataAdapter, final ISchemaReader schemaReader) {
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
  public final ExtendedIterable<TableDto> loadTables() {
    return schemaReader.loadTables();
  }
}
