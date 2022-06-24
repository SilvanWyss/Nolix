//package declaration
package ch.nolix.system.element;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.functionuniversalapi.IElementTaker;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerElementGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2018-03-04
 * @param <V> is the type of the value of a {@link SingleValue}.
 */
abstract class SingleValue<V> extends BaseValue<V> {
	
	//attribute
	private final IElementTaker<V> setterMethod;
	
	//optional attribute
	private V value;
	
	//constructor
	/**
	 * Creates a new {@link SingleValue} with the given name, setterMethod, valueCreator and specificationCreator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setterMethod is null.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
	 */
	public SingleValue(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		//Calls constructor of the base class.
		super(name, valueCreator, specificationCreator);
		
		//Asserts that the given setterMethod is not null.
		GlobalValidator.assertThat(setterMethod).thatIsNamed("setter method").isNotNull();
		
		//Sets the setterMethod of the current SingleProperty.
		this.setterMethod = setterMethod;
	}
	
	//method
	/**
	 * @return a new specification of the current {@link SingleValue}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link SingleValue} does not have a value
	 */
	public final Node getSpecification() {
		
		final var specification = specificationCreator.getOutput(getValue());
		specification.setHeader(getName());
		
		return specification;
	}
	
	//method
	/**
	 * @return the value of the current {@link SingleValue}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link SingleValue} does not have a value.
	 */
	public final V getValue() {
		
		//Asserts that the current SingleProperty has a value.
		if (value == null) {
			throw
			ArgumentDoesNotHaveAttributeException.forArgumentNameAndArgumentAndAttributeName(
				getName(),
				this,
				LowerCaseCatalogue.VALUE
			);
		}
		
		return value;
	}
	
	//method
	/**
	 * @return true if the current {@link SingleValue} does not have a value.
	 */
	public final boolean hasValue() {
		return (value != null);
	}
	
	//method
	/**
	 * Sets the value of the current {@link SingleValue}.
	 * 
	 * @param value
	 * @throws ArgumentIsNullException if the given value is null.
	 * @throws InvalidArgumentException if the current {@link SingleValue} is not mutable and has already a value.
	 */
	public final void setValue(final V value) {
		
		//Asserts that the given value is not null.
		GlobalValidator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		//Asserts that the current SingleProperty is mutable or does not have already a value.
		if (!isMutable() && hasValue()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not mutable and has already a value");
		}
		
		//Sets the value of the current SingleProperty.
		this.value = value;
	}
	
	//method
	@Override
	protected final void addOrChangeValue(final V value) {
		setterMethod.run(value);
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Handles the case that the current SingleValue has a value.
		if (hasValue()) {
			
			//Adds the specification of the current SingleValue to the given list.
			list.addAtEnd(getSpecification());
		}
	}
	
	//method
	/**
	 * Removes the value of the current {@link SingleValue}.
	 */
	protected final void internalClear() {	
		value = null;
	}
}
