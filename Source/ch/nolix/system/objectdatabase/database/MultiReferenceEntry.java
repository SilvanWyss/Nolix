//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReferenceEntry;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;

//class
final class MultiReferenceEntry<E extends IEntity> implements IMultiReferenceEntry<E> {

  //constant
  private static final DatabaseObjectHelper DATABASE_OBJECT_HELPER = new DatabaseObjectHelper();

  //attribute
  private final IMultiReference<E> parentMultiReference;

  //attribute
  private DatabaseObjectState state;

  //attribute
  private final String referencedEntityId;

  //constructor
  private MultiReferenceEntry(
    final IMultiReference<E> parentMultiReference,
    final DatabaseObjectState initialState,
    final String referencedEntityId) {

    GlobalValidator.assertThat(parentMultiReference).thatIsNamed("parent MultiReference").isNotNull();
    GlobalValidator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
    GlobalValidator.assertThat(referencedEntityId).thatIsNamed("referenced entity id").isNotBlank();

    this.parentMultiReference = parentMultiReference;
    state = initialState;
    this.referencedEntityId = referencedEntityId;
  }

  //static method
  public static <E2 extends IEntity> MultiReferenceEntry<E2> loadedEntryForMultiReferenceAndReferencedEntityId(
    final IMultiReference<E2> multiReference,
    final String referencedEntityId) {
    return new MultiReferenceEntry<>(multiReference, DatabaseObjectState.LOADED, referencedEntityId);
  }

  //static method
  public static <E2 extends IEntity> MultiReferenceEntry<E2> newEntryForMultiReferenceAndReferencedEntityId(
    final IMultiReference<E2> multiReference,
    final String referencedEntityId) {
    return new MultiReferenceEntry<>(multiReference, DatabaseObjectState.NEW, referencedEntityId);
  }

  //method
  @Override
  public IProperty getStoredBackReferencingPropertyOrNull() {
    return getReferencedEntity()
      .technicalGetRefProperties()
      .getStoredFirstOrNull(p -> p.referencesBackProperty(getStoredParentMultiReference()));
  }

  //method
  @Override
  public IMultiReference<E> getStoredParentMultiReference() {
    return parentMultiReference;
  }

  //method
  @Override
  public DatabaseObjectState getState() {
    return switch (getStoredParentMultiReference().getState()) {
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
  public E getReferencedEntity() {
    return getStoredParentMultiReference().getReferencedTable().getStoredEntityById(getReferencedEntityId());
  }

  //method
  @Override
  public String getReferencedEntityId() {
    return referencedEntityId;
  }

  //method
  @Override
  public boolean isClosed() {
    return getStoredParentMultiReference().isClosed();
  }

  //method
  @Override
  public boolean isDeleted() {
    return getStoredParentMultiReference().isDeleted();
  }

  //method
  @Override
  public boolean isLinkedWithRealDatabase() {
    return getStoredParentMultiReference().isLinkedWithRealDatabase();
  }

  //method
  void internalSetDeleted() {

    assertIsLoaded();

    state = DatabaseObjectState.DELETED;
  }

  //method
  private void assertIsLoaded() {
    DATABASE_OBJECT_HELPER.assertIsLoaded(this);
  }
}
