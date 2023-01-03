//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.propertyhelper.ValueHelper;
import ch.nolix.system.objectdatabase.propertyvalidator.ValueValidator;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IValueHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IValueValidator;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;

//class
public final class Value<V> extends BaseValue<V> implements IValue<DataImplementation, V> {
	
	//static attribute
	private static final IValueHelper valueHelper = new ValueHelper();
	
	//static attribute
	private static final IValueValidator valueValidator = new ValueValidator();
	
	//static method
	public static <V2> Value<V2> withInitialValue(final V2 initialValue) {
		
		final var value = new Value<V2>();
		
		value.setValue(initialValue);
		
		return value;
	}
	
	//optional attribute
	private V internalValue;
	
	//method
	@Override
	public V getRefValue() {
		
		valueValidator.assertIsNotEmpty(this);
		
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
		
		valueValidator.assertCanSetGivenValue(this, value);
		
		updateStateForSetValue(value);
		
		setAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	@Override
	public void setValueFromString(final String string) {
		
		@SuppressWarnings("unchecked")
		final var value =
		(V)ValueCreator.INSTANCE.createValueOfDataTypeFromString(
			DataType.forType(valueHelper.getDataType(this)),
			string
		);
		
		setValue(value);
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
	private void updateStateForSetValue(final V value) {
		internalValue = value;
	}
}
