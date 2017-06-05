//package declaration
package ch.nolix.core.basic;

//own imports
import ch.nolix.core.interfaces.OptionalNamable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * An optional namable element can have a name.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 70
 * @param <ONE> - The type of an optional namable element.
 */
public abstract class OptionalNamableElement<ONE extends OptionalNamableElement<ONE>>
implements OptionalNamable<ONE> {

	//optional attribute
	private String name;
	
	//method
	/**
	 * @return the name of this optional namable element.
	 */
	public final String getName() {
		return name;
	}
	
	//method
	/**
	 * @return true if this optional namable element has a name.
	 */
	public final boolean hasName() {
		return (name != null);
	}
	
	//method
	/**
	 * Removes the name of this optional namable element.
	 * 
	 * @return this optional namable element.
	 */
	@SuppressWarnings("unchecked")
	public final ONE removeName() {
		
		name = null;
		
		return (ONE)this;
	}
	
	//method
	/**
	 * Sets the name of this optional namable element.
	 * 
	 * @param name
	 * @return this optional namable element.
	 * @throws NullArgumentException if the given name is empty.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	@SuppressWarnings("unchecked")
	public final ONE setName(final String name) {
		
		//Checks if the given name is not null or empty.
		Validator.supposeThat(name).thatIsNamed("name").isNotNull();
		
		//Sets the name of this optional namable element.
		this.name = name;
		
		return (ONE)this;
	}
}
