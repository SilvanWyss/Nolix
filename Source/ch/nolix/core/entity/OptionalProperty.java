//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationAPI.Specified;

//class
/**
* @author Silvan Wyss
* @month 2018-02
* @lines 60
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
	 * @param specificationCreator
	 * @throws NullArgumentException if the given name is not an instance.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given value creator is not an instance.
	 * @throws NullArgumentException if the given specification creator is not an instance.
	 */
	public OptionalProperty(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<Specification, V> valueCreator,
		final IElementTakerElementGetter<V, StandardSpecification> specificationCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator, specificationCreator);
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
