//package declaration
package ch.nolix.element.layerelement;

//own imports
import ch.nolix.common.attributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 250
 * @param <V> is the type of the value of a property.
 */
public final class LayerProperty<V> implements Named {
	
	//attributes
	private final String name;
	private final V defaultValue;
	private final IElementTakerElementGetter<BaseNode, V> valueCreator;
	private final IElementTakerElementGetter<V, Node> specificationCreator;
	
	//optional attributes
	private V value;
	private LayerProperty<V> baseProperty;
	
	//constructor
	/**
	 * Creates a new property with the given name, default value and value creator.
	 * 
	 * @param name
	 * @param defaultValue
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws ArgumentIsNullException if the given default value is null.
	 * @throws ArgumentIsNullException if the given value creator is null.
	 * @throws ArgumentIsNullException if the given specification creator is null.
	 */
	public LayerProperty(
		final String name,		
		final V defaultValue,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
		
		//Asserts that the given default is not null.
		Validator
		.assertThat(defaultValue)
		.thatIsNamed(LowerCaseCatalogue.DEFAULT_VALUE)
		.isNotNull();
				
		//Asserts that the given value creator is not null.
		Validator
		.assertThat(valueCreator)
		.thatIsNamed("value creator")
		.isNotNull();
		
		//Asserts that the given specificaiton creator is not null.
		Validator
		.assertThat(specificationCreator)
		.thatIsNamed("specification creator")
		.isNotNull();
		
		//Sets the default value of this property.
		this.defaultValue = defaultValue;
		
		//Sets the value creator of this property.
		this.valueCreator = valueCreator;
		
		//Sets the specification creator of this property.
		this.specificationCreator = specificationCreator;
	}
	
	//method
	@Override
	public String getName() {
		return name;
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
		
		//Handles the case that this property does not have a value, but a base property.
		if (hasBaseProperty()) {
			return baseProperty.getRecursiveOrDefaultValue();
		}
		
		//Handles the case that this property does not have a value or base property.
		return defaultValue;
	}
	
	//method
	/**
	 * @return the value of this property.
	 * @throws ArgumentDoesNotHaveAttributeException if this property does not have a value.
	 */
	public V getValue() {
		
		//Asserts that this property has a value.
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
		
		//Handles the case that this property does not have a value but a base property.
		if (hasBaseProperty()) {
			return baseProperty.hasRecursiveValue();
		}
	
		//Handles the case that this property does not have a value or base property.
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
	 * @throws ArgumentIsNullException if the given value is null.
	 */
	public void setValue(final V value) {
		
		//Asserts that the given value is not null.
		Validator
		.assertThat(value)
		.thatIsNamed(LowerCaseCatalogue.VALUE)
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
	public void setValueFromSpecification(final BaseNode specification) {
		setValue(valueCreator.getOutput(specification));
	}
	
	//method
	void fillUpAttribute(final LinkedList<Node> attributes) {
		
		//Handles the case that the current property has a value.
		if (hasValue()) {
			
			final var attribute = specificationCreator.getOutput(getRecursiveOrDefaultValue());
			attribute.setHeader(getName());
			
			attributes.addAtEnd(attribute);
		}
	}
	
	//method
	/**
	 * Sets the base property of this property.
	 * 
	 * @param baseProperty
	 * @throws ArgumentIsNullException if the given base property is null.
	 * @throws InvalidArgumentException if this property has a base property.
	 */
	@SuppressWarnings("unchecked")
	void setBaseProperty(final LayerProperty<?> baseProperty) {
		
		//Asserts that the given base property is not null.
		Validator
		.assertThat(baseProperty)
		.thatIsNamed("base property")
		.isNotNull();
		
		supposeHasNoBaseProperty();
		
		//Sets the base property of this property.
		this.baseProperty = (LayerProperty<V>)baseProperty;
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if this property has a base property.
	 */
	private void supposeHasNoBaseProperty() {
		
		//Asserts that this property does not have a base property.
		if (hasBaseProperty()) {
			throw new InvalidArgumentException(
				this,
				"has a base property"
			);
		}
		
	}

	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if this property does not have a value.
	 */
	private void supposeHasValue() {
		
		//Asserts that this property has a value.
		if (!hasValue()) {
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				LowerCaseCatalogue.VALUE
			);
		}
	}
}
