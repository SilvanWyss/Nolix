//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
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
	public void setValue(final V value) {
		
		setAttributeForSetValue(value);
		
		noteParentEntityForSetValue();
		
		updateRecordForSetValue(value);
	}
	
	//method
	private void noteParentEntityForSetValue() {
		if (belongsToEntity()) {
			internalGetParentEntity().internalSetEdited();
		}
	}
	
	//method
	private void setAttributeForSetValue(final V value) {
		
		Validator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		internalValue = value;
	}
	
	//method
	private void updateRecordForSetValue(final V value) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAdapter().updateRecordOnTable(
				getParentEntity().getParentTable().getName(),
				valueHelper.createRecordUpdateDTOForSetValue(this, value)
			);
		}
	}
}
