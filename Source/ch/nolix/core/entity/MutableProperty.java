//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.functionInterfaces.IElementTakerRunner;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;

/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 50
 * @param <V> The value of a property.
 */
public final class MutableProperty<V extends Specified>
extends Propertyoid<V> {
	
	//constructor
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
	public MutableProperty(
		final String name,
		final IElementTakerRunner<V> setterMethod,
		final IElementTakerElementGetter<Specification, V> valueCreator		
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator);
	}
	
	//method
	/**
	 * @return true if this mutable property is mutable.
	 */
	public boolean isMutable() {
		return true;
	}
	
	//method
	/**
	 * @return true if this mutable property is optional.
	 */
	public boolean isOptional() {
		return false;
	}
}
