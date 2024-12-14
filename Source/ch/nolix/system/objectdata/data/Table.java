package ch.nolix.system.objectdata.data;

import java.util.Optional;

import ch.nolix.core.container.cachingcontainer.CachingProperty;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.datatool.TableTool;
import ch.nolix.system.objectdata.datavalidator.TableValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datatoolapi.ITableTool;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;

public final class Table<E extends IEntity> implements ITable<E> {

  private static final TableValidator TABLE_VALIDATOR = new TableValidator();

  private static final ITableTool TABLE_TOOL = new TableTool();

  private static final EntityLoader ENTITY_LOADER = new EntityLoader();

  private static final EntityMapper ENTITY_MAPPER = new EntityMapper();

  private final Database parentDatabase;

  private final String name;

  private final String id;

  private final Class<E> entityClass;

  private final CachingProperty<IContainer<IColumn>> columnsThatReferenceCurrentTable = new CachingProperty<>(
    () -> TABLE_TOOL.getColumsThatReferenceGivenTable(this));

  private boolean loadedAllEntitiesInLocalData;

  private final LinkedList<IColumn> columns = LinkedList.createEmpty();

  private final LinkedList<E> entitiesInLocalData = LinkedList.createEmpty();

  private Table(
    final Database parentDatabase,
    final String name,
    final String id,
    final Class<E> entityClass) {

    GlobalValidator.assertThat(parentDatabase).thatIsNamed("parent Database").isNotNull();
    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();
    GlobalValidator.assertThat(entityClass).thatIsNamed("entity class").isNotNull();

    this.parentDatabase = parentDatabase;
    this.name = name;
    this.id = id;
    this.entityClass = entityClass;
  }

  static <E2 extends IEntity> Table<E2> withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
    final Database parentDatabase,
    final String name,
    final String id,
    final Class<E2> entityClass) {
    return new Table<>(parentDatabase, name, id, entityClass);
  }

  @Override
  public boolean containsEntityWithId(final String id) {
    return internalGetStoredDataAndSchemaAdapter().tableContainsEntityWithGivenId(getName(), id);
  }

  @Override
  public int getEntityCount() {
    return getStoredEntities().getCount();
  }

  @Override
  public Class<E> getEntityType() {
    return entityClass;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Optional<E> getOptionalStoredEntityById(final String id) {

    final var entity = internalGetStoredEntitiesInLocalData().getOptionalStoredFirst(e -> e.hasId(id));

    if (entity.isEmpty()) {

      if (internalGetStoredDataAndSchemaAdapter().tableContainsEntityWithGivenId(getName(), id)) {

        addEntityWithIdWhenIsNotAdded(id);

        return Optional.of(getStoredEntityByIdWhenIsInLocalData(id));
      }

      return Optional.empty();
    }

    return entity;
  }

  @Override
  public IContainer<IColumn> getStoredColumns() {
    return columns;
  }

  @Override
  public IContainer<E> getStoredEntities() {

    loadAllEntitiesInLocalDataIfNotLoaded();

    return entitiesInLocalData;
  }

  @Override
  public E getStoredEntityById(final String id) {

    final var entity = internalGetStoredEntitiesInLocalData().getOptionalStoredFirst(e -> e.hasId(id));

    if (entity.isEmpty()) {

      addEntityWithIdWhenIsNotAdded(id);

      return getStoredEntityByIdWhenIsInLocalData(id);
    }

    return entity.get();
  }

  @Override
  public IDatabase getStoredParentDatabase() {
    return parentDatabase;
  }

  @Override
  public DatabaseObjectState getState() {
    return parentDatabase.getState();
  }

  @Override
  public ITable<E> insertEntity(final E entity) {

    //The Entity must know its Table that it can be inserted into the Table.
    ((BaseEntity) entity).internalSetParentTable(this);

    TABLE_VALIDATOR.assertCanInsertEntity(this, entity);

    insertWhenCanBeInserted(entity);

    return this;
  }

  @Override
  public boolean isClosed() {
    return parentDatabase.isClosed();
  }

  @Override
  public boolean isDeleted() {
    return false;
  }

  @Override
  public boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  @Override
  public boolean isLinkedWithRealDatabase() {
    return parentDatabase.isLinkedWithRealDatabase();
  }

  @Override
  public boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  @Override
  public boolean isNew() {
    return false;
  }

  @Override
  public IContainer<E> internalGetStoredEntitiesInLocalData() {
    return entitiesInLocalData;
  }

  void internalAddColumn(final IColumn column) {
    columns.addAtEnd(column);
  }

  @SuppressWarnings("unchecked")
  void internalClose() {
    ((IContainer<BaseEntity>) internalGetStoredEntitiesInLocalData()).forEach(BaseEntity::internalClose);
  }

  IContainer<IColumn> internalGetColumnsThatReferencesCurrentTable() {
    return columnsThatReferenceCurrentTable.getValue();
  }

  IDataAndSchemaAdapter internalGetStoredDataAndSchemaAdapter() {
    return parentDatabase.internalGetStoredDataAndSchemaAdapter();
  }

  @SuppressWarnings("unchecked")
  void internalReset() {

    ((IContainer<BaseEntity>) internalGetStoredEntitiesInLocalData()).forEach(BaseEntity::internalClose);

    loadedAllEntitiesInLocalData = false;
    entitiesInLocalData.clear();
  }

  void internalSetColumns(final IContainer<IColumn> columns) {
    this.columns.clear();
    this.columns.addAtEnd(columns);
  }

  private void addEntityWithIdWhenIsNotAdded(final String id) {

    final var entity = ENTITY_LOADER.loadEntityById(this, id, internalGetStoredDataAndSchemaAdapter());

    entitiesInLocalData.addAtEnd(entity);
  }

  private E getStoredEntityByIdWhenIsInLocalData(final String id) {
    return internalGetStoredEntitiesInLocalData().getStoredFirst(e -> e.hasId(id));
  }

  private void insertEntityFromGivenLoadedEntityDtoInLocalDataIfNotInserted(ILoadedEntityDto loadedEntity) {
    if (!TABLE_TOOL.containsEntityWithGivenIdInLocalData(this, loadedEntity.getId())) {

      final var entity = ENTITY_MAPPER.createLoadedEntityFromDto(loadedEntity, this);

      entitiesInLocalData.addAtEnd(entity);
    }
  }

  private void insertWhenCanBeInserted(final E entity) {

    entitiesInLocalData.addAtEnd(entity);

    ((BaseEntity) entity).internalNoteInsertIntoDatabase();
  }

  private void loadAllEntitiesInLocalDataIfNotLoaded() {
    if (!loadedAllEntitiesInLocalData()) {
      loadAllEntitiesInLocalDataWhenNotLoadedAll();
    }
  }

  private void loadAllEntitiesInLocalDataWhenNotLoadedAll() {

    for (final var r : internalGetStoredDataAndSchemaAdapter().loadEntitiesOfTable(getName())) {
      insertEntityFromGivenLoadedEntityDtoInLocalDataIfNotInserted(r);
    }

    loadedAllEntitiesInLocalData = true;
  }

  private boolean loadedAllEntitiesInLocalData() {
    return loadedAllEntitiesInLocalData;
  }
}
