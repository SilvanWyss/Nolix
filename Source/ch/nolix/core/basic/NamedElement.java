//package declaration
package ch.nolix.core.basic;

//own imports
import ch.nolix.core.interfaces.Named;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A named element has a determined name.
 * 
 * @author Silvan Wyss
 * @month: 2016-10
 * @lines 40
 */
public abstract class NamedElement implements Named {

	//attribute
	private final String name;
	
	//constructor
	/**
	 * Creates new named element with the given name.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public NamedElement(final String name) {
		
		//Checks if the given name is not null or empty.
		Validator.supposeThat(name).thatIsNamed("name").isNotEmpty();
		
		//Sets the name of this named element.
		this.name = name;
	}
	
	//method
	/**
	 * @return the name of this named element.
	 */
	public final String getName() {
		return name;
	}
}
