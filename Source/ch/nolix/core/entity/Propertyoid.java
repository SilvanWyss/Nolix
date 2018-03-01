//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.functionInterfaces.IElementTakerRunner;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 210
 * @param <V> The type of the value of a property.
 */
public abstract class Propertyoid<V extends Specified>
extends NamedElement {
	
	//attributes
	private final IElementTakerRunner<V> setterMethod;
	private final IElementTakerElementGetter<Specification, V> valueCreator;
	private boolean approved = false;
	
	//optional attribute
	private V value;

	//package-visible constructor
	/**
	 * Creates new property
	 * with the given name, setter method and value creator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given setter method is null.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	Propertyoid(
		final String name,
		final IElementTakerRunner<V> setterMethod,
		final IElementTakerElementGetter<Specification, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the given setter method is not null.
		Validator
		.suppose(setterMethod)
		.thatIsNamed("setter method")
		.isNotNull();
		
		//Checks if the given value creator is not null.
		Validator
		.suppose(valueCreator)
		.thatIsNamed("value creator")
		.isNotNull();
		
		//Sets the setter method of this property.
		this.setterMethod = setterMethod;
		
		//Sets the value creator of this property.
		this.valueCreator = valueCreator;
	}
	
	//method
	/**
	 * @return the value of this property.
	 * @throws UnexistingAttributeException
	 * if this property has no value.
	 */
	public final V getValue() {
		
		//Checks if this property has a value.
		supposeHasValue();
		
		return value;
	}
	
	//method
	/**
	 * @return true if this property is approved.
	 */
	public final boolean isApproved() {
		return approved;
	}
	
	//method
	/**
	 * @return true if this property has no value.
	 */
	public boolean isEmpty() {
		return (value == null);
	}
	
	//abstract method
	/**
	 * @return true if this property is mutable.
	 */
	public abstract boolean isMutable();
	
	//abstract method
	/**
	 * @return true if this property is optional.
	 */
	public abstract boolean isOptional();
	
	//method
	/**
	 * Sets the value of this property.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 * @throws InvalidStateException
	 * if this property is not approved when it is not mutable.
	 */
	public Propertyoid<V> setValue(final V value) {
		
		//Checks if the given value is not null.
		Validator.suppose(value).thatIsNamed("value").isNotNull();
		
		//Checks if this property is not approved when it is not mutable.
		if (!isMutable()) {
			supposeIsNotApproved();
		}
		
		//Sets the value of this property.
		this.value = value;
		
		return this;
	}
	
	//package-visible method
	/**
	 * Approves this property.
	 * 
	 * @throws InvalidStateException
	 * if this property is not optional, but empty.
	 */
	final void approve() {
		
		//Checks if this property is not empty when it is optional.
		if (!isOptional() && isEmpty()) {
			throw new InvalidStateException(
				this,
				"is not optional, but empty");
		}
		
		approved = true;
	}
	
	//package-visible method
	/**
	 * Removes the value of this property.
	 * 
	 * @return this  property.
	 */
	Propertyoid<V> clear() {
		
		//Clears the value of this optional property.
		value = null;
		
		return this;
	}
	
	//package-visible method
	/**
	 * Sets the value of this property from the given specification.
	 * This method uses the setter method of this property.
	 * 
	 * @param specification
	 */
	final void setValueFromSpecification(final Specification specification) {
		setterMethod.run(valueCreator.getOutput(specification));
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException
	 * if this optional property has no value.
	 */
	private void supposeHasValue() {
		
		//Checks if this property has a value.
		if (isEmpty()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.VALUE
			);
		}
	}
	
	//method
	/**
	 * @throws InvalidStateException if this property is approved.
	 */
	private void supposeIsNotApproved() {
		
		//Checks if this property is not approved.
		if (isApproved()) {
			throw new InvalidStateException(this, "is approved");
		}
	}
}
