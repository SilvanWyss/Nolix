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
	
	//constructor
	public Value(final String name) {
		super(name);
	}
	
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
		
		Validator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		setValueWhenGivenValueIsNotNull(value);
	}
	
	//method
	private void setValueWhenGivenValueIsNotNull(final V value) {
		
		internalValue = value;
		
		updateRecordForSetValue(value);
	}
	
	//method
	private void updateRecordForSetValue(final V value) {
		if (isLinkedWithRealDatabase()) {
			getRefDataAdapter().updateRecordOnTable(
				getParentEntity().getParentTable().getName(),
				valueHelper.createRecordUpdateDTOForSetValue(this, value)
			);
		}
	}
}
