//package declaration
package ch.nolix.core.bases;

import ch.nolix.core.skillInterfaces.OptionalNamable;
import ch.nolix.primitive.validator2.Validator;

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
	public final ONE removeName() {
		
		name = null;
		
		return getInstance();
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
	public final ONE setName(final String name) {
		
		//Checks if the given name is not null or empty.
		Validator.suppose(name).thatIsNamed("name").isInstance();
		
		//Sets the name of this optional namable element.
		this.name = name;
		
		return getInstance();
	}
}
