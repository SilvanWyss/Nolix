//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.functionInterfaces.IElementTakerRunner;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.core.validator2.Validator;

//class
/**
 * An optional property is a clearable property.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 130
 * @param <V> The value of an optional property.
 */
public final class OptionalProperty<V extends Specified>
extends Propertyoid<V>
implements Clearable<OptionalProperty<V>> {
	
	//optional attribute
	private V value;
	
	//constructor
	/**
	 * Creates new optional property with the given name and value creator.
	 * 
	 * @param name
	 * @param valueCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	public OptionalProperty(
		final String name,
		final IElementTakerElementGetter<Specification, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name, valueCreator);
	}
	
	//constructor
	/**
	 * Creates new optional property with the given name, setter method and value creator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given setter method is null.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	public OptionalProperty(
		final String name,
		final IElementTakerRunner<V> setterMethod,
		final IElementTakerElementGetter<Specification, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator);
	}
	
	//method
	/**
	 * Removes the value of this property.
	 * 
	 * @return this optional property.
	 */
	public OptionalProperty<V> clear() {
		
		//Clears the value of this optional property.
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
	 * @throws UnexistingAttributeException if this optional property has no value.
	 */
	private void supposeHasValue() {
		if (isEmpty()) {
			throw new UnexistingAttributeException(this, "value");
		}
	}
}
