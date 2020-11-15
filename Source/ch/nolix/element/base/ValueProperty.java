//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementAPI.IElement;

//class
/**
 * @author Silvan Wyss
 * @month 2018-02
 * @lines 80
 * @param <V> The type of the value of a {@link ValueProperty}.
 */
public final class ValueProperty<V> extends SingleValueProperty<V> {
	
	//constructor
	/**
	 * Creates a new {@link ValueProperty} with the given name, setterMethod and valueCreator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @throws ArgumentIsNullException if the given name is blank.
	 * @throws InvalidArgumentException if the given setterMethod is null.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 */
	@SuppressWarnings("unchecked")
	public <E extends IElement> ValueProperty(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<BaseNode, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator, v -> ((E)v).getSpecification());
	}
	
	//constructor
	/**
	 * Creates a new {@link ValueProperty} with the given name, setterMethod, valueCreator and specificationCreator.
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
	public ValueProperty(
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
