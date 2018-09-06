//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.skillInterfaces.Clearable;
import ch.nolix.core.functionInterfaces.IElementTaker;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;

//class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 70
 * @param <V> The value of an optional property.
 */
public final class MutableOptionalProperty<V extends Specified>
extends SingleProperty<V>
implements Clearable<MutableOptionalProperty<V>> {
		
	//constructor
	/**
	 * Creates a new optional property
	 * with the given name, setter method and value creator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @throws NullArgumentException if the given name is not an instance.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given setter method is not an instance.
	 * @throws NullArgumentException if the given value creator is not an instance.
	 */
	public MutableOptionalProperty(
		final String name,
		final IElementTaker<V> setterMethod,
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
	public MutableOptionalProperty<V> clear() {
		
		//Calls method of the base class.
		super.clear();
		
		return this;
	}
	
	//method
	/**
	 * @return true if this mutable optional property is mutable.
	 */
	public boolean isMutable() {
		return true;
	}
	
	//method
	/**
	 * @return true if this mutable optional property is optional.
	 */
	public boolean isOptional() {
		return true;
	}
}
