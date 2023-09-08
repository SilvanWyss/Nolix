//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.OptionalValue;
import ch.nolix.tech.relationaldoc.datavalidator.ConcreteValueContentValidator;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
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
	public IConcreteValueContent addValue(final String value) {
		
		//TODO: Implement.
		return this;
	}
	
	//method
	@Override
	public IContainer<IConstraint<String>> getConstraints() {
		
		if (getStoredParentField().inheritsFromBaseField()) {
			return getConstraintsWhenInheritsFromBaseField();
		}
		
		return new ImmutableList<>();
	}

	//method
	@Override
	public DataType getDataType() {
		
		if (dataType.containsAny()) {
			return DataType.valueOf(dataType.getStoredValue());
		}
		
		final var baseField = getStoredParentField().getStoredBaseField();
		final var valueContent = (IValueContent)baseField.getStoredContent();
		return valueContent.getDataType();
	}
	
	//method
	@Override
	public IAbstractableField getStoredParentField() {
		return parentField.getBackReferencedEntity();
	}
	
	//method
	@Override
	public IContainer<String> getStoredValues() {
		
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public boolean isAbstract() {
		return false;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public void removeValueIfContainsEqualing(final String value) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void removeValues() {
		//TODO: Implement.
	}
	
	//method
	@Override
	public IConcreteValueContent setDataType(final DataType dataType) {
		
		CONCRETE_VALUE_CONTENT_VALIDATOR.assertCanSetDataType(this, dataType);
		
		setDataTypeIfWillChange(dataType);
		
		return this;
	}
	
	//method
	private IContainer<IConstraint<String>> getConstraintsWhenInheritsFromBaseField() {
		
		final var baseField = getStoredParentField().getStoredBaseField();
		final var abstractValueContent = (IAbstractValueContent)baseField.getStoredContent();
		
		return abstractValueContent.getConstraints();
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
