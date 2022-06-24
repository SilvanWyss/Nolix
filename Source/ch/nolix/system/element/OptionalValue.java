//package declaration
package ch.nolix.system.element;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.functionuniversalapi.IElementTaker;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerElementGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2018-03-01
 * @param <V> is the type of the value of a {@link OptionalValue}.
 */
public final class OptionalValue<V> extends SingleValue<V> {
	
	//static method
	/**
	 * @param name
	 * @param setterMethod
	 * @return a new {@link OptionalValue} that will store a {@link Boolean} and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static OptionalValue<Boolean> forBoolean(final String name, final IElementTaker<Boolean> setterMethod) {
		return new OptionalValue<>(name, setterMethod, BaseNode::getOneAttributeAsBoolean, Node::withAttribute);
	}
	
	//static method
	/**
	 * @param name
	 * @param setterMethod
	 * @return a new {@link OptionalValue} that will store a {@link Double} and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static OptionalValue<Double> forDouble(final String name, final IElementTaker<Double> setterMethod) {
		return new OptionalValue<>(name, setterMethod, BaseNode::getOneAttributeAsDouble, Node::withAttribute);
	}
	
	//static method
	/**
	 * @param name
	 * @param setterMethod
	 * @return a new {@link OptionalValue} that will store a {@link Integer} and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static OptionalValue<Integer> forInt(final String name, final IElementTaker<Integer> setterMethod) {
		return new OptionalValue<>(name, setterMethod, BaseNode::getOneAttributeAsInt, Node::withAttribute);
	}
	
	//static method
	/**
	 * @param name
	 * @param setterMethod
	 * @return a new {@link OptionalValue} that will store a {@link String} and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static OptionalValue<String> forString(final String name, final IElementTaker<String> setterMethod) {
		return new OptionalValue<>(
			name,
			setterMethod,
			s -> s.getRefOneAttribute().getHeaderOrEmptyString(),
			(final String s) -> {
				
				if (s.isEmpty()) {
					return new Node();
				}
				
				return Node.withAttribute(s);
			}
		);
	}
	
	//constructor
	/**
	 * Creates a new {@link OptionalValue} with the given name, setterMethod, valueCreator and specificationCreator.
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
	public OptionalValue(
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
