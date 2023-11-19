//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValueEntry;

//class
public final class MultiValueEntry<V> implements IMultiValueEntry<V> {

  //constant
  private static final DatabaseObjectHelper DATABASE_OBJECT_HELPER = new DatabaseObjectHelper();

  //attribute
  private final IMultiValue<V> parentMultiValue;

  //attribute
  private DatabaseObjectState state;

  //attribute
  private final V value;

  //constructor
  private MultiValueEntry(
    final IMultiValue<V> parentMultiValue,
    final DatabaseObjectState initialState,
    final V value) {

    GlobalValidator.assertThat(parentMultiValue).thatIsNamed("parent MultiValue").isNotNull();
    GlobalValidator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
    GlobalValidator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();

    this.parentMultiValue = parentMultiValue;
    state = initialState;
    this.value = value;
  }

  //static method
  public static <V2> MultiValueEntry<V2> loadedEntryForMultiValueAndValue(
    final IMultiValue<V2> multiValue,
    final V2 value) {
    return new MultiValueEntry<>(multiValue, DatabaseObjectState.LOADED, value);
  }

  //static method
  public static <V2> MultiValueEntry<V2> newEntryForMultiValueAndValue(
    final IMultiValue<V2> multiValue,
    final V2 value) {
    return new MultiValueEntry<>(multiValue, DatabaseObjectState.NEW, value);
  }

  //method
  @Override
  public IMultiValue<V> getStoredParentMultiValue() {
    return parentMultiValue;
  }

  //method
  @Override
  public V getStoredValue() {
    return value;
  }

  //method
  @Override
  public DatabaseObjectState getState() {
    return switch (getStoredParentMultiValue().getState()) {
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
  public boolean isClosed() {
    return getStoredParentMultiValue().isClosed();
  }

  //method
  @Override
  public boolean isDeleted() {
    return getStoredParentMultiValue().isDeleted();
  }

  //method
  @Override
  public boolean isLinkedWithRealDatabase() {
    return getStoredParentMultiValue().isLinkedWithRealDatabase();
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
