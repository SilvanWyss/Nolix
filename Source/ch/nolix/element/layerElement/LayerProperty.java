//package declaration
package ch.nolix.element.layerElement;

import ch.nolix.core.attributeAPI.Named;
//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator.Validator;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 250
 * @param <V> The type of the value of a property.
 */
public final class LayerProperty<V> implements Named {
	
	//attributes
	private final String name;
	private final V defaultValue;
	private final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator;
	private final IElementTakerElementGetter<V, DocumentNode> specificationCreator;
	
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
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given default value is null.
	 * @throws NullArgumentException if the given value creator is null.
	 * @throws NullArgumentException if the given specification creator is null.
	 */
	public LayerProperty(
		final String name,		
		final V defaultValue,
		final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator,
		final IElementTakerElementGetter<V, DocumentNode> specificationCreator
	) {
		
		this.name = Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();
		
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
		
		//Checks if the given specificaiton creator is not null.
		Validator
		.suppose(specificationCreator)
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
	 * @throws ArgumentMissesAttributeException if this property does not have a value.
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
	public void setValueFromSpecification(final DocumentNodeoid specification) {
		setValue(valueCreator.getOutput(specification));
	}
	
	//package-visible method
	void fillUpAttribute(final List<DocumentNode> attributes) {
		
		//Handles the case that the current property has a value.
		if (hasValue()) {
			
			final var attribute = specificationCreator.getOutput(getRecursiveOrDefaultValue());
			attribute.setHeader(getName());
			
			attributes.addAtEnd(attribute);
		}
	}
	
	//package-visible method
	/**
	 * Sets the base property of this property.
	 * 
	 * @param baseProperty
	 * @throws NullArgumentException if the given base property is null.
	 * @throws InvalidArgumentException if this property has a base property.
	 */
	@SuppressWarnings("unchecked")
	void setBaseProperty(final LayerProperty<?> baseProperty) {
		
		//Checks if the given base property is not null.
		Validator
		.suppose(baseProperty)
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
		
		//Checks if this property does not have a base property.
		if (hasBaseProperty()) {
			throw new InvalidArgumentException(
				this,
				"has a base property"
			);
		}
		
	}

	//method
	/**
	 * @throws ArgumentMissesAttributeException if this property does not have a value.
	 */
	private void supposeHasValue() {
		
		//Checks if this property has a value.
		if (!hasValue()) {
			throw new ArgumentMissesAttributeException(
				this,
				VariableNameCatalogue.VALUE
			);
		}
	}
}
