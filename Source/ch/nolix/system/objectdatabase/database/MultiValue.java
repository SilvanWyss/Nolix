//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectdatabase.propertyvalidator.MultiValueValidator;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValueEntry;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IMultiValueValidator;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;

//class
public final class MultiValue<V> extends BaseValue<V> implements IMultiValue<DataImplementation, V> {
	
	//constant
	private static final IMultiValueValidator MULTI_VALUE_VALIDATOR = new MultiValueValidator();
	
	//attribute
	private boolean extractedValues;
	
	//multi-attribute
	private final LinkedList<MultiValueEntry<V>> localEntries = new LinkedList<>();
	
	//method
	@Override
	public void addValue(final V value) {
		
		assertCanAddValue(value);
		
		updateStateForAddValue(value);
		
		setAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	@Override
	public void clear() {
		if (containsAny()) {
			clearWhenContainsAny();
		}
	}
	
	//method
	@Override
	public IContainer<? extends IMultiValueEntry<DataImplementation, V>> getRefLocalEntries() {
		return localEntries;
	}
	
	//method
	@Override
	public IContainer<V> getRefValues() {
		
		extractValuesIfNeeded();
		
		return localEntries.to(IMultiValueEntry::getRefValue);
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.MULTI_VALUE;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return getRefValues().isEmpty();
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return false;
	}
	
	//method
	@Override
	public void removeValue(final V value) {
		
		MULTI_VALUE_VALIDATOR.assertCanRemoveValue(this, value);
		
		extractValuesIfNeeded();
		
		localEntries.getRefFirst(le -> le.getRefValue().equals(value)).internalSetDeleted();
	}
	
	//method
	@Override
	public IContentFieldDTO technicalToContentField() {
		return new ContentFieldDTO(getName());
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		GlobalValidator.assertThat(content).thatIsNamed(LowerCaseCatalogue.CONTENT).isNull();
	}
	
	//method
	private void assertCanAddValue(final V value) {
		MULTI_VALUE_VALIDATOR.assertCanAddGivenValue(this, value);
	}
	
	//method
	private void clearWhenContainsAny() {
		
		getRefValues().forEach(this::removeValue);
		
		setAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	private boolean extractedValues() {
		return extractedValues;
	}
	
	//method
	private void extractValuesIfNeeded() {
		if (!extractedValues()) {
			extractValuesWhenNeeded();
		}
	}
	
	//method	
	private void extractValuesWhenNeeded() {
		
		extractedValues = true;
		
		localEntries.addAtEnd(loadEntries());
	}
	
	//method
	@SuppressWarnings("unchecked")
	private IContainer<MultiValueEntry<V>> loadEntries() {
		return
		internalGetRefDataAndSchemaAdapter().loadMultiValueEntries(
			getRefParentEntity().getParentTableName(),
			getRefParentEntity().getId(),
			getName()
		)
		.to(mve -> MultiValueEntry.loadedEntryForMultiValueAndValue(this, (V)mve));
	}
	
	//method
	private void updateStateForAddValue(final V value) {
		localEntries.addAtEnd(MultiValueEntry.newEntryForMultiValueAndValue(this, value));
	}
}
