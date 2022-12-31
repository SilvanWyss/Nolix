//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectdatabase.propertyhelper.MultiValueHelper;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IMultiValueHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;

//class
public final class MultiValue<V> extends BaseValue<V> implements IMultiValue<DataImplementation, V> {
	
	//static attribute
	private static final IMultiValueHelper multiValueHelper = new MultiValueHelper();
	
	//attribute
	private boolean loadedValues;
	
	//multi-attribute
	private final LinkedList<V> values = new LinkedList<>();
	
	//method
	@Override
	public void addValue(final V value) {
		
		assertCanAddGivenValue(value);
		
		updateStateForAddValue(value);
		
		updateRecordForAddValue(value);
		
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
	public IContainer<V> getRefValues() {
		
		loadValuesIfNotLoaded();
		
		return values;
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
	public IContentFieldDTO technicalToContentField() {
		return new ContentFieldDTO(getName(), StringCatalogue.EMPTY_STRING);
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		GlobalValidator.assertThat(content).thatIsNamed(LowerCaseCatalogue.CONTENT).isNull();
	}
	
	//method
	private void assertCanAddGivenValue(final V value) {
		multiValueHelper.assertCanAddGivenValue(this, value);
	}
	
	//method
	private void assertCanClear() {
		multiValueHelper.assertCanClear(this);
	}
	
	//method
	private void clearWhenContainsAny() {
		
		assertCanClear();
		
		updateStateForClear();
		
		updateRecordForClear();
		
		setAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	private boolean loadedValues() {
		return loadedValues;
	}
	
	//method
	private void loadValuesIfNotLoaded() {
		if (!loadedValues()) {
			loadValuesWhenNotLoaded();
		}
	}
	
	//method	
	private void loadValuesWhenNotLoaded() {
		
		loadedValues = true;
		
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().loadEntriesOfMultiValue(
				getRefParentEntity().getParentTableName(),
				getRefParentEntity().getId(),
				getName()
			);
		}
	}
	
	//method
	private void updateRecordForAddValue(final V value) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().insertMultiValueEntry(
				getRefParentEntity().getParentTableName(),
				getRefParentEntity().getId(),
				getName(),
				value.toString()
			);
		}
	}
	
	//method
	private void updateRecordForClear() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().deleteMultiValueEntries(
				getRefParentEntity().getParentTableName(),
				getRefParentEntity().getId(),
				getName()
			);
		}
	}
	
	//method
	private void updateStateForAddValue(final V value) {
		values.addAtEnd(value);
	}
	
	//method
	private void updateStateForClear() {
		values.clear();
	}
}
