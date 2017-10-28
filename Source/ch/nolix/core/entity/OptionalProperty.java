//package declaration
package ch.nolix.core.entity;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
//own imports
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.core.validator2.Validator;

/**
 * An optional property is a clearable property.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 80
 * @param <V> The value of an optional property.
 */
public class OptionalProperty<V extends Specified> extends Propertyoid<V> implements Clearable<OptionalProperty<V>> {
	
	//optional attribute
	private V value;
	
	//constructor
	/**
	 * Creates new property with the given name and value creator.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	public OptionalProperty(
		final String name,
		final IElementTakerElementGetter<IContainer<Specification>, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name, valueCreator);
	}
	
	//method
	/**
	 * Removes the value of this property.
	 * 
	 * @return this optional property.
	 */
	public OptionalProperty<V> clear() {
		
		value = null;
		
		return this;
	}
	
	//method
	/**
	 * @return the value of this optional property.
	 * @throws UnexistingAttributeException if this optional property has no value.
	 */
	public V getValue() {
		
		//Checks if this optional property has a value.
		supposeHasValue();
		
		return value;
	}
	
	//method
	/**
	 * @return true if this optional propery has no value.
	 */
	public boolean isEmpty() {
		return (value != null);
	}
	
	//method
	/**
	 * Sets the value of this optional property.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 */
	public void setValue(final V value) {
		
		//Checks if the given value is not null.
		Validator.suppose(value).thatIsNamed("value").isNotNull();
		
		//Sets the value of this property.
		this.value = value;
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if htis optional property has no value.
	 */
	private void supposeHasValue() {
		if (isEmpty()) {
			throw new UnexistingAttributeException(this, "value");
		}
	}
}
