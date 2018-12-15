//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.specificationAPI.Specified;

//class
/**
* @author Silvan Wyss
* @month 2018-02
* @lines 90
* @param <V> The type of the value of an immutable property.
*/
public final class Property<V extends Specified>
extends SingleProperty<V> {
	
	//constructor
	/**
	 * Creates a new property
	 * with the given name, setter method and value generator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given value creator is null.
	 * @throws NullArgumentException if the given specification creator is null.
	 */
	public Property(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator,
		final IElementTakerElementGetter<V, DocumentNode> specificationCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator, specificationCreator);
	}
	
	//method
	/**
	 * @return true if this property is mutable.
	 */
	@Override
	public boolean isMutable() {
		return false;
	}
	
	//method
	/**
	 * @return true if this property is optional.
	 */
	@Override
	public boolean isOptional() {
		return false;
	}
}
