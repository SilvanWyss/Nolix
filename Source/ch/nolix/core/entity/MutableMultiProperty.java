//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.specificationAPI.Specified;
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
public final class MutableMultiProperty<V extends Specified>
extends Propertyoid<V>
implements Clearable<MutableMultiProperty<V>> {

	//attributes
	private final IElementTaker<V> adderMethod;
	private boolean approved = false;
	
	//multi attribute
	private final List<V> values = new List<>();
	
	//constructor
	/**
	 * Creates a new multi property with the given name, value created and adder method.
	 * 
	 * @param name
	 * @param valueCreator
	 * @param adderMethod
	 * @param specificationCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given adder method is null.
	 * @throws NullArgumentException if the given value creator is null.
	 * @throws NullArgumentException if the given specification creator is not an instace.
	 */
	public MutableMultiProperty(
		final String name,
		final IElementTaker<V> adderMethod,
		final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator,
		final IElementTakerElementGetter<V, DocumentNode> specificationCreator) {
		
		//Calls constructor of the base class
		super(name, valueCreator, specificationCreator);
		
		//Checks if the given adder method is not null.
		Validator
		.suppose(adderMethod)
		.thatIsNamed("adder method")
		.isInstance();
		
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
	@Override
	public MutableMultiProperty<V> clear() {
		
		values.clear();
		
		return this;
	}
	
	//method
	/**
	 * @return the values of this multi property.
	 */
	@Override
	public ReadContainer<V> getRefValues() {
		return new ReadContainer<V>(values);
	}
	
	//method
	/**
	 * @return true if this single property is approved.
	 */
	@Override
	public final boolean isApproved() {
		return approved;
	}
	
	//method
	/**
	 * @return true if this multi property does not contain a value.
	 */
	@Override
	public boolean isEmpty() {
		return values.isEmpty();
	}
	
	//method
	/**
	 * @return true if this multi property is mutable.
	 */
	@Override
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
	@Override
	void addOrChangeValue(final V value) {
		adderMethod.run(value);
	}
	
	//package-visible method
	/**
	 * Approves this multi property.
	 */
	@Override
	void approve() {
		approved = true;
	}
}
