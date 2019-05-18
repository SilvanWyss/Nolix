//package declaration
package ch.nolix.core.entity;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 150
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
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws NullArgumentException if the given adderMethod is null.
	 * @throws NullArgumentException if the given valueCreator is null.
	 * @throws NullArgumentException if the given specificationCreator is null.
	 */
	public MultiProperty(
		final String name,
		final IElementTaker<V> adderMethod,
		final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator,
		final IElementTakerElementGetter<V, DocumentNode> specificationCreator
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
	 * @throws NullArgumentException if the given value is null.
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
	
	//TODO: Delete this method.
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReadContainer<V> getRefValues() {
		return new ReadContainer<V>(values);
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
}
