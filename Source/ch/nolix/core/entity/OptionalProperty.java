//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.functionInterfaces.IElementTaker;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;

//class
/**
* @author Silvan Wyss
* @month 2018-02
* @lines 90
* @param <V> The type of the value of an immutable optional property.
*/
public final class OptionalProperty<V extends Specified>
extends SingleProperty<V> {
	
	//constructor
	/**
	 * Creates a new immutable optional property
	 * with the given name, setter method and value generator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	public OptionalProperty(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<Specification, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator);
	}
	
	//method
	/**
	 * @return true if this immutable optional property is mutable.
	 */
	public boolean isMutable() {
		return false;
	}
	
	//method
	/**
	 * @return true if this optional property is optional.
	 */
	public boolean isOptional() {
		return true;
	}
}
