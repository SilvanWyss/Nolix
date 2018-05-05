//package declaration
package ch.nolix.core.entity2;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 230
 * @param <V> The type of the value of a property.
 */
public final class Property<V extends Specified> extends NamedElement {
	
	//attributes
	private final V defaultValue;
	private final IElementTakerElementGetter<Specification, V> valueCreator;
	
	//optional attributes
	private V value;
	private Property<V> baseProperty;
	
	//constructor
	/**
	 * Creates a new property with the given name, default value and value creator.
	 * 
	 * @param name
	 * @param defaultValue
	 * @param valueCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given default value is null.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	public Property(
		final String name,		
		final V defaultValue,
		final IElementTakerElementGetter<Specification, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the given default is not null.
		Validator
		.suppose(defaultValue)
		.thatIsNamed(VariableNameCatalogue.DEFAULT_VALUE)
		.isNotNull();
				
		//Checks if the given value creator is not null.
		Validator
		.suppose(valueCreator)
		.thatIsNamed("value creator")
		.isNotNull();
		
		//Sets the default value of this property.
		this.defaultValue = defaultValue;
		
		//Sets the value creator of this property.
		this.valueCreator = valueCreator;
	}
	
	//method
	/**
	 * @return the recursive or default value of this property.
	 */
	public V getRecursiveOrDefaultValue() {
	
		//Handles the case that this property has a value.
		if (hasValue()) {
			return value;
		}
		
		//Handles the case that this property has no value, but a base property.
		if (hasBaseProperty()) {
			return baseProperty.getRecursiveOrDefaultValue();
		}
		
		//Handles the case that this property has no value and no base property.
		return defaultValue;
	}
	
	//method
	/**
	 * @return the value of this property.
	 * @throws UnexistingAttributeException if this property has no value.
	 */
	public V getValue() {
		
		//Checks if this property has a value.
		supposeHasValue();
				
		return value;
	}
	
	//method
	/**
	 * @return true if this property has a base property.
	 */
	public boolean hasBaseProperty() {
		return (baseProperty != null);
	}
	
	//method
	/**
	 * @return true if this property has a value or a base property with a value.
	 */
	public boolean hasRecursiveValue() {
		
		//Handles the case that this property has a value.
		if (hasValue()) {
			return true;
		}
		
		//Handles the case that this property has no value but a a base property.
		if (hasBaseProperty()) {
			return baseProperty.hasRecursiveValue();
		}
	
		//Handles the case that this property has no value and no base property.
		return false;
	}
	
	//method
	/**
	 * @return true if this property has a value.
	 */
	public boolean hasValue() {
		return (value != null);
	}
	
	//method
	/**
	 * Removes the value of this property.
	 */
	public void removeValue() {
		value = null;
	}
	
	//method
	/**
	 * Sets the value of this property.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 */
	public void setValue(final V value) {
		
		//Checks if the given value is not null.
		Validator
		.suppose(value)
		.thatIsNamed(VariableNameCatalogue.VALUE)
		.isNotNull();
		
		//Sets the value of this property.
		this.value = value;
	}
	
	//method
	/**
	 * Sets the value from this property from the given specification.
	 * 
	 * @param specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public void setValueFromSpecification(final Specification specification) {
		setValue(valueCreator.getOutput(specification));
	}
	
	//package-visible method
	/**
	 * Sets the base property of this property.
	 * 
	 * @param baseProperty
	 * @throws NullArgumentException if the given base property is null.
	 * @throws InvalidStateException if this property has a base property.
	 */
	@SuppressWarnings("unchecked")
	void setBaseProperty(final Property<?> baseProperty) {
		
		//Checks if the given base property is not null.
		Validator
		.suppose(baseProperty)
		.thatIsNamed("base property")
		.isNotNull();
		
		supposeHasNoBaseProperty();
		
		//Sets the base property of this property.
		this.baseProperty = (Property<V>)baseProperty;
	}
	
	//method
	/**
	 * @throws InvalidStateException if this property has a base property.
	 */
	private void supposeHasNoBaseProperty() {
		
		//Checks if this property has no base property.
		if (hasBaseProperty()) {
			throw new InvalidStateException(
				this,
				"has a base property"
			);
		}
		
	}

	//method
	/**
	 * @throws UnexistingAttributeException if this property has no value.
	 */
	private void supposeHasValue() {
		
		//Checks if this property has a value.
		if (!hasValue()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.VALUE
			);
		}
	}
}
