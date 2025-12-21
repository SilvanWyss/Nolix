package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
import ch.nolix.systemapi.databaseobject.modelvalidator.IDatabaseObjectValidator;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.IDatabaseSearcher;
import ch.nolix.systemapi.objectdata.structure.EntityCache;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity} a
 *            {@link MultiBackReferenceEntry} references back.
 */
public final class MultiBackReferenceEntry<E extends IEntity> implements IMultiBackReferenceEntry<E> {
  private static final IDatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final IDatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private final IMultiBackReference<E> parentMultiBackReference;

  private DatabaseObjectState state;

  private EntityCache<E> backReferencedEntityCache;

  private MultiBackReferenceEntry(
    final IMultiBackReference<E> parentMultiBackReference,
    final DatabaseObjectState initialState,
    final E backReferencedEntity) {
    Validator.assertThat(parentMultiBackReference).thatIsNamed("parent MultiBackReference").isNotNull();
    Validator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
    Validator.assertThat(backReferencedEntity).thatIsNamed("back referenced entity").isNotNull();

    final var backReferencedEntityId = backReferencedEntity.getId();

    this.parentMultiBackReference = parentMultiBackReference;
    state = initialState;
    this.backReferencedEntityCache = new EntityCache<>(backReferencedEntityId, null, backReferencedEntity);
  }

  private MultiBackReferenceEntry(
    final IMultiBackReference<E> parentMultiBackReference,
    final DatabaseObjectState initialState,
    final String backReferencedEntityId,
    final String backReferencedTableId) {
    Validator.assertThat(parentMultiBackReference).thatIsNamed("parent MultiBackReference").isNotNull();
    Validator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
    Validator.assertThat(backReferencedEntityId).thatIsNamed("back referenced entity id").isNotBlank();
    Validator.assertThat(backReferencedTableId).thatIsNamed("back referenced table id").isNotBlank();

    this.parentMultiBackReference = parentMultiBackReference;
    this.state = initialState;
    this.backReferencedEntityCache = new EntityCache<>(backReferencedEntityId, backReferencedTableId, null);
  }

  public static <T extends IEntity> MultiBackReferenceEntry<T> //
  createLoadedEntryForMultiBackReferenceAndBackReferencedEntityIdAndBackReferencedTableId(
    final IMultiBackReference<T> multiBackReference,
    final String backReferencedEntityId,
    final String backReferencedTableId) {
    return //
    new MultiBackReferenceEntry<>(
      multiBackReference,
      DatabaseObjectState.UNEDITED,
      backReferencedEntityId,
      backReferencedTableId);
  }

  public static <T extends IEntity> MultiBackReferenceEntry<T> //
  createNewEntryForMultiBackReferenceAndBackReferencedEntityIdAndBackReferencedTableId(
    final IMultiBackReference<T> multiBackReference,
    final String backReferencedEntityId,
    final String backReferencedTableId) {
    return //
    new MultiBackReferenceEntry<>(
      multiBackReference,
      DatabaseObjectState.NEW,
      backReferencedEntityId,
      backReferencedTableId);
  }

  public static <T extends IEntity> MultiBackReferenceEntry<T> //
  createNewEntryForMultiBackReferenceAndBackReferencedEntity(
    final IMultiBackReference<T> multiBackReference,
    final T backReferencedEntity) {
    return //
    new MultiBackReferenceEntry<>(multiBackReference, DatabaseObjectState.NEW, backReferencedEntity);
  }

  @Override
  public boolean belongsToDatabase() {
    return getStoredParentMultiBackReference().belongsToDatabase();
  }

  @Override
  public boolean belongsToTable() {
    return getStoredParentMultiBackReference().belongsToTable();
  }

  @Override
  public String getBackReferencedEntityId() {
    return backReferencedEntityCache.entityId();
  }

  @Override
  public String getBackReferencedTableId() {
    retrieveBackReferencedTableId();

    return backReferencedEntityCache.nullableTableId();
  }

  @Override
  public DatabaseObjectState getState() {
    return switch (getStoredParentMultiBackReference().getState()) {
      case NEW ->
        DatabaseObjectState.NEW;
      case DELETED ->
        DatabaseObjectState.DELETED;
      case CLOSED ->
        DatabaseObjectState.CLOSED;
      default ->
        state;
    };
  }

  @Override
  public E getStoredBackReferencedEntity() {

    retrieveBackReferencedEntity();

    return backReferencedEntityCache.nullableEntity();
  }

  @Override
  @SuppressWarnings("unchecked")
  public ITable<E> getStoredBackReferencedTable() {
    //This part is not mandatory, but provides a better performance.
    final var backReferencedEntity = backReferencedEntityCache.nullableEntity();
    if (backReferencedEntity != null && backReferencedEntity.belongsToTable()) {
      return (ITable<E>) backReferencedEntity.getStoredParentTable();
    }

    if (belongsToDatabase()) {
      final var database = getStoredParentMultiBackReference().getStoredParentDatabase();

      return (ITable<E>) DATABASE_SEARCHER.getStoredTableById(database, getBackReferencedTableId());
    }

    final var database = backReferencedEntityCache.nullableEntity().getStoredParentDatabase();

    return (ITable<E>) DATABASE_SEARCHER.getStoredTableById(database, getBackReferencedTableId());
  }

  @Override
  public IDatabase getStoredParentDatabase() {
    return getStoredParentTable().getStoredParentDatabase();
  }

  @Override
  public IMultiBackReference<E> getStoredParentMultiBackReference() {
    return parentMultiBackReference;
  }

  @Override
  public ITable<? extends IEntity> getStoredParentTable() {
    return getStoredParentMultiBackReference().getStoredParentTable();
  }

  @Override
  public boolean isClosed() {
    return getStoredParentMultiBackReference().isClosed();
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return getStoredParentMultiBackReference().isConnectedWithRealDatabase();
  }

  @Override
  public boolean isDeleted() {
    return getStoredParentMultiBackReference().isDeleted();
  }

  @Override
  public boolean isEdited() {
    return false;
  }

  @Override
  public boolean isLoaded() {
    return (getState() == DatabaseObjectState.UNEDITED);
  }

  @Override
  public boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  void setDeleted() {
    DATABASE_OBJECT_VALIDATOR.assertIsLoaded(this);

    state = DatabaseObjectState.DELETED;
  }

  private void retrieveBackReferencedEntity() {
    var backReferencedEntity = backReferencedEntityCache.nullableEntity();

    if (backReferencedEntity == null) {
      final var backReferencedEntityId = backReferencedEntityCache.entityId();
      final var backReferencedTableId = backReferencedEntityCache.nullableTableId();
      backReferencedEntity = getStoredBackReferencedTable().getStoredEntityById(backReferencedEntityId);

      backReferencedEntityCache = //
      new EntityCache<>(backReferencedEntityId, backReferencedTableId, backReferencedEntity);
    }
  }

  private void retrieveBackReferencedTableId() {
    var backReferencedTableId = backReferencedEntityCache.nullableTableId();

    if (backReferencedTableId == null) {
      final var backReferencedEntityId = backReferencedEntityCache.entityId();
      final var backReferencedEntity = backReferencedEntityCache.nullableEntity();
      backReferencedTableId = backReferencedEntity.getStoredParentTable().getId();

      backReferencedEntityCache = //
      new EntityCache<>(backReferencedEntityId, backReferencedTableId, backReferencedEntity);
    }
  }
}
