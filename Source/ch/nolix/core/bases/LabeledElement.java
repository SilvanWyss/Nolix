//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.skillAPI.Labeled;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link LabeledElement} has an info string.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 50
 */
public abstract class LabeledElement implements Labeled {
	
	//attribute
	private final String infoString;
	
	//constructor
	/**
	 * Creates a new {@link LabeledElement} with the given infoString.
	 * 
	 * @param infoString
	 * @throws NullArgumentException if the given infoString is null.
	 * @throws InvalidArgumentException if the given infoString is blank.
	 */
	public LabeledElement(final String infoString) {
		
		//Checks if the given infoString is not null and not blank.
		Validator
		.suppose(infoString)
		.thatIsNamed(VariableNameCatalogue.INFO_STRING)
		.isNotBlank();
		
		//Sets the infoString of the current LabeledElement.
		this.infoString = infoString;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getInfoString() {
		return infoString;
	}
}
