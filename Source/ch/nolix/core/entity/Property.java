//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.functionInterfaces.IElementTakerRunner;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.core.validator2.Validator;

/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 70
 * @param <V> The value of a property.
 */
public final class Property<V extends Specified>
extends Propertyoid<V> {
	
	//constructor
	/**
	 * Creates new property with the given name, value creator and value.
	 * 
	 * @param name
	 * @param valueCreator
	 * @param value
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given value creator is null.
	 * @throws NullArgumentException if the given value is null.
	 */
	public Property(
		final String name,
		final IElementTakerElementGetter<IContainer<Specification>, V> valueCreator,
		final V value
	) {
		
		//Calls constructor of the base class.
		super(name, valueCreator);
		
		//Sets the value of this property.
		setValue(value);
	}
	
	//constructor
	/**
	 * Creates new property with the given name, setter method, value creator and value.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @param value
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given setter method is null.
	 * @throws NullArgumentException if the given value creator is null.
	 * @throws NullArgumentException if the given value is null.
	 */
	public Property(
		final String name,
		final IElementTakerRunner<V> setterMethod,
		final IElementTakerElementGetter<IContainer<Specification>, V> valueCreator,
		final V value
		
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator);
		
		//Sets the value of this property.
		setValue(value);
	}

	//attribute
	private V value;	
	
	//method
	/**
	 * @return the value of this property.
	 */
	public V getValue() {
		return value;
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
		Validator.suppose(value).thatIsNamed("value").isNotNull();
		
		//Sets the value of this property.
		this.value = value;
	}
	
	//package-visible method
	/**
	 * @return true if this property has no value.
	 */
	boolean isEmpty() {
		return false;
	}
}
