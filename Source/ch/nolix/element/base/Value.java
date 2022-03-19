//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.functionapi.IElementTaker;
import ch.nolix.core.functionapi.IElementTakerElementGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2018-03-01
 * @param <V> is the type of the value of a {@link Value}.
 */
public final class Value<V> extends SingleValue<V> {
	
	//static method
	/**
	 * @param name
	 * @param setterMethod
	 * @return a new {@link Value} that will store a {@link Boolean} and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static Value<Boolean> forBoolean(final String name, final IElementTaker<Boolean> setterMethod) {
		return new Value<>(name, setterMethod, BaseNode::getOneAttributeAsBoolean, Node::withAttribute);
	}
	
	//static method
	/**
	 * @param name
	 * @param setterMethod
	 * @return a new {@link Value} that will store a {@link Integer} and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static Value<Integer> forInt(final String name, final IElementTaker<Integer> setterMethod) {
		return new Value<>(name, setterMethod, BaseNode::getOneAttributeAsInt, Node::withAttribute);
	}
	
	//static method
	/**
	 * @param name
	 * @param setterMethod
	 * @return a new {@link Value} that will store a {@link String} and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static Value<String> forString(final String name, final IElementTaker<String> setterMethod) {
		return new Value<>(
			name,
			setterMethod,
			s -> s.getRefOneAttribute().getHeaderOrEmptyString(),
			s -> {
				if (s.isEmpty()) {
					return new Node();
				}
				
				return Node.withAttribute(s);
			}
		);
	}
	
	//constructor
	/**
	 * Creates a new {@link Value} with the given name, setterMethod, valueCreator and specificationCreator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given setterMethod is blank.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
	 */
	public Value(
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
		return false;
	}
}
