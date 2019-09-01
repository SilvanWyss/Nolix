//package declaration
package ch.nolix.element.base;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//package-visible abstract class
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 150
 * @param <V> The type of the value of a {@link SingleProperty}.
 */
abstract class SingleProperty<V> extends Propertyoid<V> {
	
	//attribute
	private final IElementTaker<V> setterMethod;
	
	//optional attribute
	private V value;

	//constructor
	/**
	 * Creates a new {@link SingleProperty} with the given name, setterMethod, valueCreator and specificationCreator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws NullArgumentException if the given valueCreator is null.
	 * @throws NullArgumentException if the given specificationCreator is null.
	 */
	public SingleProperty(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		//Calls constructor of the base class.
		super(name, valueCreator, specificationCreator);
		
		//Checks if the given setterMethod is not null.
		Validator
		.suppose(setterMethod)
		.thatIsNamed("setter method")
		.isNotNull();
		
		//Sets the setterMethod of the current SingleProperty.
		this.setterMethod = setterMethod;
	}
	
	//method
	/**
	 * @return true if the current {@link SingleProperty} has a value.
	 */
	public final boolean containsAny() {
		return (value != null);
	}
	
	//method
	/**
	 * @return the value of the current {@link SingleProperty}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link SingleProperty} does not have a value.
	 */
	public final V getValue() {
		
		//Checks if the current SingleProperty has a value.
		if (value == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.VALUE);
		}
		
		return value;
	}
	
	//method
	/**
	 * @return true if the current {@link SingleProperty} has a value.
	 */
	public final boolean hasValue() {
		return (value != null);
	}
	
	//method
	/**
	 * @return true if the current {@link SingleProperty} does not have a value.
	 */
	@Override
	public final boolean isEmpty() {
		return (value == null);
	}
	
	//abstract method
	/**
	 * @return true if the current {@link SingleProperty} is optional.
	 */
	public abstract boolean isOptional();
	
	//method
	/**
	 * Sets the value of the current {@link SingleProperty}.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 * @throws InvalidArgumentException if the current {@link SingleProperty} is not mutable and has already a value.
	 */
	public final void setValue(final V value) {
		
		//Checks if the given value is not null.
		Validator
		.suppose(value)
		.thatIsNamed(VariableNameCatalogue.VALUE)
		.isNotNull();
		
		//Checks if the current SingleProperty is mutable or does not have already a value.
		if (!isMutable() && hasValue()) {
			throw new InvalidArgumentException(this, "is not mutable and has already a value");
		}
		
		//Sets the value of the current SingleProperty.
		this.value = value;
	}
	
	//package-visible method
	@Override
	final void addOrChangeValue(final V value) {
		setterMethod.run(value);
	}
	
	//package-visible method
	/**
	 * Removes the value of the current {@link SingleProperty}.
	 * 
	 * @return the current {@link SingleProperty}.
	 */
	void clear() {	
		value = null;
	}
	
	//package-visible method
	/**
	 * {@inheritDoc}
	 */
	@Override
	final void fillUpSpecificationsOfValues(final List<Node> list) {
		
		//Handles the case that the current SingleProperty has a value.
		//For a better performance, this implementation does not use all comfortable methods.
		if (value != null) {
			
			//Creates a specification of the current value.
			final var specification = specificationCreator.getOutput(value);
			specification.setHeader(getName());
			
			list.addAtEnd(specification);
		}
	}
}
