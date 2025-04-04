package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.middataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class Database implements IDatabase {

  private static final IResourceValidator RESOURCE_VALIDATOR = new ResourceValidator();

  private static final DatabaseTableLoader DATABASE_TABLE_LOADER = new DatabaseTableLoader();

  private final IEntityTypeSet entityTypeSet;

  private final ITime schemaTimestamp;

  private final IContainer<? extends ITable<IEntity>> tables;

  private final IDataAdapterAndSchemaReader midDataAdapterAndSchemaReader;

  private final ICloseController closeController = CloseController.forElement(this);

  private Database(
    final IEntityTypeSet entityTypeSet,
    final IDataAdapterAndSchemaReader midDataAdapterAndSchemaReader) {

    RESOURCE_VALIDATOR.assertIsOpen(midDataAdapterAndSchemaReader);
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

  @Override
  public IEntityTypeSet getEntityTypeSet() {
    return entityTypeSet;
  }

  @Override
  public String getName() {
    return getStoredMidDataAdapterAndSchemaReader().getDatabaseName();
  }

  @Override
  public ITime getSchemaTimestamp() {
    return schemaTimestamp;
  }

  @Override
  public DatabaseObjectState getState() {

    if (getStoredMidDataAdapterAndSchemaReader().isClosed()) {
      return DatabaseObjectState.CLOSED;
    }

    if (getStoredTables().containsAny(ITable::isEdited)) {
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

    final var table = getStoredTableByEntityType(type);

    return table.getStoredEntities();
  }

  @Override
  @SuppressWarnings("unchecked")
  public <E extends IEntity> ITable<E> getStoredTableByEntityType(final Class<E> entityType) {

    final var tableName = entityType.getSimpleName();

    return (ITable<E>) getStoredTableByName(tableName);
  }

  @Override
  public ITable<IEntity> getStoredTableByName(final String name) {
    return getStoredTables().getStoredFirst(t -> t.hasName(name));
  }

  @Override
  public IContainer<? extends ITable<IEntity>> getStoredTables() {
    return tables;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <E extends IEntity> IDatabase insertEntity(final E entity) {

    final var entityType = (Class<E>) entity.getClass();
    final var table = getStoredTableByEntityType(entityType);

    table.insertEntity(entity);

    return this;
  }

  @Override
  public boolean isClosed() {
    return getStoredMidDataAdapterAndSchemaReader().isClosed();
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return true;
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

    midDataAdapterAndSchemaReader.close();
  }

  IDataAdapterAndSchemaReader getStoredMidDataAdapterAndSchemaReader() {
    return midDataAdapterAndSchemaReader;
  }

  private IContainer<Table<IEntity>> loadTables() {
    return ImmutableList.forIterable(DATABASE_TABLE_LOADER.loadTablesForDatabase(this));
  }
}
