//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.system.objectdata.propertyvalidator.MultiValueValidator;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValueEntry;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class MultiValue<V> extends BaseValue<V> implements IMultiValue<V> {

  //constant
  private static final DatabaseObjectTool DATABASE_OBJECT_TOOL = new DatabaseObjectTool();

  //constant
  private static final MultiValueValidator MULTI_VALUE_VALIDATOR = new MultiValueValidator();

  //attribute
  private boolean loadedAllPersistedValues;

  //multi-attribute
  private final LinkedList<MultiValueEntry<V>> localEntries = new LinkedList<>();

  //constructor
  private MultiValue(final Class<V> valueType) {
    super(valueType);
  }

  //static method
  public static <V2> MultiValue<V2> withValueType(final Class<V2> valueType) {
    return new MultiValue<>(valueType);
  }

  //method
  @Override
  public void addValue(final V value) {

    assertCanAddValue(value);

    updateStateForAddValue(value);

    setAsEditedAndRunPotentialUpdateAction();
  }

  //method
  @Override
  public void clear() {

    getAllStoredValues().forEach(this::removeValue);

    setAsEditedAndRunPotentialUpdateAction();
  }

  //method
  @Override
  public IContainer<V> getAllStoredValues() {

    updateStateForLoadAllPersistedValuesIfNotLoaded();

    return getStoredValuesFromAllNewOrLoadedOrEditedLocalEntries();
  }

  //method
  @Override
  public IContainer<? extends IMultiValueEntry<V>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(DATABASE_OBJECT_TOOL::isNewOrDeleted);
  }

  //method
  @Override
  public PropertyType getType() {
    return PropertyType.MULTI_VALUE;
  }

  //method
  @Override
  public boolean isEmpty() {
    return localEntries.isEmpty()
    && isEmptyWhenDoesNotHaveLocalEntries();
  }

  //method
  @Override
  public boolean isMandatory() {
    return false;
  }

  //method
  @Override
  public boolean loadedAllPersistedValues() {
    return loadedAllPersistedValues;
  }

  //method
  @Override
  public void removeValue(final V value) {

    MULTI_VALUE_VALIDATOR.assertCanRemoveValue(this, value);

    updateStateForLoadAllPersistedValuesIfNotLoaded();

    updateStateForRemoveValue(value);
  }

  //method
  @Override
  public IContentFieldDto internalToContentField() {
    return new ContentFieldDto(getName());
  }

  //method
  @Override
  void internalSetOrClearFromContent(final Object content) {
    GlobalValidator.assertThat(content).thatIsNamed(LowerCaseVariableCatalogue.CONTENT).isNull();
  }

  //method
  private void assertCanAddValue(final V value) {
    MULTI_VALUE_VALIDATOR.assertCanAddGivenValue(this, value);
  }

  //method
  private IContainer<V> getStoredValuesFromAllNewOrLoadedOrEditedLocalEntries() {

    final var values = new LinkedList<V>();

    for (final var e : localEntries) {
      if (DATABASE_OBJECT_TOOL.isNewOrLoadedOrEdited(e)) {
        values.addAtEnd(e.getStoredValue());
      }
    }

    return values;
  }

  //method
  private boolean isEmptyWhenDoesNotHaveLocalEntries() {
    return getAllStoredValues().isEmpty();

    //TODO: Implement.
    /*
     * if (loadedAllPersistedValues()) { return true; }
     * 
     * if (isLinkedWithRealDatabase()) {
     * 
     * final var entity = getStoredParentEntity(); final var column =
     * getStoredParentColumn();
     * 
     * return internalGetRefDataAndSchemaAdapter().multiValueIsEmpty(
     * entity.getParentTableName(), entity.getId(), column.getId()); }
     * 
     * return true;
     */
  }

  //method
  @SuppressWarnings("unchecked")
  private IContainer<MultiValueEntry<V>> loadAllPersistedValues() {
    return internalGetRefDataAndSchemaAdapter().loadMultiValueEntries(
      getStoredParentEntity().getParentTableName(),
      getStoredParentEntity().getId(),
      getName())
      .to(mve -> MultiValueEntry.loadedEntryForMultiValueAndValue(this, (V) mve));
  }

  //method
  private void updateStateForAddValue(final V value) {

    final var newEntry = MultiValueEntry.newEntryForMultiValueAndValue(this, value);

    localEntries.addAtEnd(newEntry);
  }

  //method
  private void updateStateForLoadAllPersistedValues() {
  
    loadedAllPersistedValues = true;
  
    localEntries.addAtEnd(loadAllPersistedValues());
  }

  //method
  private void updateStateForLoadAllPersistedValuesIfNotLoaded() {
    if (!loadedAllPersistedValues()) {
      updateStateForLoadAllPersistedValues();
    }
  }

  //method
  private void updateStateForRemoveValue(final V value) {

    final var entry = localEntries.getStoredFirst(e -> e.getStoredValue() == value);

    entry.internalSetDeleted();
  }
}
