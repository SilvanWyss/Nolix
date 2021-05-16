//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-01
 * @lines 120
 * @param <V> is the type of the value of a {@link MutableOptionalValue}.
 */
public final class MutableOptionalValue<V> extends SingleValue<V> {
	
	//static method
	/**
	 * @param name
	 * @param setterMethod
	 * @return a new {@link MutableOptionalValue}
	 * that will store a {@link Boolean} and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static MutableOptionalValue<Boolean> forBoolean(final String name, final IElementTaker<Boolean> setterMethod) {
		return new MutableOptionalValue<>(name, setterMethod, BaseNode::getOneAttributeAsBoolean, Node::withAttribute);
	}
	
	//static method
	/**
	 * @param name
	 * @param setterMethod
	 * @return a new {@link MutableOptionalValue}
	 * that will store a {@link Integer} and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static MutableOptionalValue<Integer> forInt(final String name, final IElementTaker<Integer> setterMethod) {
		return new MutableOptionalValue<>(name, setterMethod, BaseNode::getOneAttributeAsInt, Node::withAttribute);
	}
	
	//static method
	/**
	 * @param name
	 * @param setterMethod
	 * @return a new {@link MutableOptionalValue}
	 * that will store a {@link String} and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 */
	public static MutableOptionalValue<String> forString(final String name,	final IElementTaker<String> setterMethod) {
		return new MutableOptionalValue<>(
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
	 * Creates a new {@link MutableOptionalValue}
	 * with the given name, setterMethod, valueCreator and specificationCreator.
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
	public MutableOptionalValue(
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
	 * Removes the value of the current {@link SingleValue}.
	 */
	public void clear() {
		internalClear();
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
		return true;
	}
}
