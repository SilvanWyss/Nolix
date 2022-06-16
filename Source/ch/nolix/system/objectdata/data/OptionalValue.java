//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;
import ch.nolix.system.objectdata.propertyhelper.OptionalValueHelper;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IOptionalValueHelper;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDTO;

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
		
		updateRecordForClear();
		
		internalSetParentEntityAsEditedAndRunProbableUpdateAction();
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
		
		updateRecordForSetValue(value);
		
		internalSetParentEntityAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	@Override
	public void setValueFromStringRepresentation(final String string) {
		
		@SuppressWarnings("unchecked")
		final var value = (V)DataType.forType(optionalValueHelper.getDataType(this)).createValueFromString(string);
		
		setValue(value);
	}
	
	//method
	@Override
	public IContentFieldDTO technicalToContentField() {
		
		if (isEmpty()) {
			return new ContentFieldDTO(getName());
		}
		
		return new ContentFieldDTO(getName(), getRefValue().toString());
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
		
		GlobalValidator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		internalValue = value;
	}
	
	//method
	private void updateRecordForClear() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getParentTableName(),
				optionalValueHelper.createRecordUpdateDTOForClear(this)
			);
		}
	}
	
	//method
	private void updateRecordForSetValue(final V value) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getParentTableName(),
				optionalValueHelper.createRecordUpdateDTOForSetValue(this, value)
			);
		}
	}
}
