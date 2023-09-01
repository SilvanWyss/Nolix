//package declaraiton
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractValueContentValidator;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractParameterizedValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IValueContent;

//class
public final class AbstractValueContent extends ValueContent implements IAbstractValueContent {
	
	//constant
	public static final DataType DEFAULT_DATA_TYPE = DataType.TEXT;
	
	//constant
	private static final AbstractValueContentValidator ABSTRACT_VALUE_CONTENT_VALIDATOR =
	new AbstractValueContentValidator();
	
	//attribute
	private final BackReference<AbstractableField> parentField =
	BackReference.forEntityAndBackReferencedPropertyName(AbstractableField.class, "abstractValueContent");
	
	//attribute
	private final Value<String> dataType = Value.withInitialValue(DEFAULT_DATA_TYPE.toString());
	
	//constructor
	public AbstractValueContent() {
		initialize();
	}
	
	//method
	@Override
	public DataType getDataType() {
		
		final var parentField = getStoredParentField();
		if (parentField.inheritsFromBaseField()) {
			return ((IValueContent)parentField.getStoredBaseField().getStoredContent()).getDataType();
		}
		
		return DataType.valueOf(dataType.getStoredValue());
	}
	
	//method
	@Override
	public IAbstractParameterizedValueContent<?> getStoredAbstractParameterizedValueContent() {
		
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IAbstractableField getStoredParentField() {
		return parentField.getBackReferencedEntity();
	}
	
	//method
	@Override
	public IAbstractValueContent setDataType(final DataType dataType) {
		
		ABSTRACT_VALUE_CONTENT_VALIDATOR.assertCanSetDataType(this, dataType);
		
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
		
		clearRealisingFields();
	}
	
	//method
	private void clearRealisingFields() {
		
		final var localParentField = getStoredParentField();
		final var subTypes = getStoredParentField().getStoredParentObject().getStoredSubTypes();
		
		for (final var sb : subTypes) {
			
			final var field = sb.getStoredFields().getStoredFirstOrNull(f -> f.hasSameNameAs(localParentField));
			
			if (field != null) {
				final var concreteValueContent = (IConcreteValueContent)field.getStoredContent();
				concreteValueContent.getStoredConcreteParameterizedValueContent().removeValues();
			}
		}
	}
}
