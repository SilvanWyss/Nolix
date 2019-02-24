//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.skillAPI.OptionalHeaderable;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link OptionalHeaderableElement} has a header
 * that can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 90
 * @param <ONE> The type of a {@link OptionalHeaderableElement}.
 */
public abstract class OptionalHeaderableElement<ONE extends OptionalHeaderableElement<ONE>>
implements ISmartObject<ONE>, OptionalHeaderable<ONE> {
	
	//optional attribute
	private String header;
	
	//method
	/**
	 * @return the header of the current {@link OptionalHeaderableElement}.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link OptionalHeaderableElement} does not have a header.
	 */
	@Override
	public final String getHeader() {
		
		supposeHasHeader();
		
		return header;
	}
	
	//method
	/**
	 * @return true if the current {@link OptionalHeaderableElement} has a header.
	 */
	@Override
	public final boolean hasHeader() {
		return (header != null);
	}
	
	//method
	/**
	 * Removes the header of the current {@link OptionalHeaderableElement}.
	 * 
	 * @return the current {@link OptionalHeaderableElement}.
	 */
	@Override
	public final ONE removeHeader() {
		
		header = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the header of the current {@link OptionalHeaderableElement}.
	 * 
	 * @param header
	 * @return the current {@link OptionalHeaderableElement}.
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	@Override
	public final ONE setHeader(final String header) {
		
		//Checks if the given header is not null and not blank.
		Validator
		.suppose(header)
		.thatIsNamed(VariableNameCatalogue.NAME)
		.isNotBlank();
		
		//Sets the header of the current OptionalHeaderableElement.
		this.header = header;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link OptionalHeaderableElement} does not have a header.
	 */
	private void supposeHasHeader() {
		if (!hasHeader()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.NAME);
		}
	}
}
