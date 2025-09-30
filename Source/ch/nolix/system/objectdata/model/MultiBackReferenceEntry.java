package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.systemapi.databaseobject.modelvalidator.IDatabaseObjectValidator;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;
import ch.nolix.systemapi.objectdata.structure.EntityCache;

public final class MultiBackReferenceEntry<E extends IEntity> implements IMultiBackReferenceEntry<E> {
  private static final IDatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

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

  public static <E2 extends IEntity> MultiBackReferenceEntry<E2> //
  createLoadedEntryForMultiBackReferenceAndBackReferencedEntityIdAndBackReferencedTableId(
    final IMultiBackReference<E2> multiBackReference,
    final String backReferencedEntityId,
    final String backReferencedTableId) {
    return //
    new MultiBackReferenceEntry<>(
      multiBackReference,
      DatabaseObjectState.UNEDITED,
      backReferencedEntityId,
      backReferencedTableId);
  }

  public static <E2 extends IEntity> MultiBackReferenceEntry<E2> //
  createNewEntryForMultiBackReferenceAndBackReferencedEntityIdAndBackReferencedTableId(
    final IMultiBackReference<E2> multiBackReference,
    final String backReferencedEntityId,
    final String backReferencedTableId) {
    return //
    new MultiBackReferenceEntry<>(
      multiBackReference,
      DatabaseObjectState.NEW,
      backReferencedEntityId,
      backReferencedTableId);
  }

  public static <E2 extends IEntity> MultiBackReferenceEntry<E2> //
  createNewEntryForMultiBackReferenceAndBackReferencedEntity(
    final IMultiBackReference<E2> multiBackReference,
    final E2 backReferencedEntity) {
    return //
    new MultiBackReferenceEntry<>(multiBackReference, DatabaseObjectState.NEW, backReferencedEntity);
  }

  @Override
  public String getBackReferencedEntityId() {
    return backReferencedEntityCache.entityId();
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
    return getStoredParentMultiBackReference()
      .getStoredBackReferencedTable()
      .getStoredEntityById(getBackReferencedEntityId());
  }

  @Override
  public IMultiBackReference<E> getStoredParentMultiBackReference() {
    return parentMultiBackReference;
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
}
