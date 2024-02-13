//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReferenceEntry;

//class
public final class MultiBackReferenceEntry<E extends IEntity> implements IMultiBackReferenceEntry<E> {

  //attribute
  private final IMultiBackReference<E> parentMultiBackReference;

  //attribute
  private DatabaseObjectState state;

  //attribute
  private final String backReferencedEntityId;

  //constructor
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

  //static method
  public static <E2 extends IEntity> MultiBackReferenceEntry<E2> loadedEntryForMultiBackReferenceAndReferencedEntityId(
    final IMultiBackReference<E2> multiBackReference,
    final String backReferencedEntityId) {
    return new MultiBackReferenceEntry<>(multiBackReference, DatabaseObjectState.LOADED, backReferencedEntityId);
  }

  //static method
  public static <E2 extends IEntity> MultiBackReferenceEntry<E2> newEntryForMultiBackReferenceAndReferencedEntityId(
    final IMultiBackReference<E2> multiBackReference,
    final String backReferencedEntityId) {
    return new MultiBackReferenceEntry<>(multiBackReference, DatabaseObjectState.NEW, backReferencedEntityId);
  }

  //method
  @Override
  public String getBackReferencedEntityId() {
    return backReferencedEntityId;
  }

  //method
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

  //method
  @Override
  public E getStoredBackReferencedEntity() {
    return getStoredParentMultiBackReference()
      .getStoredBackReferencedTable()
      .getStoredEntityById(getBackReferencedEntityId());
  }

  //method
  @Override
  public IMultiBackReference<E> getStoredParentMultiBackReference() {
    return parentMultiBackReference;
  }

  //method
  @Override
  public boolean isClosed() {
    return getStoredParentMultiBackReference().isClosed();
  }

  //method
  @Override
  public boolean isDeleted() {
    return getStoredParentMultiBackReference().isDeleted();
  }

  //method
  @Override
  public boolean isLinkedWithRealDatabase() {
    return getStoredParentMultiBackReference().isLinkedWithRealDatabase();
  }

  //method
  @Override
  public boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }
}
