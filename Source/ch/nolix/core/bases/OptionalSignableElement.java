//package declaration
package ch.nolix.core.bases;

import ch.nolix.core.skillInterfaces.OptionalSignable;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * An optional signable element can have an info string.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 * @param <OSE> - The type of an optional signable element.
 */
public abstract class OptionalSignableElement<OSE extends OptionalSignableElement<OSE>>
implements OptionalSignable<OSE> {

	//optional attribute
	private String infoString;
	
	//method
	/**
	 * @return the info string of this optional signable element.
	 * @throws UnexistingAttributeException if this optional signable element has no info string.
	 */
	public final String getInfoString() {
		
		//Checks if this optional signable element has an info string.
		if (!hasInfoString()) {
			throw new UnexistingAttributeException(this, "info string");
		}
		
		return infoString;
	}
	
	//method
	/**
	 * @return true if this optional signable element has an info stirng.
	 */
	public final boolean hasInfoString() {
		return (infoString != null);
	}
	
	//method
	/**
	 * Removes the info string of this optional signable element.
	 * 
	 * @return this optioanl signable element.
	 */
	public final OSE removeInfoString() {
		
		infoString = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the info string of this optional signable element.
	 * 
	 * @param infoString
	 * @return this optional signable element.
	 * @throws NullArgumentException if the given info string is null.
	 * @throws EmptyArgumentException if the given info string is empty.
	 */
	public final OSE setInfoString(final String infoString) {
		
		//Checks if the given info string is not null or empty.
		Validator.suppose(infoString).thatIsNamed("info string").isNotEmpty();
		
		//Sets the info string of this optional signable element.
		this.infoString = infoString;
		
		return getInstance();
	}
}
