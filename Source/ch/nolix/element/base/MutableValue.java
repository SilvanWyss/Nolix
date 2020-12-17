//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;

//class
/**
 * @author Silvan Wyss
 * @date 2018-03-01
 * @lines 60
 * @param <V> The type of the value of a {@link MutableValue}.
 */
public final class MutableValue<V> extends SingleValue<V> {
	
	//constructor
	/**
	 * Creates a new {@link MutableValue}
	 * with the given name, defaultValue, setterMethod, valueCreator and specificationCreator.
	 * 
	 * @param name
	 * @param defaultValue
	 * @param setterMethod
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given defaultValue is null.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
	 */
	public MutableValue(
		final String name,
		final V defaultValue,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator, specificationCreator);
		
		setValue(defaultValue);
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
