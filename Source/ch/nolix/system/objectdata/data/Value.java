//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.system.objectdata.propertyhelper.ValueHelper;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectdataapi.dataapi.IValue;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IValueHelper;

//class
public final class Value<V> extends BaseValue<V> implements IValue<DataImplementation, V> {
	
	//static attribute
	private static final IValueHelper valueHelper = new ValueHelper();
	
	//optional attribute
	private V internalValue;
	
	//method
	@Override
	public V getRefValue() {
		
		valueHelper.assertHasValue(this);
		
		return internalValue;
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.VALUE;
	}
	
	//method
	@Override
	public boolean hasValue() {
		return (internalValue != null);
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return false;
	}
	
	//method
	@Override
	public void setValue(final V value) {
		
		valueHelper.assertCanSetGivenValue(this, value);
		
		updateStateForSetValue(value);
		
		internalSetParentEntityAsEdited();
		
		updateRecordForSetValue(value);
	}
	
	//method
	private void updateRecordForSetValue(final V value) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getTableName(),
				valueHelper.createRecordUpdateDTOForSetValue(this, value)
			);
		}
	}
	
	//method
	private void updateStateForSetValue(final V value) {
		internalValue = value;
	}
}
