//package declaration
package ch.nolix.element.base;

import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;

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
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
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
