//package declaration
package ch.nolix.core.bases;

import ch.nolix.core.skillInterfaces.Signable;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * A signable element has an info string.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
 * @param <OSE> - The type of an optional signable element.
 */
public abstract class SignableElement<SE extends SignableElement<SE>>
implements Signable<SE> {

	//attribute
	private String infoString;
	
	//constructor
	/**
	 * Creates a new signable element with the given info stirng.
	 * 
	 * @param infoString
	 * @throws NullArgumentException if the given info string is not an instance.
	 * @throws EmptyArgumentException if the given info string is empty.
	 */
	public SignableElement(final String infoString) {
		setInfoString(infoString);
	}
	
	//method
	/**
	 * @return the info string of this signable element.
	 */
	public final String getInfoString() {
		return infoString;
	}
	
	//method
	/**
	 * Sets the info string of this signable element.
	 * 
	 * @param infoString
	 * @return this signable element.
	 * @throws NullArgumentException if the given info string is not an instance.
	 * @throws EmptyArgumentException if the given info string is empty.
	 */
	public final SE setInfoString(final String infoString) {
		
		//Checks if the given info string is not null or empty.
		Validator.suppose(infoString).thatIsNamed("info string").isNotEmpty();
		
		//Sets the info string of this optional signable element.
		this.infoString = infoString;
		
		return getInstance();
	}
}
