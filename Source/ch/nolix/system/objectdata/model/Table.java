package ch.nolix.system.objectdata.model;

import java.util.Optional;

import ch.nolix.core.container.cachingcontainer.CachingProperty;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.datatool.EntityCreator;
import ch.nolix.system.objectdata.datavalidator.TableValidator;
import ch.nolix.system.objectdata.modelexaminer.EntityExaminer;
import ch.nolix.system.objectdata.modelexaminer.TableExaminer;
import ch.nolix.system.objectdata.modelfiller.EntityFiller;
import ch.nolix.system.objectdata.modelsearcher.TableSearcher;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.middataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityCreator;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.IEntityExaminer;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.ITableExaminer;
import ch.nolix.systemapi.objectdataapi.modelfillerapi.IEntityFiller;
import ch.nolix.systemapi.objectdataapi.modelsearcherapi.ITableSearcher;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

public final class Table<E extends IEntity> implements ITable<E> {

  private static final TableValidator TABLE_VALIDATOR = new TableValidator();

  private static final ITableSearcher TABLE_TOOL = new TableSearcher();

  private static final ITableExaminer TABLE_EXAMINER = new TableExaminer();

  private static final IEntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IEntityExaminer ENTITY_EXAMINER = new EntityExaminer();

  private static final IEntityFiller ENTITY_FILLER = new EntityFiller();

  private final Database parentDatabase;

  private final String name;

  private final String id;

  private final Class<E> entityClass;

  private final CachingProperty<IContainer<IColumnView<ITable<IEntity>>>> columnsThatReferenceCurrentTable = //
  new CachingProperty<>(() -> TABLE_TOOL.getStoredColumsThatReferencesTable(this));

  private boolean loadedAllEntitiesInLocalData;

  private final LinkedList<IColumnView<ITable<IEntity>>> columnViews = LinkedList.createEmpty();

  private final LinkedList<E> entitiesInLocalData = LinkedList.createEmpty();

  private Table(
    final Database parentDatabase,
    final String name,
    final String id,
    final Class<E> entityClass) {

    Validator.assertThat(parentDatabase).thatIsNamed("parent Database").isNotNull();
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();
    Validator.assertThat(entityClass).thatIsNamed("entity class").isNotNull();

    this.parentDatabase = parentDatabase;
    this.name = name;
    this.id = id;
    this.entityClass = entityClass;
  }

  static <E2 extends IEntity> Table<E2> withParentDatabaseAndNameAndIdAndEntityType(
    final Database parentDatabase,
    final String name,
    final String id,
    final Class<E2> entityType) {
    return new Table<>(parentDatabase, name, id, entityType);
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
  public IContainer<IColumnView<ITable<IEntity>>> getStoredColumns() {
    return columnViews;
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

    if (parentDatabase.isClosed()) {
      return DatabaseObjectState.CLOSED;
    }

    if (internalGetStoredEntitiesInLocalData().containsAny(ENTITY_EXAMINER::isNewOrEditedOrDeleted)) {
      return DatabaseObjectState.EDITED;
    }

    return DatabaseObjectState.LOADED;
  }

  @Override
  public ITable<E> insertEntity(final E entity) {

    //The Entity must know its Table that it can be inserted into the Table.
    entity.internalSetParentTable(this);

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
  public boolean isConnectedWithRealDatabase() {
    return parentDatabase.isConnectedWithRealDatabase();
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

  void close() {
    for (final var e : internalGetStoredEntitiesInLocalData()) {
      ((AbstractEntity) e).close();
    }
  }

  void internalAddColumn(final IColumnView<ITable<IEntity>> columnView) {
    columnViews.addAtEnd(columnView);
  }

  IContainer<IColumnView<ITable<IEntity>>> internalGetColumnsThatReferencesCurrentTable() {
    return columnsThatReferenceCurrentTable.getValue();
  }

  IDataAdapterAndSchemaReader internalGetStoredDataAndSchemaAdapter() {
    return parentDatabase.getStoredRawDataAdapterAndSchemaReader();
  }

  void internalSetColumns(final IContainer<IColumnView<ITable<IEntity>>> columnViews) {
    this.columnViews.clear();
    this.columnViews.addAtEnd(columnViews);
  }

  private void addEntityWithIdWhenIsNotAdded(final String id) {

    final var entity = EntityLoader.loadEntityById(this, id, internalGetStoredDataAndSchemaAdapter());

    entitiesInLocalData.addAtEnd(entity);
  }

  private E getStoredEntityByIdWhenIsInLocalData(final String id) {
    return internalGetStoredEntitiesInLocalData().getStoredFirst(e -> e.hasId(id));
  }

  private void insertEntityFromGivenLoadedEntityDtoInLocalDataIfNotInserted(EntityLoadingDto loadedEntity) {
    if (!TABLE_EXAMINER.containsEntityWithGivenIdInLocalData(this, loadedEntity.id())) {

      final var entity = ENTITY_CREATOR.createEmptyEntityForTable(this);
      entity.internalSetParentTable(this);

      ENTITY_FILLER.fillUpEntityFromEntityLoadingDto(entity, loadedEntity);

      entitiesInLocalData.addAtEnd(entity);
    }
  }

  private void insertWhenCanBeInserted(final E entity) {

    entitiesInLocalData.addAtEnd(entity);

    ((AbstractEntity) entity).noteInsertIntoDatabase();
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
