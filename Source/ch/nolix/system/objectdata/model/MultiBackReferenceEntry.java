package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiBackReferenceEntry;

public final class MultiBackReferenceEntry<E extends IEntity> implements IMultiBackReferenceEntry<E> {

  private final IMultiBackReference<E> parentMultiBackReference;

  private DatabaseObjectState state;

  private final String backReferencedEntityId;

  private MultiBackReferenceEntry(
    final IMultiBackReference<E> parentMultiBackReference,
    final DatabaseObjectState initialState,
    final String backReferencedEntityId) {

    GlobalValidator.assertThat(parentMultiBackReference).thatIsNamed("parent MultiBackReference").isNotNull();
    GlobalValidator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
    GlobalValidator.assertThat(backReferencedEntityId).thatIsNamed("back referenced entity id").isNotBlank();

    this.parentMultiBackReference = parentMultiBackReference;
    state = initialState;
    this.backReferencedEntityId = backReferencedEntityId;
  }

  public static <E2 extends IEntity> MultiBackReferenceEntry<E2> loadedEntryForMultiBackReferenceAndReferencedEntityId(
    final IMultiBackReference<E2> multiBackReference,
    final String backReferencedEntityId) {
    return new MultiBackReferenceEntry<>(multiBackReference, DatabaseObjectState.LOADED, backReferencedEntityId);
  }

  public static <E2 extends IEntity> MultiBackReferenceEntry<E2> newEntryForMultiBackReferenceAndReferencedEntityId(
    final IMultiBackReference<E2> multiBackReference,
    final String backReferencedEntityId) {
    return new MultiBackReferenceEntry<>(multiBackReference, DatabaseObjectState.NEW, backReferencedEntityId);
  }

  @Override
  public String getBackReferencedEntityId() {
    return backReferencedEntityId;
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
  public boolean isDeleted() {
    return getStoredParentMultiBackReference().isDeleted();
  }

  @Override
  public boolean isEdited() {
    return false;
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return getStoredParentMultiBackReference().isConnectedWithRealDatabase();
  }

  @Override
  public boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  @Override
  public boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  void internalDelete() {
    state = DatabaseObjectState.DELETED;
  }
}
