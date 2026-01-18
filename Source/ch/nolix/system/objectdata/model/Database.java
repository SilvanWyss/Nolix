package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class Database implements IDatabase {
  private final IEntityTypeSet entityTypeSet;

  private final ITime schemaTimestamp;

  private final IContainer<? extends ITable<IEntity>> tables;

  private final IDataAdapterAndSchemaReader midDataAdapterAndSchemaReader;

  private final ICloseController closeController = CloseController.forElement(this);

  private Database(
    final IEntityTypeSet entityTypeSet,
    final IDataAdapterAndSchemaReader midDataAdapterAndSchemaReader) {
    ResourceValidator.assertIsOpen(midDataAdapterAndSchemaReader);
    Validator.assertThat(entityTypeSet).thatIsNamed(IEntityTypeSet.class).isNotNull();

    this.entityTypeSet = entityTypeSet;
    this.schemaTimestamp = midDataAdapterAndSchemaReader.getSchemaTimestamp();
    this.midDataAdapterAndSchemaReader = midDataAdapterAndSchemaReader;
    createCloseDependencyTo(this.midDataAdapterAndSchemaReader);
    this.tables = loadTables();
  }

  public static Database withEntityTypeSetAndMidDataAdapterAndSchemaReader(
    final IEntityTypeSet entityTypeSet,
    final IDataAdapterAndSchemaReader midDataAdapterAndSchemaReader) {
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

    if (getStoredTables().containsAny(ITable::isEdited)) {
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
  public <E extends IEntity> IContainer<E> getStoredEntitiesByType(final Class<E> type) {
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
  public IContainer<? extends ITable<IEntity>> getStoredTables() {
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

  IDataAdapterAndSchemaReader getStoredMidDataAdapterAndSchemaReader() {
    return midDataAdapterAndSchemaReader;
  }

  private IContainer<Table<IEntity>> loadTables() {
    return ImmutableList.fromIterable(TableLoader.loadTablesForDatabase(this));
  }
}
