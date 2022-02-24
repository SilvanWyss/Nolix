//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.system.objectdata.propertyhelper.ValueHelper;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IValueHelper;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDTO;

//class
public final class Value<V> extends BaseValue<V> implements IValue<DataImplementation, V> {
	
	//static attribute
	private static final IValueHelper valueHelper = new ValueHelper();
	
	//optional attribute
	private V internalValue;
	
	//method
	@Override
	public V getRefValue() {
		
		valueHelper.assertIsNotEmpty(this);
		
		return internalValue;
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.VALUE;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (internalValue == null);
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return true;
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
	@Override
	public IContentFieldDTO technicalToContentField() {
		return new ContentFieldDTO(getName(), getRefValue().toString());
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	void internalSetOrClearDirectlyFromContent(final Object content) {
		internalValue = (V)content;		
	}
	
	//method
	private void updateRecordForSetValue(final V value) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getParentTableName(),
				valueHelper.createRecordUpdateDTOForSetValue(this, value)
			);
		}
	}
	
	//method
	private void updateStateForSetValue(final V value) {
		internalValue = value;
	}
}
