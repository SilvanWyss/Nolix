//package declaration
package ch.nolix.core.entity;

import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
//own imports
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.specificationAPI.Specified;

/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 60
 * @param <V> The value of a property.
 */
public final class MutableProperty<V extends Specified>
extends SingleProperty<V> {
	
	//constructor
	/**
	 * Creates a new property
	 * with the given name, setter method and value creator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @param specificaitonCreatr
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given setter method is null.
	 * @throws NullArgumentException if the given value creator is null.
	 * @throws NullArgumentException if the given specification creator is null.
	 */
	public MutableProperty(
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
