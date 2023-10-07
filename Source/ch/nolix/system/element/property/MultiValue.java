//package declaration
package ch.nolix.system.element.property;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.coreapi.functionapi.mutationapi.Clearable;

//class
/**
 * @author Silvan Wyss
 * @date 2018-03-04
 * @param <V> is the type of the values of a {@link MultiValue}.
 */
public final class MultiValue<V> extends BaseValue<V> implements Clearable {
	
	//static method
	/**
	 * @param name
	 * @param adderMethod
	 * @return a new {@link MultiValue} that will store {@link Integer}s and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given adderMethod is null.
	 */
	public static MultiValue<Integer> forInts(final String name, final IElementTaker<Integer> adderMethod) {
		return new MultiValue<>(name, adderMethod, INode::toInt, Node::withHeader);
	}
	
	//static method
	/**
	 * @param name
	 * @param adderMethod
	 * @return a new {@link MultiValue} that will store {@link String}s and have the given name and adderMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given adderMethod is null.
	 */
	public static MultiValue<String> forStrings(final String name, final IElementTaker<String> adderMethod) {
		return new MultiValue<>(name, adderMethod, INode::getHeader, Node::withHeader);
	}
	
	//attribute
	private final IElementTaker<V> adderMethod;
	
	//multi-attribute
	private final LinkedList<V> values = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link MultiValue} with the given name, valueCreator, adderMethod and specificationCreator.
	 * 
	 * @param name
	 * @param valueCreator
	 * @param adderMethod
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given adderMethod is null.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
	 */
	public MultiValue(
		final String name,
		final IElementTaker<V> adderMethod,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator
	) {
		
		//Calls constructor of the base class
		super(name, valueCreator, specificationCreator);
		
		//Asserts that the given adderMethod is not null.
		GlobalValidator.assertThat(adderMethod).thatIsNamed("adder method").isNotNull();
		
		//Sets the adderMethod of the current MultiProperty.
		this.adderMethod = adderMethod;
	}
	
	//method
	/**
	 * Adds the given value to the current {@link MultiValue}.
	 * 
	 * @param value
	 * @throws ArgumentIsNullException if the given value is null.
	 */
	public void add(final V value) {
		values.addAtEnd(value);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {	
		values.clear();
	}
	
	//method
	/**
	 * @return the values of the current {@link MultiValue}.
	 */
	public IContainer<V> getStoredValues() {
		return values;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return values.isEmpty();
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
	 * Removes the given value of the current {@link MultiValue}.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the current {@link MultiValue} does not contain the given value.
	 */
	public void remove(final V value) {
		values.removeFirstOccurrenceOf(value);
	}
	
	//method
	/**
	 * Removes and returns the last value of the current {@link MultiValue}.
	 * 
	 * @return the last element of the current {@link MultiValue}.
	 * @throws EmptyArgumentException if the current {@link MultiValue} is empty.
	 */
	public V removeAndGetRefLast() {
		return values.removeAndGetRefLast();
	}
	
	//method
	/**
	 * Removes the last value of the current {@link MultiValue}.
	 * 
	 * @return the current {@link MultiValue}.
	 * @throws EmptyArgumentException if the current {@link MultiValue} is empty.
	 */
	public MultiValue<V> removeLast() {
		
		values.removeLast();
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addOrChangeValue(final V value) {
		adderMethod.run(value);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
		
		//Iterates the values of the current MultiProperty.
		for (final var v : getStoredValues()) {
			
			//Creates a specification from the current value.
			final var specification =
			Node.withHeaderAndChildNodes(getName(), specificationCreator.getOutput(v).getStoredChildNodes());
			
			//Adds the specification to the given list.
			list.addAtEnd(specification);
		}
	}
}