//package declaration
package ch.nolix.element.base;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.invalidArgumentException.EmptyArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 200
 * @param <V> The type of the values of a {@link MultiValueProperty}.
 */
public final class MultiValueProperty<V> extends Property<V> implements IContainer<V> {
	
	//attribute
	private final IElementTaker<V> adderMethod;
	
	//multi-attribute
	private final LinkedList<V> values = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link MultiValueProperty} with the given name, valueCreator, adderMethod and specificationCreator.
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
	public MultiValueProperty(
		final String name,
		final IElementTaker<V> adderMethod,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		//Calls constructor of the base class
		super(name, valueCreator, specificationCreator);
		
		//Asserts that the given adderMethod is not null.
		Validator
		.assertThat(adderMethod)
		.thatIsNamed("adder method")
		.isNotNull();
		
		//Sets the adderMethod of the current MultiProperty.
		this.adderMethod = adderMethod;
	}
	
	//method
	/**
	 * Adds the given value to the current {@link MultiValueProperty}.
	 * 
	 * @param value
	 * @throws ArgumentIsNullException if the given value is null.
	 * @throws InvalidArgumentException if the current {@link MultiValueProperty} contains already the given value.
	 */
	public void add(final V value) {
		values.addAtEndRegardingSingularity(value);
	}
	
	//method
	/**
	 * Removes all values of the current {@link MultiValueProperty}.
	 */
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
	 * @return the last value of the current {@link MultiValueProperty}.
	 * @throws EmptyArgumentException if the current {@link MultiValueProperty} is empty.
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
	 * Removes the given value of the current {@link MultiValueProperty}.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the current {@link MultiValueProperty} does not contain the given value.
	 */
	public void remove(final V value) {
		values.removeFirst(value);
	}
	
	//method
	/**
	 * Removes and returns the last value of the current {@link MultiValueProperty}.
	 * 
	 * @return the last element of the current {@link MultiValueProperty}.
	 * @throws EmptyArgumentException if the current {@link MultiValueProperty} is empty.
	 */
	public V removeAndGetRefLast() {
		return values.removeAndGetRefLast();
	}
	
	//method
	/**
	 * Removes the last value of the current {@link MultiValueProperty}.
	 * 
	 * @return the current {@link MultiValueProperty}.
	 * @throws EmptyArgumentException if the current {@link MultiValueProperty} is empty.
	 */
	public MultiValueProperty<V> removeLast() {
		
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
	final void fillUpSpecificationsOfValues(final LinkedList<Node> list) {
		
		//Iterates the values of the current MultiProperty.
		for (final var v : this) {
			
			//Creates a specification of the current value.
			final var specification = specificationCreator.getOutput(v);
			specification.setHeader(getName());
			
			list.addAtEnd(specification);
		}
	}
}
