//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.functionInterfaces.IElementTakerRunner;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A multi property is clearable.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 150
 * @param <V> The type of the values of a multi property.
 */
public final class MultiProperty<V extends Specified>
extends Propertyoid<V>
implements Clearable<MultiProperty<V>> {

	//attributes
	private final IElementTakerRunner<V> adderMethod;
	private boolean approved = false;
	
	//multi attribute
	private final List<V> values = new List<>();
	
	//constructor
	/**
	 * Creates new multi property with the given name, value created and adder method.
	 * 
	 * @param name
	 * @param valueCreator
	 * @param adderMethod
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given adder method is null.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	public MultiProperty(
		final String name,
		final IElementTakerRunner<V> adderMethod,
		final IElementTakerElementGetter<Specification, V> valueCreator) {
		
		//Calls constructor of the base class
		super(name, valueCreator);
		
		//Checks if the given adder method is not null.
		Validator
		.suppose(adderMethod)
		.thatIsNamed("adder method")
		.isNotNull();
		
		//Sets the adder method of this multi property.
		this.adderMethod = adderMethod;
	}
	
	//method
	/**
	 * Adds the given value to this multi property.
	 * 
	 * @param value
	 * @throws NullArgumentException
	 * if the given value is null.
	 * @throws InvalidArgumentException
	 * if this multi property contains already the given value.
	 */
	public void addValue(final V value) {
		values.addAtEndRegardingSingularity(value);
	}
	
	//method
	/**
	 * Removes all values of this multi property.
	 * 
	 * @return this multi property.
	 */
	public MultiProperty<V> clear() {
		
		values.clear();
		
		return this;
	}
	
	//method
	/**
	 * @return the values of this multi property.
	 */
	public ReadContainer<V> getRefValues() {
		return new ReadContainer<V>(values);
	}
	
	//method
	/**
	 * @return true if this single property is approved.
	 */
	public final boolean isApproved() {
		return approved;
	}
	
	//method
	/**
	 * @return true if this multi property contains no value.
	 */
	public boolean isEmpty() {
		return values.isEmpty();
	}
	
	//method
	/**
	 * @return true if this multi property is mutable.
	 */
	public boolean isMutable() {
		return true;
	}
	
	//method
	/**
	 * Removes the given value of this multi property.
	 * 
	 * @param value
	 * @throws InvalidArgumentException
	 * if this multi property does not contain the given value.
	 */
	public void removeValue(final V value) {		
		values.removeFirst(value);
	}
	
	//package-visible method
	/**
	 * Adds or changes the given value to this multi property.
	 * This method uses the adder method of this multi property.
	 */
	void addOrChangeValue(final V value) {
		adderMethod.run(value);
	}
	
	//package-visible method
	/**
	 * Approves this multi property.
	 */
	void approve() {
		approved = true;
	}
}
