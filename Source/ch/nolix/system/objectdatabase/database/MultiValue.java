//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.propertyvalidator.MultiValueValidator;
import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDto;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValueEntry;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IMultiValueValidator;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

//class
public final class MultiValue<V> extends BaseValue<V> implements IMultiValue<V> {
	
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
	public IContainer<? extends IMultiValueEntry<V>> getOriLocalEntries() {
		return localEntries;
	}
	
	//method
	@Override
	public IContainer<V> getOriValues() {
		
		extractValuesIfNeeded();
		
		return localEntries.to(IMultiValueEntry::getOriValue);
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.MULTI_VALUE;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return getOriValues().isEmpty();
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
		
		localEntries.removeAndGetRefFirst(le -> le.getOriValue().equals(value)).internalSetDeleted();
	}
	
	//method
	@Override
	public IContentFieldDto technicalToContentField() {
		return new ContentFieldDto(getName());
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
		
		getOriValues().forEach(this::removeValue);
		
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
			getOriParentEntity().getParentTableName(),
			getOriParentEntity().getId(),
			getName()
		)
		.to(mve -> MultiValueEntry.loadedEntryForMultiValueAndValue(this, (V)mve));
	}
	
	//method
	private void updateStateForAddValue(final V value) {
		localEntries.addAtEnd(MultiValueEntry.newEntryForMultiValueAndValue(this, value));
	}
}
