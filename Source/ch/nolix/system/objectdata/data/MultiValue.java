//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.objectdata.propertyhelper.MultiValueHelper;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IMultiValueHelper;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDTO;

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
		
		internalSetParentEntityAsEditedAndRunProbableUpdateAction();
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
		
		internalSetParentEntityAsEditedAndRunProbableUpdateAction();
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
			internalGetRefDataAndSchemaAdapter().loadAllMultiValueEntriesFromRecord(
				getParentEntity().getParentTableName(),
				getParentEntity().getId(),
				getName()
			);
		}
	}

	//method
	private void updateRecordForAddValue(final V value) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().insertEntryIntoMultiValue(
				getParentEntity().getParentTableName(),
				getParentEntity().getId(),
				getName(),
				value.toString()
			);
		}
	}
	
	//method
	private void updateRecordForClear() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().deleteEntriesFromMultiValue(
				getParentEntity().getParentTableName(),
				getParentEntity().getId(),
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
