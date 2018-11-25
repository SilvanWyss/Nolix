//package declaration
package ch.nolix.core.bases;

import ch.nolix.core.skillAPI.Labelable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A {@link SignableElement} has an info string.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
 * @param <OSE> The type of a {@link SignableElement}.
 */
public abstract class SignableElement<SE extends SignableElement<SE>>
implements Labelable<SE> {

	//attribute
	private String infoString;
	
	//constructor
	/**
	 * Creates a new {@link SignableElement} with the given info string.
	 * 
	 * @param infoString
	 * @throws NullArgumentException if the given info string is null.
	 * @throws EmptyArgumentException if the given info string is empty.
	 */
	public SignableElement(final String infoString) {
		setInfoString(infoString);
	}
	
	//method
	/**
	 * @return the info string of the current {@link SignableElement}.
	 */
	public final String getInfoString() {
		return infoString;
	}
	
	//method
	/**
	 * Sets the info string of the current {@link SignableElement}.
	 * 
	 * @param infoString
	 * @return this {@link SignableElement}.
	 * @throws NullArgumentException if the given info string is null.
	 * @throws EmptyArgumentException if the given info string is empty.
	 */
	public final SE setInfoString(final String infoString) {
		
		//Checks if the given info string is not null and not empty.
		Validator.suppose(infoString).thatIsNamed("info string").isNotEmpty();
		
		//Sets the info string of the current optional signable element.
		this.infoString = infoString;
		
		return getInstance();
	}
}
