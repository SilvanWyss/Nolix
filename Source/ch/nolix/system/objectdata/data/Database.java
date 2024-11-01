package ch.nolix.system.objectdata.data;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class Database implements IDatabase {

  private static final DatabaseTableLoader DATABASE_TABLE_LOADER = new DatabaseTableLoader();

  private final ITime schemaTimestamp;

  private final IDataAndSchemaAdapter dataAndSchemaAdapter;

  private final ISchema schema;

  private final LinkedList<? extends ITable<IEntity>> tables;

  private Database(final IDataAndSchemaAdapter dataAndSchemaAdapter, final ISchema schema) {

    GlobalValidator.assertThat(dataAndSchemaAdapter).thatIsNamed(IDataAndSchemaAdapter.class).isNotNull();
    GlobalValidator.assertThat(schema).thatIsNamed(ISchema.class).isNotNull();

    schemaTimestamp = dataAndSchemaAdapter.getSchemaTimestamp();
    this.dataAndSchemaAdapter = dataAndSchemaAdapter;
    this.schema = schema;
    tables = loadTables();
  }

  public static Database withDataAndSchemaAdapterAndSchema(
    final IDataAndSchemaAdapter dataAndSchemaAdapter,
    final ISchema schema) {
    return new Database(dataAndSchemaAdapter, schema);
  }

  @Override
  public <E extends IEntity> IContainer<E> getStoredEntitiesByType(final Class<E> type) {
    return getStoredTableByEntityType(type).getStoredEntities();
  }

  @Override
  @SuppressWarnings("unchecked")
  public <E extends IEntity> ITable<E> getStoredTableByEntityType(
    final Class<E> entityClass) {
    return (ITable<E>) getStoredTableByName(entityClass.getSimpleName());
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
  public ITime getSchemaTimestamp() {
    return schemaTimestamp;
  }

  @Override
  public DatabaseObjectState getState() {

    if (isClosed()) {
      return DatabaseObjectState.CLOSED;
    }

    if (internalGetStoredDataAndSchemaAdapter().hasChanges()) {
      return DatabaseObjectState.EDITED;
    }

    return DatabaseObjectState.LOADED;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <E extends IEntity> IDatabase insertEntity(final E entity) {

    getStoredTableByEntityType((Class<E>) entity.getClass()).insertEntity(entity);

    return this;
  }

  @Override
  public boolean isClosed() {
    return internalGetStoredDataAndSchemaAdapter().isClosed();
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
  public boolean isLinkedWithRealDatabase() {
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

  void internalClose() {
    for (final var t : getStoredTables()) {
      ((Table<?>) t).internalClose();
    }
  }

  IDataAndSchemaAdapter internalGetStoredDataAndSchemaAdapter() {
    return dataAndSchemaAdapter;
  }

  ISchema internalGetSchema() {
    return schema;
  }

  void internalReset() {
    for (final var t : getStoredTables()) {
      ((Table<?>) t).internalReset();
    }
  }

  private LinkedList<Table<IEntity>> loadTables() {
    return DATABASE_TABLE_LOADER.loadTablesForDatabase(this);
  }
}
