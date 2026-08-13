/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.systemapi.database.databaseobject.DatabaseObjectState;
import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class Database implements IDatabase {
  private final IEntityTypeSet entityTypeSet;

  private final ITime schemaTimestamp;

  private final ExtendedIterable<? extends ITable<IEntity>> tables;

  private final DataAdapterAndSchemaReader midDataAdapterAndSchemaReader;

  private final ICloseController closeController = CloseController.forElement(this);

  private Database(
    final IEntityTypeSet entityTypeSet,
    final DataAdapterAndSchemaReader midDataAdapterAndSchemaReader) {
    ResourceValidator.assertIsOpen(midDataAdapterAndSchemaReader);
    Validator.assertThat(entityTypeSet).thatIsNamed(IEntityTypeSet.class).isNotNull();

    this.entityTypeSet = entityTypeSet;
    this.schemaTimestamp = midDataAdapterAndSchemaReader.getSchemaTimestamp();
    this.midDataAdapterAndSchemaReader = midDataAdapterAndSchemaReader;
    createCloseDependencyTo(this.midDataAdapterAndSchemaReader);
    this.tables = TableLoader.loadTablesForDatabase(this);
  }

  public static Database withEntityTypeSetAndMidDataAdapterAndSchemaReader(
    final IEntityTypeSet entityTypeSet,
    final DataAdapterAndSchemaReader midDataAdapterAndSchemaReader) {
    return new Database(entityTypeSet, midDataAdapterAndSchemaReader);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IEntityTypeSet getEntityTypeSet() {
    return entityTypeSet;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return getStoredMidDataAdapterAndSchemaReader().getDatabaseName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITime getSchemaTimestamp() {
    return schemaTimestamp;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseObjectState getState() {
    if (getStoredMidDataAdapterAndSchemaReader().isClosed()) {
      return DatabaseObjectState.CLOSED;
    }

    if (getStoredTables().containsMatching(ITable::isEdited)) {
      return DatabaseObjectState.EDITED;
    }

    return DatabaseObjectState.UNEDITED;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> ExtendedIterable<E> getStoredEntitiesByType(final Class<E> type) {
    final var table = getStoredTableByEntityType(type);

    return table.getStoredEntities();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public <E extends IEntity> ITable<E> getStoredTableByEntityType(final Class<E> entityType) {
    final var tableName = entityType.getSimpleName();

    return (ITable<E>) getStoredTableByName(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITable<IEntity> getStoredTableByName(final String name) {
    return getStoredTables().getStoredFirst(t -> t.hasName(name));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends ITable<IEntity>> getStoredTables() {
    return tables;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public <E extends IEntity> IDatabase insertEntity(final E entity) {
    final var entityType = (Class<E>) entity.getClass();
    final var table = getStoredTableByEntityType(entityType);

    table.insertEntity(entity);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isClosed() {
    return getStoredMidDataAdapterAndSchemaReader().isClosed();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isConnectedWithRealDatabase() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDeleted() {
    return (getState() == DatabaseObjectState.DELETED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isLoaded() {
    return (getState() == DatabaseObjectState.UNEDITED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    for (final var t : getStoredTables()) {
      ((Table<?>) t).close();
    }

    midDataAdapterAndSchemaReader.close();
  }

  DataAdapterAndSchemaReader getStoredMidDataAdapterAndSchemaReader() {
    return midDataAdapterAndSchemaReader;
  }
}
