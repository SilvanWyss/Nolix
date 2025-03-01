package ch.nolix.system.objectdata.model;

import java.util.function.Predicate;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.fieldvalidator.MultiValueValidator;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValueEntry;

public final class MultiValue<V> extends AbstractValue<V> implements IMultiValue<V> {

  private static final DatabaseObjectExaminer DATABASE_OBJECT_TOOL = new DatabaseObjectExaminer();

  private static final MultiValueValidator MULTI_VALUE_VALIDATOR = new MultiValueValidator();

  private boolean loadedAllPersistedValues;

  private final LinkedList<MultiValueEntry<V>> localEntries = LinkedList.createEmpty();

  private MultiValue(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> MultiValue<V2> withValueType(final Class<V2> valueType) {
    return new MultiValue<>(valueType);
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
  public IContainer<? extends IMultiValueEntry<V>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(DATABASE_OBJECT_TOOL::isNewOrDeleted);
  }

  @Override
  public ContentType getType() {
    return ContentType.MULTI_VALUE;
  }

  @Override
  public void internalSetOptionalContent(final Object content) {
    Validator.assertThat(content).thatIsNamed(LowerCaseVariableCatalog.CONTENT).isNull();
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
  private IContainer<MultiValueEntry<V>> loadAllPersistedValues() {
    return internalGetStoredDataAndSchemaAdapter().loadMultiValueEntries(
      getStoredParentEntity().getParentTableName(),
      getStoredParentEntity().getId(),
      getName())
      .to(mve -> MultiValueEntry.loadedEntryForMultiValueAndValue(this, (V) mve));
  }

  private void updateStateForAddValue(final V value) {

    final var newEntry = MultiValueEntry.newEntryForMultiValueAndValue(this, value);

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
