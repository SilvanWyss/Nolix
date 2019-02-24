//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.skillAPI.Labelable;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link LabelableElement} has an info string that can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
 * @param <LE> The type of a {@link LabelableElement}.
 */
public abstract class LabelableElement<LE extends LabelableElement<LE>>
implements ISmartObject<LE>, Labelable<LE> {
	
	//attribute
	private String infoString;
	
	//constructor
	/**
	 * Creates a new {@link LabelableElement} with the given info string.
	 * 
	 * @param infoString
	 * @throws NullArgumentException if the given info string is null.
	 * @throws InvalidArgumentException if the given info string is blank.
	 */
	public LabelableElement(final String infoString) {
		setInfoString(infoString);
	}
	
	//method
	/**
	 * @return the info string of the current {@link LabelableElement}.
	 */
	@Override
	public final String getInfoString() {
		return infoString;
	}
	
	//method
	/**
	 * Sets the info string of the current {@link LabelableElement}.
	 * 
	 * @param infoString
	 * @return this {@link LabelableElement}.
	 * @throws NullArgumentException if the given info string is null.
	 * @throws InvalidArgumentException if the given info string is blank.
	 */
	@Override
	public final LE setInfoString(final String infoString) {
		
		//Checks if the given info string is not null and not blank.
		Validator.suppose(infoString).thatIsNamed("info string").isNotBlank();
		
		//Sets the info string of the current LabelableElement.
		this.infoString = infoString;
		
		return asConcreteType();
	}
}
