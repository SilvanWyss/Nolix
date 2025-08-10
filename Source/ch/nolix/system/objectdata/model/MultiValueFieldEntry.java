package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.systemapi.databaseobject.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;
import ch.nolix.systemapi.objectdata.model.IMultiValueFieldEntry;

public final class MultiValueFieldEntry<V> implements IMultiValueFieldEntry<V> {

  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private final IMultiValueField<V> parentMultiValue;

  private DatabaseObjectState state;

  private final V value;

  private MultiValueFieldEntry(
    final IMultiValueField<V> parentMultiValue,
    final DatabaseObjectState initialState,
    final V value) {

    Validator.assertThat(parentMultiValue).thatIsNamed("parent MultiValue").isNotNull();
    Validator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    this.parentMultiValue = parentMultiValue;
    state = initialState;
    this.value = value;
  }

  public static <V2> MultiValueFieldEntry<V2> loadedEntryForMultiValueAndValue(
    final IMultiValueField<V2> multiValue,
    final V2 value) {
    return new MultiValueFieldEntry<>(multiValue, DatabaseObjectState.UNEDITED, value);
  }

  public static <V2> MultiValueFieldEntry<V2> newEntryForMultiValueAndValue(
    final IMultiValueField<V2> multiValue,
    final V2 value) {
    return new MultiValueFieldEntry<>(multiValue, DatabaseObjectState.NEW, value);
  }

  @Override
  public IMultiValueField<V> getStoredParentMultiValue() {
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
    return (getState() == DatabaseObjectState.UNEDITED);
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
