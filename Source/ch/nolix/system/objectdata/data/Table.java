//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.caching.CachingProperty;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.datatool.TableTool;
import ch.nolix.system.objectdata.datavalidator.TableValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datatoolapi.ITableTool;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;

//class
public final class Table<E extends IEntity> implements ITable<E> {

  //constant
  private static final TableValidator TABLE_VALIDATOR = new TableValidator();

  //constant
  private static final ITableTool TABLE_TOOL = new TableTool();

  //constant
  private static final EntityMapper ENTITY_MAPPER = new EntityMapper();

  //attribute
  private final Database parentDatabase;

  //attribute
  private final String name;

  //attribute
  private final String id;

  //attribute
  private final Class<E> entityClass;

  //attribute
  private final CachingProperty<IContainer<IColumn>> columnsThatReferenceCurrentTable = new CachingProperty<>(
    () -> TABLE_TOOL.getColumsThatReferenceGivenTable(this));

  //attribute
  private boolean loadedAllEntitiesInLocalData;

  //multi-attribute
  private final LinkedList<IColumn> columns = new LinkedList<>();

  //multi-attribute
  private final LinkedList<E> entitiesInLocalData = new LinkedList<>();

  //constructor
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

  //static method
  static <E2 extends IEntity> Table<E2> withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
    final Database parentDatabase,
    final String name,
    final String id,
    final Class<E2> entityClass) {
    return new Table<>(parentDatabase, name, id, entityClass);
  }

  //method
  @Override
  public boolean containsEntityWithId(final String id) {
    return internalGetRefDataAndSchemaAdapter().tableContainsEntityWithGivenId(getName(), id);
  }

  //method
  @Override
  public int getEntityCount() {
    return getStoredEntities().getElementCount();
  }

  //method
  @Override
  public Class<E> getEntityType() {
    return entityClass;
  }

  //method
  @Override
  public String getId() {
    return id;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public Optional<E> getOptionalStoredEntityById(final String id) {

    final var entity = technicalGetRefEntitiesInLocalData().getOptionalStoredFirst(e -> e.hasId(id));

    if (entity.isEmpty()) {

      if (internalGetRefDataAndSchemaAdapter().tableContainsEntityWithGivenId(getName(), id)) {

        addEntityWithIdWhenIsNotAdded(id);

        return Optional.of(getStoredEntityByIdWhenIsInLocalData(id));
      }

      return Optional.empty();
    }

    return entity;
  }

  //method
  @Override
  public IContainer<IColumn> getStoredColumns() {
    return columns;
  }

  //method
  @Override
  public IContainer<E> getStoredEntities() {

    loadAllEntitiesInLocalDataIfNotLoaded();

    return entitiesInLocalData;
  }

  //method
  @Override
  public E getStoredEntityById(final String id) {

    final var entity = technicalGetRefEntitiesInLocalData().getOptionalStoredFirst(e -> e.hasId(id));

    if (entity.isEmpty()) {

      addEntityWithIdWhenIsNotAdded(id);

      return getStoredEntityByIdWhenIsInLocalData(id);
    }

    return entity.get();
  }

  //method
  @Override
  public IDatabase getStoredParentDatabase() {
    return parentDatabase;
  }

  //method
  @Override
  public DatabaseObjectState getState() {
    return parentDatabase.getState();
  }

  //method
  @Override
  public ITable<E> insertEntity(final E entity) {

    @SuppressWarnings("unchecked")
    final var table = (ITable<IEntity>) this;

    //The inserted Entity must know its Table to be inserted.
    ((BaseEntity) entity).internalSetParentTable(table);

    TABLE_VALIDATOR.assertCanInsertGivenEntity(this, entity);

    insertWhenCanBeInserted(entity);

    return this;
  }

  //method
  @Override
  public boolean isClosed() {
    return parentDatabase.isClosed();
  }

  //method
  @Override
  public boolean isDeleted() {
    return false;
  }

  //method
  @Override
  public boolean isLinkedWithRealDatabase() {
    return parentDatabase.isLinkedWithRealDatabase();
  }

  //method
  @Override
  public boolean isNew() {
    return false;
  }

  //method
  @Override
  public IContainer<E> technicalGetRefEntitiesInLocalData() {
    return entitiesInLocalData;
  }

  //method
  void internalAddColumn(final IColumn column) {
    columns.addAtEnd(column);
  }

  //method
  @SuppressWarnings("unchecked")
  void internalClose() {
    ((IContainer<BaseEntity>) technicalGetRefEntitiesInLocalData()).forEach(BaseEntity::internalClose);
  }

  //method
  IContainer<IColumn> internalGetColumnsThatReferencesCurrentTable() {
    return columnsThatReferenceCurrentTable.getValue();
  }

  //method
  IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
    return parentDatabase.internalGetRefDataAndSchemaAdapter();
  }

  //method
  @SuppressWarnings("unchecked")
  void internalReset() {

    ((IContainer<BaseEntity>) technicalGetRefEntitiesInLocalData()).forEach(BaseEntity::internalClose);

    loadedAllEntitiesInLocalData = false;
    entitiesInLocalData.clear();
  }

  //method
  void internalSetColumns(final IContainer<IColumn> columns) {
    this.columns.clear();
    this.columns.addAtEnd(columns);
  }

  //method
  private void addEntityWithIdWhenIsNotAdded(final String id) {
    entitiesInLocalData.addAtEnd(loadEntityById(id));
  }

  //method
  @SuppressWarnings("unchecked")
  private E createLoadedEntityFromDto(ILoadedEntityDto loadedEntityDto) {
    return (E) ENTITY_MAPPER.createLoadedEntityFromDto(loadedEntityDto, (Table<BaseEntity>) this);
  }

  //method
  private E getStoredEntityByIdWhenIsInLocalData(final String id) {
    return technicalGetRefEntitiesInLocalData().getStoredFirst(e -> e.hasId(id));
  }

  //method
  private void insertEntityFromGivenLoadedEntityDtoInLocalDataIfNotInserted(ILoadedEntityDto loadedEntity) {
    if (!TABLE_TOOL.containsEntityWithGivenIdInLocalData(this, loadedEntity.getId())) {
      entitiesInLocalData.addAtEnd(createLoadedEntityFromDto(loadedEntity));
    }
  }

  //method
  private void insertWhenCanBeInserted(final E entity) {

    entitiesInLocalData.addAtEnd(entity);

    final var baseEntity = (BaseEntity) entity;
    baseEntity.internalUpdateProbableBackReferencesWhenIsNew();
    baseEntity.internalNoteInsert();
  }

  //method
  private void loadAllEntitiesInLocalDataIfNotLoaded() {
    if (!loadedAllEntitiesInLocalData()) {
      loadAllEntitiesInLocalDataWhenNotLoadedAll();
    }
  }

  //method
  private void loadAllEntitiesInLocalDataWhenNotLoadedAll() {

    for (final var r : internalGetRefDataAndSchemaAdapter().loadEntitiesOfTable(getName())) {
      insertEntityFromGivenLoadedEntityDtoInLocalDataIfNotInserted(r);
    }

    loadedAllEntitiesInLocalData = true;
  }

  //method
  private boolean loadedAllEntitiesInLocalData() {
    return loadedAllEntitiesInLocalData;
  }

  //method
  private E loadEntityById(final String id) {
    return createLoadedEntityFromDto(loadEntityDtoById(id));
  }

  //method
  private ILoadedEntityDto loadEntityDtoById(final String id) {
    return internalGetRefDataAndSchemaAdapter().loadEntity(getName(), id);
  }
}