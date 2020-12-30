//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElement;

//class
/**
 * @author Silvan Wyss
 * @month 2018-02
 * @lines 80
 * @param <V> is the type of the value of a {@link Value}.
 */
public final class Value<V> extends SingleValue<V> {
	
	//constructor
	/**
	 * Creates a new {@link Value} with the given name, setterMethod and valueCreator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @throws ArgumentIsNullException if the given name is blank.
	 * @throws InvalidArgumentException if the given setterMethod is null.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 */
	@SuppressWarnings("unchecked")
	public <E extends IElement> Value(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<BaseNode, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator, v -> ((E)v).getSpecification());
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
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOptional() {
		return false;
	}
}
