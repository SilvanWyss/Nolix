//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.OptionalValue;
import ch.nolix.tech.relationaldoc.datavalidator.ConcreteValueContentValidator;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteParameterizedValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IValueContent;

//class
public final class ConcreteValueContent extends ValueContent implements IConcreteValueContent {
	
	//constant
	private static final ConcreteValueContentValidator CONCRETE_VALUE_CONTENT_VALIDATOR =
	new ConcreteValueContentValidator();
	
	//attribute
	private final BackReference<AbstractableField> parentField =
	BackReference.forEntityAndBackReferencedPropertyName(AbstractableField.class, "concreteValueContent");
	
	//attribute
	private final OptionalValue<String> dataType = new OptionalValue<String>();
	
	//method
	@Override
	public DataType getDataType() {
		
		if (dataType.containsAny()) {
			return DataType.valueOf(dataType.getStoredValue());
		}
		
		return ((IValueContent)getStoredParentField().getStoredBaseField().getStoredContent()).getDataType();
	}
	
	//method
	@Override
	public IAbstractableField getStoredParentField() {
		return parentField.getBackReferencedEntity();
	}
	
	//method
	@Override
	public IConcreteParameterizedValueContent<?> getStoredConcreteParameterizedValueContent() {
		
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IConcreteValueContent setDataType(final DataType dataType) {
		
		CONCRETE_VALUE_CONTENT_VALIDATOR.assertCanSetDataType(this, dataType);
		
		setDataTypeIfWillChange(dataType);
		
		return this;
	}
	
	//method
	private void setDataTypeIfWillChange(final DataType dataType) {
		if (getDataType() != dataType) {
			setDataTypeWhenWillChange(dataType);
		}
	}
	
	//method
	private void setDataTypeWhenWillChange(final DataType dataType) {
		
		this.dataType.setValue(dataType.toString());
		
		//TODO: Update parameterizedConcreteValue.
	}
}
