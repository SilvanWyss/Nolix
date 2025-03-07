package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class Database implements IDatabase {

  private static final IResourceValidator RESOURCE_VALIDATOR = new ResourceValidator();

  private static final DatabaseTableLoader DATABASE_TABLE_LOADER = new DatabaseTableLoader();

  private final IEntityTypeSet entityTypeSet;

  private final ITime schemaTimestamp;

  private final ImmutableList<? extends ITable<IEntity>> tables;

  private final IDataAdapterAndSchemaReader rawDataAdapterAndSchemaReader;

  private final ICloseController closeController = CloseController.forElement(this);

  private Database(final IEntityTypeSet entityTypeSet,
    final IDataAdapterAndSchemaReader rawDataAdapterAndSchemaReader) {

    RESOURCE_VALIDATOR.assertIsOpen(rawDataAdapterAndSchemaReader);
    Validator.assertThat(entityTypeSet).thatIsNamed(IEntityTypeSet.class).isNotNull();

    this.entityTypeSet = entityTypeSet;
    this.schemaTimestamp = rawDataAdapterAndSchemaReader.getSchemaTimestamp();
    this.rawDataAdapterAndSchemaReader = rawDataAdapterAndSchemaReader;
    createCloseDependencyTo(this.rawDataAdapterAndSchemaReader);
    this.tables = loadTables();
  }

  public static Database withSchemaAndRawDataAdapterAndSchemaReader(
    final IEntityTypeSet entityTypeSet,
    final IDataAdapterAndSchemaReader rawDataAdapterAndSchemaReader) {
    return new Database(entityTypeSet, rawDataAdapterAndSchemaReader);
  }

  @Override
  public IEntityTypeSet getEntityTypeSet() {
    return entityTypeSet;
  }

  @Override
  public String getName() {
    return getStoredRawDataAdapterAndSchemaReader().getDatabaseName();
  }

  @Override
  public ITime getSchemaTimestamp() {
    return schemaTimestamp;
  }

  @Override
  public DatabaseObjectState getState() {

    if (getStoredRawDataAdapterAndSchemaReader().isClosed()) {
      return DatabaseObjectState.CLOSED;
    }

    if (getStoredTables().containsAny(ITable::isEdited) || getStoredRawDataAdapterAndSchemaReader().hasChanges()) {
      return DatabaseObjectState.EDITED;
    }

    return DatabaseObjectState.LOADED;
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public <E extends IEntity> IContainer<E> getStoredEntitiesByType(final Class<E> type) {
    return getStoredTableByEntityType(type).getStoredEntities();
  }

  @Override
  @SuppressWarnings("unchecked")
  public <E extends IEntity> ITable<E> getStoredTableByEntityType(final Class<E> entityType) {
    return (ITable<E>) getStoredTableByName(entityType.getSimpleName());
  }

  @Override
  public ITable<IEntity> getStoredTableByName(final String name) {
    return tables.getStoredFirst(t -> t.hasName(name));
  }

  @Override
  public IContainer<? extends ITable<IEntity>> getStoredTables() {
    return tables;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <E extends IEntity> IDatabase insertEntity(final E entity) {

    getStoredTableByEntityType((Class<E>) entity.getClass()).insertEntity(entity);

    return this;
  }

  @Override
  public boolean isClosed() {
    return getStoredRawDataAdapterAndSchemaReader().isClosed();
  }

  @Override
  public boolean isDeleted() {
    return (getState() == DatabaseObjectState.DELETED);
  }

  @Override
  public boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return true;
  }

  @Override
  public boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  @Override
  public boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  @Override
  public void noteClose() {

    for (final var t : getStoredTables()) {
      ((Table<?>) t).close();
    }

    rawDataAdapterAndSchemaReader.close();
  }

  IDataAdapterAndSchemaReader getStoredRawDataAdapterAndSchemaReader() {
    return rawDataAdapterAndSchemaReader;
  }

  private ImmutableList<Table<IEntity>> loadTables() {
    return ImmutableList.forIterable(DATABASE_TABLE_LOADER.loadTablesForDatabase(this));
  }
}
