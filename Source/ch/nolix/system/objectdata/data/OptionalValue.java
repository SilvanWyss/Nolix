//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectdata.propertyhelper.OptionalValueHelper;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IOptionalValueHelper;

//class
public final class OptionalValue<V> extends BaseValue<V> implements IOptionalValue<DataImplementation, V> {
	
	//static attribute
	private static final IOptionalValueHelper optionalValueHelper = new OptionalValueHelper();
	
	//optional attribute
	private V internalValue;
	
	//method
	@Override
	public void clear() {
		
		internalValue = null;
		
		internalSetParentEntityAsEdited();
		
		updateRecordForClear();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (internalValue == null);
	}
	
	//method
	@Override
	public V getRefValue() {
		
		optionalValueHelper.assertHasValue(this);
		
		return internalValue;
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.OPTIONAL_VALUE;
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return false;
	}
	
	//method
	@Override
	public void setValue(final V value) {
		
		optionalValueHelper.assertCanSetGivenValue(this, value);
		
		updateStateForSetValue(value);
		
		internalSetParentEntityAsEdited();
		
		updateRecordForSetValue(value);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	void internalSetOrClearDirectlyFromContent(final Object content) {
		if (content == null) {
			internalValue = null;
		} else {
			internalValue = (V)content;
		}
	}
	
	//method
	private void updateStateForSetValue(final V value) {
		
		Validator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		internalValue = value;
	}
	
	//method
	private void updateRecordForClear() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getTableName(),
				optionalValueHelper.createRecordUpdateDTOForClear(this)
			);
		}
	}
	
	//method
	private void updateRecordForSetValue(final V value) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getTableName(),
				optionalValueHelper.createRecordUpdateDTOForSetValue(this, value)
			);
		}
	}
}
