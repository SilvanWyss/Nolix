//package declaration
package ch.nolix.system.element;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionuniversalapi.IElementTaker;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerElementGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2018-03-01
 * @param <V> is the type of the value of a {@link MutableValue}.
 */
public final class MutableValue<V> extends SingleValue<V> {
	
	//static method
	/**
	 * @param name
	 * @param defaultValue
	 * @param setterMethod
	 * @return a new {@link MutableValue}
	 * that will store a {@link Boolean} and have the given name, defaultValue and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static MutableValue<Boolean> forBoolean(
		final String name,
		final boolean defaultValue,
		final IElementTaker<Boolean> setterMethod
	) {
		return
		new MutableValue<>(name, defaultValue, setterMethod, INode::getSingleChildNodeAsBoolean, Node::withChildNode);
	}
	
	//static method
	/**
	 * @param name
	 * @param defaultValue
	 * @param setterMethod
	 * @return a new {@link MutableValue} that will
	 * store a {@link Double} and have the given name, defaultValue and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static MutableValue<Double> forDouble(
		final String name,
		final double defaultValue,
		final IElementTaker<Double> setterMethod
	) {
		return
		new MutableValue<>(name, defaultValue, setterMethod, INode::getSingleChildNodeAsDouble, Node::withChildNode);
	}
	
	//static method
	/**
	 * @param name
	 * @param defaultValue
	 * @param setterMethod
	 * @return a new {@link MutableValue}
	 * that will store a {@link Integer} and have the given name, defaultValue and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static MutableValue<Integer> forInt(
		final String name,
		final int defaultValue,
		final IElementTaker<Integer> setterMethod
	) {
		return
		new MutableValue<>(name, defaultValue, setterMethod, INode::getSingleChildNodeAsInt, Node::withChildNode);
	}
	
	//static method
	/**
	 * @param name
	 * @param defaultValue
	 * @param setterMethod
	 * @return a new {@link MutableValue}
	 * that will store a {@link String} and have the given name, defaultValue and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static MutableValue<String> forString(
		final String name,
		final String defaultValue,
		final IElementTaker<String> setterMethod
	) {
		return
		new MutableValue<>(
			name,
			defaultValue,
			setterMethod,
			s -> s.getRefSingleChildNode().getHeaderOrEmptyString(),
			(final String s) -> {
				
				if (s.isEmpty()) {
					return Node.EMPTY_NODE;
				}
				
				return Node.withChildNode(s);
			}
		);
	}
	
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
		final IElementTakerElementGetter<BaseNode<?>, V> valueCreator,
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
}
