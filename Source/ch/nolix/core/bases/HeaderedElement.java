//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.skillAPI.Headered;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link HeaderedElement} has a header.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 50
 */
public abstract class HeaderedElement implements Headered {
	
	//attribute
	private final String header;
	
	//constructor
	/**
	 * Creates a new {@link HeaderedElement} with the given header.
	 * 
	 * @param header
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public HeaderedElement(final String header) {
		
		//Checks if the given header is not null and not blank.
		Validator
		.suppose(header)
		.thatIsNamed(VariableNameCatalogue.HEADER)
		.isNotBlank();
		
		//Sets the header of the current HeaderedElement.
		this.header = header;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getHeader() {
		return header;
	}
}
