package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.validator.DatabaseObjectValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValueEntry;

public final class MultiValueEntry<V> implements IMultiValueEntry<V> {

  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private final IMultiValue<V> parentMultiValue;

  private DatabaseObjectState state;

  private final V value;

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

  public static <V2> MultiValueEntry<V2> loadedEntryForMultiValueAndValue(
    final IMultiValue<V2> multiValue,
    final V2 value) {
    return new MultiValueEntry<>(multiValue, DatabaseObjectState.LOADED, value);
  }

  public static <V2> MultiValueEntry<V2> newEntryForMultiValueAndValue(
    final IMultiValue<V2> multiValue,
    final V2 value) {
    return new MultiValueEntry<>(multiValue, DatabaseObjectState.NEW, value);
  }

  @Override
  public IMultiValue<V> getStoredParentMultiValue() {
    return parentMultiValue;
  }

  @Override
  public V getStoredValue() {
    return value;
  }

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

  @Override
  public boolean isClosed() {
    return getStoredParentMultiValue().isClosed();
  }

  @Override
  public boolean isDeleted() {
    return getStoredParentMultiValue().isDeleted();
  }

  @Override
  public boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return getStoredParentMultiValue().isConnectedWithRealDatabase();
  }

  @Override
  public boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  @Override
  public boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  void internalSetDeleted() {

    assertIsLoaded();

    state = DatabaseObjectState.DELETED;
  }

  private void assertIsLoaded() {
    DATABASE_OBJECT_VALIDATOR.assertIsLoaded(this);
  }
}
