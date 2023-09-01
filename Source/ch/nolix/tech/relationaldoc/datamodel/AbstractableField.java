//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractableFieldValidator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IContent;

//class
public final class AbstractableField extends Entity implements IAbstractableField {
	
	//constant
	public static final boolean DEFAULT_ABSTRACT_FLAG = false;
	
	//constant
	public static final Cardinality DEFAULT_CARDINALITY = Cardinality.TO_ONE;
	
	//constant
	private static final AbstractableFieldValidator ABSTRACTABLE_FIELD_VALIDATOR = new AbstractableFieldValidator();
	
	//attribute
	private final BackReference<AbstractableObject> parentObject =
	BackReference.forEntityAndBackReferencedPropertyName(AbstractableObject.class, "nonInheritedFields");
	
	//attribute
	private final Value<String> name = new Value<>();
	
	//attribute
	private final Value<Boolean> abstractFlag = Value.withInitialValue(DEFAULT_ABSTRACT_FLAG);
	
	//attribute
	private final Value<String> cardinality = Value.withInitialValue(DEFAULT_CARDINALITY.toString());
	
	//method
	@Override
	public Cardinality getCardinality() {
		
		if (inheritsFromBaseField()) {
			return getStoredBaseField().getCardinality();
		}
		
		return Cardinality.valueOf(cardinality.getStoredValue());
	}
	
	//method
	@Override
	public String getName() {
		
		if (inheritsFromBaseField()) {
			return getStoredBaseField().getName();
		}
		
		return name.getStoredValue();
	}
	
	//method
	@Override
	public IAbstractableField getStoredBaseField() {
		
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IContent getStoredContent() {
		
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IAbstractableObject getStoredParentObject() {
		return parentObject.getBackReferencedEntity();
	}
	
	//method
	@Override
	public boolean inheritsFromBaseField() {
		
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public boolean isAbstract() {
		return abstractFlag.getStoredValue();
	}
	
	//method
	@Override
	public IAbstractableField setAsAbstract() {
		
		ABSTRACTABLE_FIELD_VALIDATOR.assertCanSetAsAbstract(this);
		
		abstractFlag.setValue(true);
		
		return this;
	}
	
	//method
	@Override
	public IAbstractableField setAsConcrete() {
		
		//TODO: Implement.
		return this;
	}
	
	//method
	@Override
	public IAbstractableField setCardinality(final Cardinality cardinality) {
		
		//TODO: Implement.
		return this;
	}
	
	//method
	@Override
	public IAbstractableField setContent(final IContent content) {
		
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IAbstractableField setName(final String name) {
		
		if (inheritsFromBaseField()) {
			getStoredBaseField().setName(name);
		} else {
			
			ABSTRACTABLE_FIELD_VALIDATOR.assertCanSetName(this, name);
			
			this.name.setValue(name);
		}
		
		return this;
	}
}
