//package declaration
package ch.nolix.element.base;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 190
 * @param <V> The type of the values of a {@link MultiProperty}.
 */
public final class MultiProperty<V> extends Propertyoid<V> implements IContainer<V> {
	
	//attribute
	private final IElementTaker<V> adderMethod;
	
	//multi-attribute
	private final List<V> values = new List<>();
	
	//constructor
	/**
	 * Creates a new {@link MultiProperty} with the given name, valueCreator, adderMethod and specificationCreator.
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
	public MultiProperty(
		final String name,
		final IElementTaker<V> adderMethod,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		//Calls constructor of the base class
		super(name, valueCreator, specificationCreator);
		
		//Checks if the given adderMethod is not null.
		Validator
		.suppose(adderMethod)
		.thatIsNamed("adder method")
		.isNotNull();
		
		//Sets the adderMethod of the current MultiProperty.
		this.adderMethod = adderMethod;
	}
	
	//method
	/**
	 * Adds the given value to the current {@link MultiProperty}.
	 * 
	 * @param value
	 * @throws ArgumentIsNullException if the given value is null.
	 * @throws InvalidArgumentException if the current {@link MultiProperty} contains already the given value.
	 */
	public void addValue(final V value) {
		values.addAtEndRegardingSingularity(value);
	}
	
	//method
	/**
	 * Removes all values of the current {@link MultiProperty}.
	 */
	public void clear() {	
		values.clear();
	}
	
	//method
	/**
	 * @return the last value of the current {@link MultiProperty}.
	 * @throws EmptyArgumentException if the current {@link MultiProperty} is empty.
	 */
	public V getRefLast() {
		return values.getRefLast();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return values.getSize();
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
	 * Removes and returns the last value from the current {@link MultiProperty}.
	 * 
	 * @return the last element of the current {@link MultiProperty}.
	 * @throws EmptyArgumentException if the current {@link MultiProperty} is empty.
	 */
	public V removeAndGetRefLastValue() {
		return values.removeAndGetRefLast();
	}
	
	//method
	/**
	 * Removes the last value from the current {@link MultiProperty}.
	 * 
	 * @return the current {@link MultiProperty}.
	 * @throws EmptyArgumentException if the current {@link MultiProperty} is empty.
	 */
	public MultiProperty<V> removeLastValue() {
		
		values.removeLast();
		
		return this;
	}
	
	//method
	/**
	 * Removes the given value from the current {@link MultiProperty}.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the current {@link MultiProperty} does not contain the given value.
	 */
	public void removeValue(final V value) {
		values.removeFirst(value);
	}
	
	//package-visible method
	/**
	 * {@inheritDoc}
	 */
	@Override
	void addOrChangeValue(final V value) {
		adderMethod.run(value);
	}

	//package-visible method
	/**
	 * {@inheritDoc}
	 */
	@Override
	final void fillUpSpecificationsOfValues(final List<Node> list) {
		
		//Iterates the values of the current MultiProperty.
		for (final var v : this) {
			
			//Creates a specification of the current value.
			final var specification = specificationCreator.getOutput(v);
			specification.setHeader(getName());
			
			list.addAtEnd(specification);
		}
	}
}
