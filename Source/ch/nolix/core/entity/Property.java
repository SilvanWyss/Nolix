//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.functionInterfaces.IElementTakerRunner;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;

//class
/**
 * An immutable property is a property that can be set only once.
 * 
* @author Silvan Wyss
* @month 2018-02
* @lines 90
* @param <V> The type of the value of an immutable property.
*/
public final class Property<V extends Specified>
extends Propertyoid<V> {
	
	//constructor
	/**
	 * Creates new property
	 * with the given name, setter method and value generator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	public Property(
		final String name,
		final IElementTakerRunner<V> setterMethod,
		final IElementTakerElementGetter<Specification, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator);
	}
	
	//method
	/**
	 * @return true if this property is mutable.
	 */
	public boolean isMutable() {
		return false;
	}
	
	//method
	/**
	 * @return true if this property is optional.
	 */
	public boolean isOptional() {
		return false;
	}
}
