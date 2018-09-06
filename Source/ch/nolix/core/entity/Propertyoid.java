//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
* @author Silvan Wyss
* @month 2017-10
* @lines 50
* @param <V> The type of the values of a property.
*/
public abstract class Propertyoid<V extends Specified>
extends NamedElement {
	
	//attributes
	private final IElementTakerElementGetter<Specification, V> valueCreator;
	
	//package-visible constructor
	Propertyoid(
		final String name,
		final IElementTakerElementGetter<Specification, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the given value creator is an instance.
		Validator
		.suppose(valueCreator)
		.thatIsNamed("value creator")
		.isInstance();
		
		//Sets the value creator of this property.
		this.valueCreator = valueCreator;
	}
	
	//abstract method
	/**
	 * @return true if this property is approved.
	 */
	public abstract boolean isApproved();
	
	//abstract method
	public abstract boolean isEmpty();
	
	//abstract method
	/**
	 * @return true if this property is mutable.
	 */
	public abstract boolean isMutable();
	
	//package-visible abstract method
	/**
	 * Adds or change the given value to this property.
	 * 
	 * @param value
	 */
	abstract void addOrChangeValue(V value);
	
	//package-visible method
	/**
	 * Adds or changes the value from the given specification to this property.
	 * 
	 * @param specification
	 */
	final void addOrChangeValueFromSpecification(
		final Specification specification
	) {
		addOrChangeValue(valueCreator.getOutput(specification));
	}
	
	//package-visible method
	/**
	 * Approves this property.
	 */
	abstract void approve();
	
	//package-visible abstract method
	/**
	 * @return the values of this property.
	 */
	abstract ReadContainer<V> getRefValues();
}
