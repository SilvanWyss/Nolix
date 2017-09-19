//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.interfaces.OptionalSignable;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

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
	@SuppressWarnings("unchecked")
	public final OSE removeInfoString() {
		
		infoString = null;
		
		return (OSE)this;
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
	@SuppressWarnings("unchecked")
	public final OSE setInfoString(final String infoString) {
		
		//Checks if the given info string is not null or empty.
		Validator.supposeThat(infoString).thatIsNamed("info string").isNotEmpty();
		
		//Sets the info string of this optional signable element.
		this.infoString = infoString;
		
		return (OSE)this;
	}
}
