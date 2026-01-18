/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import java.util.Optional;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.datastructure.property.LazyCalculatedProperty;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.entitytool.EntityCreator;
import ch.nolix.system.objectdata.entitytool.EntityFiller;
import ch.nolix.system.objectdata.modelexaminer.EntityExaminer;
import ch.nolix.system.objectdata.modelexaminer.TableExaminer;
import ch.nolix.system.objectdata.modelsearcher.TableSearcher;
import ch.nolix.system.objectdata.modelvalidator.TableValidator;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.objectdata.entitytool.IEntityCreator;
import ch.nolix.systemapi.objectdata.entitytool.IEntityFiller;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.IEntityExaminer;
import ch.nolix.systemapi.objectdata.modelexaminer.ITableExaminer;
import ch.nolix.systemapi.objectdata.modelsearcher.ITableSearcher;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity}s of a {@link Table}.
 */
public final class Table<E extends IEntity> implements ITable<E> {
  private static final TableValidator TABLE_VALIDATOR = new TableValidator();

  private static final ITableSearcher TABLE_TOOL = new TableSearcher();

  private static final ITableExaminer TABLE_EXAMINER = new TableExaminer();

  private static final IEntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IEntityExaminer ENTITY_EXAMINER = new EntityExaminer();

  private static final IEntityFiller ENTITY_FILLER = new EntityFiller();

  private final Database parentDatabase;

  private final String name;

  private final String memberId;

  private final Class<E> entityClass;

  private final LazyCalculatedProperty<IContainer<IColumn>> columnsThatReferenceCurrentTable = //
  LazyCalculatedProperty.forValueCreater(() -> TABLE_TOOL.getStoredColumsThatReferencesTable(this));

  private boolean loadedAllEntitiesInLocalData;

  private final ILinkedList<IColumn> memberColumns = LinkedList.createEmpty();

  private final ILinkedList<E> entitiesInLocalData = LinkedList.createEmpty();

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
    memberId = id;
    this.entityClass = entityClass;
  }

  static <T extends IEntity> Table<T> withParentDatabaseAndNameAndIdAndEntityType(
    final Database parentDatabase,
    final String name,
    final String id,
    final Class<T> entityType) {
    return new Table<>(parentDatabase, name, id, entityType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToDatabase() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsEntityWithId(final String id) {
    return getStoredMidDataDataAdapterAndSchemaReader().tableContainsEntity(getName(), id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getEntityCount() {
    var entityCount = getStoredMidDataDataAdapterAndSchemaReader().getEntityCount(getName());

    for (final var e : internalGetStoredEntitiesInLocalData()) {
      if (e.isNew()) {
        entityCount++;
      } else if //NOSONAR: When an Entity is new it is not deleted.
      (e.isDeleted()) {
        entityCount--;
      }
    }

    return entityCount;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<E> getEntityType() {
    return entityClass;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    return memberId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<E> getOptionalStoredEntityById(final String id) {
    final var entity = internalGetStoredEntitiesInLocalData().getOptionalStoredFirst(e -> e.hasId(id));

    if (entity.isEmpty()) {
      if (getStoredMidDataDataAdapterAndSchemaReader().tableContainsEntity(getName(), id)) {
        addEntityWithIdWhenIsNotAdded(id);

        return Optional.of(getStoredEntityByIdWhenIsInLocalData(id));
      }

      return Optional.empty();
    }

    return entity;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IColumn> getStoredColumns() {
    return memberColumns;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<E> getStoredEntities() {
    loadAllEntitiesInLocalDataIfNotLoaded();

    return entitiesInLocalData;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredEntityById(final String id) {
    final var entity = internalGetStoredEntitiesInLocalData().getOptionalStoredFirst(e -> e.hasId(id));

    if (entity.isEmpty()) {
      addEntityWithIdWhenIsNotAdded(id);

      return getStoredEntityByIdWhenIsInLocalData(id);
    }

    return entity.get();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IDatabase getStoredParentDatabase() {
    return parentDatabase;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseObjectState getState() {
    if (parentDatabase.isClosed()) {
      return DatabaseObjectState.CLOSED;
    }

    if (internalGetStoredEntitiesInLocalData().containsAny(ENTITY_EXAMINER::isNewOrEditedOrDeleted)) {
      return DatabaseObjectState.EDITED;
    }

    return DatabaseObjectState.UNEDITED;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITable<E> insertEntity(final E entity) {
    //The Entity must know its Table that it can be inserted into the Table.
    entity.internalSetParentTable(this);

    TABLE_VALIDATOR.assertCanInsertEntity(this, entity);

    executeInsertEntity(entity);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isClosed() {
    return parentDatabase.isClosed();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDeleted() {
    return false;
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
  public boolean isConnectedWithRealDatabase() {
    return parentDatabase.isConnectedWithRealDatabase();
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
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<E> internalGetStoredEntitiesInLocalData() {
    return entitiesInLocalData;
  }

  void close() {
    for (final var e : internalGetStoredEntitiesInLocalData()) {
      ((AbstractEntity) e).close();
    }
  }

  IDataAdapterAndSchemaReader getStoredMidDataDataAdapterAndSchemaReader() {
    return parentDatabase.getStoredMidDataAdapterAndSchemaReader();
  }

  void internalAddColumn(final IColumn column) {
    memberColumns.addAtEnd(column);
  }

  IContainer<IColumn> internalGetColumnsThatReferencesCurrentTable() {
    return columnsThatReferenceCurrentTable.getStoredValue();
  }

  void internalSetColumns(final IContainer<IColumn> columns) {
    memberColumns.clear();
    memberColumns.addAtEnd(columns);
  }

  private void addEntityWithIdWhenIsNotAdded(final String id) {
    final var entity = EntityLoader.loadEntityById(this, id, getStoredMidDataDataAdapterAndSchemaReader());

    entitiesInLocalData.addAtEnd(entity);
  }

  private void executeInsertEntity(final E entity) {
    entitiesInLocalData.addAtEnd(entity);

    ((AbstractEntity) entity).noteInsertIntoDatabase();
  }

  private E getStoredEntityByIdWhenIsInLocalData(final String id) {
    return internalGetStoredEntitiesInLocalData().getStoredFirst(e -> e.hasId(id));
  }

  private void insertEntityFromGivenLoadedEntityDtoInLocalDataIfNotInserted(EntityLoadingDto loadedEntity) {
    if (!TABLE_EXAMINER.containsEntityWithGivenIdInLocalData(this, loadedEntity.id())) {
      final var entity = ENTITY_CREATOR.createEmptyEntityForEntityType(getEntityType());

      entity.internalSetParentTable(this);

      ENTITY_FILLER.fillUpEntityFromEntityLoadingDto(entity, loadedEntity);

      entitiesInLocalData.addAtEnd(entity);
    }
  }

  private void loadAllEntitiesInLocalDataIfNotLoaded() {
    if (!loadedAllEntitiesInLocalData()) {
      loadAllEntitiesInLocalDataWhenNotLoadedAll();
    }
  }

  private void loadAllEntitiesInLocalDataWhenNotLoadedAll() {
    for (final var r : getStoredMidDataDataAdapterAndSchemaReader().loadEntities(getName())) {
      insertEntityFromGivenLoadedEntityDtoInLocalDataIfNotInserted(r);
    }

    loadedAllEntitiesInLocalData = true;
  }

  private boolean loadedAllEntitiesInLocalData() {
    return loadedAllEntitiesInLocalData;
  }
}
