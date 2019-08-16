//package declaration
package ch.nolix.element.base;

import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.node.Node;
import ch.nolix.core.node.BaseNode;

//class
/**
 * @author Silvan Wyss
 * @month 2018-02
 * @lines 60
 * @param <V> The type of the value of a {@link MutableProperty}.
 */
public final class MutableProperty<V> extends SingleProperty<V> {
	
	//constructor
	/**
	 * Creates a new {@link MutableProperty} with the given name, setterMethod, valueCreator and specificationCreator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws NullArgumentException if the given valueCreator is null.
	 * @throws NullArgumentException if the given specificationCreator is null.
	 */
	public MutableProperty(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator, specificationCreator);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMutable() {
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOptional() {
		return false;
	}
}
