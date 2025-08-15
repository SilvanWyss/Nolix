package ch.nolix.system.objectdata.model;

import java.util.function.Predicate;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.fieldvalidator.MultiValueValidator;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;
import ch.nolix.systemapi.objectdata.model.IMultiValueFieldEntry;

public final class MultiValueField<V> extends AbstractBaseValueField<V> implements IMultiValueField<V> {

  private static final DatabaseObjectExaminer DATABASE_OBJECT_TOOL = new DatabaseObjectExaminer();

  private static final MultiValueValidator MULTI_VALUE_VALIDATOR = new MultiValueValidator();

  private boolean loadedAllPersistedValues;

  private final LinkedList<MultiValueFieldEntry<V>> localEntries = LinkedList.createEmpty();

  private MultiValueField(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> MultiValueField<V2> withValueType(final Class<V2> valueType) {
    return new MultiValueField<>(valueType);
  }

  @Override
  public void addValue(final V value) {

    assertCanAddValue(value);

    updateStateForAddValue(value);

    setAsEditedAndRunPotentialUpdateAction();
  }

  @Override
  public void clear() {

    getAllStoredValues().forEach(this::removeValue);

    setAsEditedAndRunPotentialUpdateAction();
  }

  @Override
  public IContainer<V> getAllStoredValues() {

    updateStateForLoadAllPersistedValuesIfNotLoaded();

    return getStoredValuesFromAllNewOrLoadedOrEditedLocalEntries();
  }

  @Override
  public IContainer<? extends IMultiValueFieldEntry<V>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(DATABASE_OBJECT_TOOL::isNewOrDeleted);
  }

  @Override
  public FieldType getType() {
    return FieldType.MULTI_VALUE_FIELD;
  }

  @Override
  public void internalSetNullableContent(final Object nullableContent) {
    Validator.assertThat(nullableContent).thatIsNamed(LowerCaseVariableCatalog.CONTENT).isNull();
  }

  @Override
  public boolean isEmpty() {
    return localEntries.isEmpty()
    && isEmptyWhenDoesNotHaveLocalEntries();
  }

  @Override
  public boolean isMandatory() {
    return false;
  }

  @Override
  public boolean loadedAllPersistedValues() {
    return loadedAllPersistedValues;
  }

  @Override
  public void removeFirstValue(final Predicate<V> selector) {

    final var value = getAllStoredValues().getOptionalStoredFirst(selector);

    value.ifPresent(this::removeValue);
  }

  @Override
  public void removeValue(final V value) {

    MULTI_VALUE_VALIDATOR.assertCanRemoveValue(this, value);

    updateStateForLoadAllPersistedValuesIfNotLoaded();

    updateStateForRemoveValue(value);
  }

  private void assertCanAddValue(final V value) {
    MULTI_VALUE_VALIDATOR.assertCanAddGivenValue(this, value);
  }

  private IContainer<V> getStoredValuesFromAllNewOrLoadedOrEditedLocalEntries() {

    final ILinkedList<V> values = LinkedList.createEmpty();

    for (final var e : localEntries) {
      if (DATABASE_OBJECT_TOOL.isNewOrLoadedOrEdited(e)) {
        values.addAtEnd(e.getStoredValue());
      }
    }

    return values;
  }

  private boolean isEmptyWhenDoesNotHaveLocalEntries() {
    return getAllStoredValues().isEmpty();
  }

  @SuppressWarnings("unchecked")
  private IContainer<MultiValueFieldEntry<V>> loadAllPersistedValues() {
    return //
    getStoredDataAndSchemaAdapter().loadMultiValueValues(
      getStoredParentEntity().getStoredParentTable().getName(),
      getStoredParentEntity().getId(),
      getName())
      .to(mve -> MultiValueFieldEntry.loadedEntryForMultiValueAndValue(this, (V) mve));
  }

  private void updateStateForAddValue(final V value) {

    final var newEntry = MultiValueFieldEntry.newEntryForMultiValueAndValue(this, value);

    localEntries.addAtEnd(newEntry);
  }

  private void updateStateForLoadAllPersistedValues() {

    loadedAllPersistedValues = true;

    localEntries.addAtEnd(loadAllPersistedValues());
  }

  private void updateStateForLoadAllPersistedValuesIfNotLoaded() {
    if (!loadedAllPersistedValues()) {
      updateStateForLoadAllPersistedValues();
    }
  }

  private void updateStateForRemoveValue(final V value) {

    final var entry = localEntries.getStoredFirst(e -> e.getStoredValue() == value);

    entry.internalSetDeleted();
  }
}
