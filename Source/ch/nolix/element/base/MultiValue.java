//package declaration
package ch.nolix.element.base;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.skillapi.Clearable;

//class
/**
 * @author Silvan Wyss
 * @date 2018-03-04
 * @lines 230
 * @param <V> is the type of the values of a {@link MultiValue}.
 */
public final class MultiValue<V> extends BaseValue<V> implements Clearable, IContainer<V> {
	
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
		return new MultiValue<>(name, adderMethod, BaseNode::toInt, Node::withHeader);
	}
	
	//static method
	/**
	 * @param name
	 * @param adderMethod
	 * @return a new {@link MultiValue} that will store {@link String}s and have the given name and setterMethod.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given adderMethod is null.
	 */
	public static MultiValue<String> forStrings(final String name, final IElementTaker<String> adderMethod) {
		return new MultiValue<>(name, adderMethod, BaseNode::getHeader, Node::withHeader);
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
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		//Calls constructor of the base class
		super(name, valueCreator, specificationCreator);
		
		//Asserts that the given adderMethod is not null.
		Validator.assertThat(adderMethod).thatIsNamed("adder method").isNotNull();
		
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
	 * @return true if the current {@link MultiValue} contains a value.
	 */
	@Override
	public boolean containsAny() {
		return Clearable.super.containsAny();
	}
	
	//method
	/**
	 * Removes all values of the current {@link MultiValue}.
	 */
	@Override
	public void clear() {	
		values.clear();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public V getRefAt(final int index) {
		return values.getRefAt(index);
	}
	
	//method
	/**
	 * @return the last value of the current {@link MultiValue}.
	 * @throws EmptyArgumentException if the current {@link MultiValue} is empty.
	 */
	public V getRefLast() {
		return values.getRefLast();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getElementCount() {
		return values.getElementCount();
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
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<V> iterator() {
		return values.iterator();
	}
	
	//method
	/**
	 * Removes the given value of the current {@link MultiValue}.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the current {@link MultiValue} does not contain the given value.
	 */
	public void remove(final V value) {
		values.removeFirst(value);
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
	void addOrChangeValue(final V value) {
		adderMethod.run(value);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Iterates the values of the current MultiProperty.
		for (final var v : this) {
			
			//Creates a specification from the current value.
			final var specification = specificationCreator.getOutput(v);
			specification.setHeader(getName());
			
			//Adds the specification to the given list.
			list.addAtEnd(specification);
		}
	}
}
