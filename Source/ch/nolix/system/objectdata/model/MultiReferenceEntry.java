package ch.nolix.system.objectdata.model;

import java.util.Optional;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
import ch.nolix.systemapi.databaseobject.modelvalidator.IDatabaseObjectValidator;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.IDatabaseSearcher;
import ch.nolix.systemapi.objectdata.structure.EntityCache;

final class MultiReferenceEntry<E extends IEntity> implements IMultiReferenceEntry<E> {
  private static final IDatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final IDatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private final IMultiReference<E> parentMultiReference;

  private DatabaseObjectState state;

  private EntityCache<E> referencedEntityCache;

  private MultiReferenceEntry(
    final IMultiReference<E> parentMultiReference,
    final DatabaseObjectState initialState,
    final E referencedEntity) {
    Validator.assertThat(parentMultiReference).thatIsNamed("parent MultiReference").isNotNull();
    Validator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
    Validator.assertThat(referencedEntity).thatIsNamed("referenced entity").isNotNull();

    final var referencedEntityId = referencedEntity.getId();

    this.parentMultiReference = parentMultiReference;
    this.state = initialState;
    this.referencedEntityCache = new EntityCache<>(referencedEntityId, null, referencedEntity);
  }

  private MultiReferenceEntry(
    final IMultiReference<E> parentMultiReference,
    final DatabaseObjectState initialState,
    final String referencedEntityId,
    final String referencedTableId) {
    Validator.assertThat(parentMultiReference).thatIsNamed("parent MultiReference").isNotNull();
    Validator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
    Validator.assertThat(referencedEntityId).thatIsNamed("referenced entity id").isNotBlank();
    Validator.assertThat(referencedTableId).thatIsNamed("referenced table id").isNotBlank();

    this.parentMultiReference = parentMultiReference;
    this.state = initialState;
    this.referencedEntityCache = new EntityCache<>(referencedEntityId, referencedTableId, null);
  }

  public static <T extends IEntity> MultiReferenceEntry<T> //
  createLoadedEntryForMultiReferenceAndReferencedEntityIdAndReferencedTableId(
    final IMultiReference<T> multiReference,
    final String referencedEntityId,
    final String referencedTableId) {
    return //
    new MultiReferenceEntry<>(
      multiReference,
      DatabaseObjectState.UNEDITED,
      referencedEntityId,
      referencedTableId);
  }

  public static <T extends IEntity> MultiReferenceEntry<T> createNewEntryForMultiReferenceAndReferencedEntity(
    final IMultiReference<T> multiReference,
    final T referencedEntity) {
    return new MultiReferenceEntry<>(multiReference, DatabaseObjectState.NEW, referencedEntity);
  }

  public static <T extends IEntity> MultiReferenceEntry<T> //
  createNewEntryForMultiReferenceAndReferencedEntityIdAndReferencedTableId(
    final IMultiReference<T> multiReference,
    final String referencedEntityId,
    final String referencedTableId) {
    return new MultiReferenceEntry<>(multiReference, DatabaseObjectState.NEW, referencedEntityId, referencedTableId);
  }

  @Override
  public boolean belongsToDatabase() {
    return getStoredParentMultiReference().belongsToDatabase();
  }

  @Override
  public boolean belongsToTable() {
    return getStoredParentMultiReference().belongsToTable();
  }

  @Override
  public Optional<? extends IField> getOptionalStoredBaseBackReferenceWhoReferencesBackTheParentMultiReferenceOfThis() {
    return //
    getStoredReferencedEntity()
      .internalGetStoredFields()
      .getOptionalStoredFirst(p -> p.referencesBackField(getStoredParentMultiReference()));
  }

  @Override
  public String getReferencedEntityId() {
    return referencedEntityCache.entityId();
  }

  @Override
  public String getReferencedTableId() {
    retrieveReferencedTableId();

    return referencedEntityCache.nullableTableId();
  }

  @Override
  public String getReferencedTableName() {
    final var referencedEntity = referencedEntityCache.nullableEntity();

    if (referencedEntity != null) {
      return referencedEntity.getParentTableName();
    }

    return getStoredReferencedTable().getName();
  }

  @Override
  public DatabaseObjectState getState() {

    updateStateFromParentMultiReference();

    return state;
  }

  @Override
  public IDatabase getStoredParentDatabase() {
    return getStoredParentTable().getStoredParentDatabase();
  }

  @Override
  public IMultiReference<E> getStoredParentMultiReference() {
    return parentMultiReference;
  }

  @Override
  public ITable<? extends IEntity> getStoredParentTable() {
    return getStoredParentMultiReference().getStoredParentTable();
  }

  @Override
  public E getStoredReferencedEntity() {

    retrieveReferencedEntity();

    return referencedEntityCache.nullableEntity();
  }

  @Override
  @SuppressWarnings("unchecked")
  public ITable<E> getStoredReferencedTable() {
    //This part is not mandatory, but provides a better performance.
    final var referencedEntity = referencedEntityCache.nullableEntity();
    if (referencedEntity != null && referencedEntity.belongsToTable()) {
      return (ITable<E>) referencedEntity.getStoredParentTable();
    }

    if (belongsToDatabase()) {
      final var database = getStoredParentMultiReference().getStoredParentDatabase();

      return (ITable<E>) DATABASE_SEARCHER.getStoredTableById(database, getReferencedTableId());
    }

    final var database = referencedEntityCache.nullableEntity().getStoredParentDatabase();

    return (ITable<E>) DATABASE_SEARCHER.getStoredTableById(database, getReferencedTableId());
  }

  @Override
  public boolean isClosed() {
    return getStoredParentMultiReference().isClosed();
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return getStoredParentMultiReference().isConnectedWithRealDatabase();
  }

  @Override
  public boolean isDeleted() {
    return getStoredParentMultiReference().isDeleted();
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

  private void retrieveReferencedEntity() {
    var referencedEntity = referencedEntityCache.nullableEntity();

    if (referencedEntity == null) {
      final var referencedEntityId = referencedEntityCache.entityId();
      final var referencedTableId = referencedEntityCache.nullableTableId();
      referencedEntity = getStoredReferencedTable().getStoredEntityById(referencedEntityId);

      referencedEntityCache = new EntityCache<>(referencedEntityId, referencedTableId, referencedEntity);
    }
  }

  private void retrieveReferencedTableId() {
    var referencedTableId = referencedEntityCache.nullableTableId();

    if (referencedTableId == null) {
      final var referencedEntityId = referencedEntityCache.entityId();
      final var referencedEntity = referencedEntityCache.nullableEntity();
      referencedTableId = referencedEntity.getStoredParentTable().getId();

      referencedEntityCache = new EntityCache<>(referencedEntityId, referencedTableId, referencedEntity);
    }
  }

  private void updateStateFromParentMultiReference() {
    state = //
    switch (getStoredParentMultiReference().getState()) {
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
}
