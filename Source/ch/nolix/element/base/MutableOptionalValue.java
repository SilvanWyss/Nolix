//package declaration
package ch.nolix.element.base;

import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementAPI.IElement;

//class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 90
 * @param <V> The value of an optional property.
 */
public final class MutableOptionalValue<V> extends SingleValue<V> {
	
	//constructor
	/**
	 * Creates a new {@link MutableOptionalValue} with the given name, setterMethod and valueCreator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @throws ArgumentIsNullException if the given name is blank.
	 * @throws InvalidArgumentException if the given setterMethod is null.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 */
	@SuppressWarnings("unchecked")
	public <E extends IElement> MutableOptionalValue(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<BaseNode, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator, v -> ((E)v).getSpecification());
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
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		
		//Calls method of the base class.
		super.clear();
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
