//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.StringCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.objectdata.propertyhelper.MultiValueHelper;
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IMultiValueHelper;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;

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
		
		internalSetParentEntityAsEdited();
	}
	
	//method
	@Override
	public void clear() {
		
		assertCanClear();
		
		updateStateForClear();
		
		updateRecordForClear();
		
		internalSetParentEntityAsEdited();
	}
	
	//method
	@Override
	public int getElementCount() {
		
		loadValuesIfNotLoaded();
		
		return values.getElementCount();
	}
	
	//method
	@Override
	public V getRefAt(final int index) {
		
		loadValuesIfNotLoaded();
		
		return values.getRefAt(index);
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.MULTI_VALUE;
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return false;
	}
	
	//method
	@Override
	public Iterator<V> iterator() {
		
		loadValuesIfNotLoaded();
		
		return values.iterator();
	}
	
	//method
	@Override
	public IContentFieldDTO technicalToContentField() {
		return new ContentFieldDTO(getName(), StringCatalogue.EMPTY_STRING);
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		Validator.assertThat(content).thatIsNamed(LowerCaseCatalogue.CONTENT).isNull();
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
