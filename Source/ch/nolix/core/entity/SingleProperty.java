//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.functionInterfaces.IElementTaker;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 210
 * @param <V> The type of the value of a single property.
 */
public abstract class SingleProperty<V extends Specified>
extends Propertyoid<V> {
	
	//attributes
	private final IElementTaker<V> setterMethod;
	private boolean approved = false;
	
	//optional attribute
	private V value;

	//package-visible constructor
	/**
	 * Creates new single property
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
	SingleProperty(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<Specification, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name, valueCreator);
		
		//Checks if the given setter method is not null.
		Validator
		.suppose(setterMethod)
		.thatIsNamed("setter method")
		.isNotNull();
		
		//Sets the setter method of this single property.
		this.setterMethod = setterMethod;
	}
	
	//method
	/**
	 * @return the value of this single property.
	 * @throws UnexistingAttributeException
	 * if this single property has no value.
	 */
	public final V getValue() {
		
		//Checks if this single property has a value.
		supposeHasValue();
		
		return value;
	}
	
	//method
	/**
	 * @return true if this single property is approved.
	 */
	public final boolean isApproved() {
		return approved;
	}
	
	//method
	/**
	 * @return true if this single property has no value.
	 */
	public boolean isEmpty() {
		return (value == null);
	}
	
	//abstract method
	/**
	 * @return true if this single property is optional.
	 */
	public abstract boolean isOptional();
	
	//method
	/**
	 * Sets the value of this single property.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 * @throws InvalidStateException
	 * if this single property is not approved when it is not mutable.
	 */
	public Propertyoid<V> setValue(final V value) {
		
		//Checks if the given value is not null.
		Validator
		.suppose(value)
		.thatIsNamed(VariableNameCatalogue.VALUE)
		.isNotNull();
		
		//Checks if this single property is not approved when it is not mutable.
		if (!isMutable()) {
			supposeIsNotApproved();
		}
		
		//Sets the value of this single property.
		this.value = value;
		
		return this;
	}
	
	//package-visible method
	final void addOrChangeValue(final V value) {
		setterMethod.run(value);
	}
	
	//package-visible method
	/**
	 * Approves this single property.
	 * 
	 * @throws InvalidStateException
	 * if this single property is not optional, but empty.
	 */
	final void approve() {
		
		//Checks if this single property is not empty when it is optional.
		if (!isOptional() && isEmpty()) {
			throw new InvalidStateException(
				this,
				"is not optional, but empty");
		}
	}
	
	//package-visible method
	/**
	 * Removes the value of this single property.
	 * 
	 * @return this  single property.
	 */
	Propertyoid<V> clear() {
		
		//Clears the value of this optional single property.
		value = null;
		
		return this;
	}
	
	//method
	/**
	 * @return the values of this single property.
	 */
	final ReadContainer<V> getRefValues() {
		
		//Handles the case that this single property is empty.
		if (isEmpty()) {
			return new ReadContainer<V>();
		}
		
		//Handles  the case that this single property is not empty.
		return new ReadContainer<V>(getValue());
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException
	 * if this optional single property has no value.
	 */
	private void supposeHasValue() {
		
		//Checks if this single property has a value.
		if (isEmpty()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.VALUE
			);
		}
	}
	
	//method
	/**
	 * @throws InvalidStateException if this single property is approved.
	 */
	private void supposeIsNotApproved() {
		
		//Checks if this single property is not approved.
		if (isApproved()) {
			throw new InvalidStateException(this, "is approved");
		}
	}
}
