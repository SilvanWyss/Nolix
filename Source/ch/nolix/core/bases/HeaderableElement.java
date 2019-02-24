//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.skillAPI.Headerable;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link HeaderableElement} has a header that can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2019-02
 * @lines 60
 * @parma <NE> The type of a {@link HeaderableElement}.
 */
public abstract class HeaderableElement<NE extends HeaderableElement<NE>>
implements ISmartObject<NE>, Headerable<NE> {
	
	//attribute
	private String header;
	
	//constructor
	/**
	 * Creates a new {@link HeaderableElement} with the given header.
	 * 
	 * @param header
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public HeaderableElement(final String header) {
		setHeader(header);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getHeader() {
		return header;
	}
	
	//method
	/**
	 * Sets the header of the current {@link HeaderableElement}.
	 * 
	 * @param header
	 * @return the current {@link HeaderableElement}.
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is blank.
	 */
	@Override
	public final NE setHeader(final String header) {
		
		//Checks if the given header is not null and not blank.
		Validator
		.suppose(header)
		.thatIsNamed(VariableNameCatalogue.HEADER)
		.isNotBlank();
		
		//Sets the header of the current HeaderableElement.
		this.header = header;
		
		return asConcreteType();
	}
}
