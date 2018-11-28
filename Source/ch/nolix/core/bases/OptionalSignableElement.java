//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.skillAPI.OptionalSignable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A {@link OptionalSignableElement} can have an info string.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 * @param <OSE> The type of a {@link OptionalSignableElement}.
 */
public abstract class OptionalSignableElement<OSE extends OptionalSignableElement<OSE>>
implements ISmartObject<OSE>, OptionalSignable<OSE> {
	
	//optional attribute
	private String infoString;
	
	//method
	/**
	 * @return the info string of the current {@link OptionalSignableElement}.
	 * @throws UnexistingAttributeException
	 * if the current {@link OptionalSignableElement} does not have an info string.
	 */
	public final String getInfoString() {
		
		supposeHasInfoString();
		
		return infoString;
	}
	
	//method
	/**
	 * @return true if the current {@link OptionalSignableElement} has an info string.
	 */
	public final boolean hasInfoString() {
		return (infoString != null);
	}
	
	//method
	/**
	 * Removes the info string of the current {@link OptionalSignableElement}.
	 * 
	 * @return the current {@link OptionalSignableElement}.
	 */
	public final OSE removeInfoString() {
		
		infoString = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the info string of the current {@link OptionalSignableElement}.
	 * 
	 * @param infoString
	 * @return the current {@link OptionalSignableElement}.
	 * @throws NullArgumentException if the given info string is null.
	 * @throws EmptyArgumentException if the given info string is empty.
	 */
	public final OSE setInfoString(final String infoString) {
		
		//Checks if the given info string is not null and not empty.
		Validator.suppose(infoString).thatIsNamed("info string").isNotEmpty();
		
		//Sets the info string of the current optional signable element.
		this.infoString = infoString;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException
	 * if the current {@link OptionalSignableElement} has no info string.
	 */
	private void supposeHasInfoString() {
		if (!hasInfoString()) {
			throw new UnexistingAttributeException(this, "info string");
		}
	}
}
