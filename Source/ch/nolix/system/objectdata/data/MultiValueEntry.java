//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjectvalidator.DatabaseObjectValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValueEntry;

//class
public final class MultiValueEntry<V> implements IMultiValueEntry<V> {

  //constant
  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

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
    GlobalValidator.assertThat(value).thatIsNamed(LowerCaseVariableCatalogue.VALUE).isNotNull();

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
  public boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  //method
  @Override
  public boolean isLinkedWithRealDatabase() {
    return getStoredParentMultiValue().isLinkedWithRealDatabase();
  }

  //method
  @Override
  public boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  //method
  @Override
  public boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  //method
  void internalSetDeleted() {

    assertIsLoaded();

    state = DatabaseObjectState.DELETED;
  }

  //method
  private void assertIsLoaded() {
    DATABASE_OBJECT_VALIDATOR.assertIsLoaded(this);
  }
}
