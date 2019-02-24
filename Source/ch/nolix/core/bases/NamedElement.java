//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.skillAPI.Named;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link NamedElement} has a name.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 50
 */
public abstract class NamedElement implements Named {
	
	//attribute
	private final String name;
	
	//constructor
	/**
	 * Creates a new {@link NamedElement} with the given name.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 */
	public NamedElement(final String name) {
		
		//Checks if the given name is not null and not blank.
		Validator
		.suppose(name)
		.thatIsNamed(VariableNameCatalogue.NAME)
		.isNotBlank();
		
		//Sets the name of the current NamedElement.
		this.name = name;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return name;
	}
}
