//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.requestapi.OptionalityRequestable;

//class
/**
 * @author Silvan Wyss
 * @date 2018-03-04
 * @lines 140
 * @param <V> is the type of the value of a {@link SingleValue}.
 */
abstract class SingleValue<V> extends Property<V> implements OptionalityRequestable {
	
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
		Validator
		.assertThat(setterMethod)
		.thatIsNamed("setter method")
		.isNotNull();
		
		//Sets the setterMethod of the current SingleProperty.
		this.setterMethod = setterMethod;
	}
	
	//method
	/**
	 * @return the value of the current {@link SingleValue}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link SingleValue} does not have a value.
	 */
	public final V getValue() {
		
		//Asserts that the current SingleProperty has a value.
		if (value == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.VALUE);
		}
		
		return value;
	}
	
	//method
	/**
	 * @return true if the current {@link SingleValue} does not have a value.
	 */
	public final boolean hasValue() {
		return (value == null);
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
		Validator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		//Asserts that the current SingleProperty is mutable or does not have already a value.
		if (!isMutable() && hasValue()) {
			throw new InvalidArgumentException(this, "is not mutable and has already a value");
		}
		
		//Sets the value of the current SingleProperty.
		this.value = value;
	}
	
	//visibility-reduced method
	@Override
	final void addOrChangeValue(final V value) {
		setterMethod.run(value);
	}
	
	//visibility-reduced method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	final void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Handles the case that the current SingleProperty has a value.
		if (value != null) {
			
			//Creates a specification from the current value.
			final var specification = specificationCreator.getOutput(value);
			specification.setHeader(getName());
			
			//Adds the specification to the given list.
			list.addAtEnd(specification);
		}
	}
	
	//visibility-reduced method
	/**
	 * Removes the value of the current {@link SingleValue}.
	 */
	final void internalClear() {	
		value = null;
	}
}
