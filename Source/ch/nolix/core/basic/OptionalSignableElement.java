//package declaration
package ch.nolix.core.basic;

//own imports
import ch.nolix.core.interfaces.OptionalSignable;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.ZetaValidator;

//abstract class
/**
 * An optional signable element can have an info string.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
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
	 * Sets the info string of this optional signable element.
	 * 
	 * @param infoString
	 * @return this optional signable element.
	 * @throws NullArgumentException if the given info string is null.
	 * @throws EmptyArgumentException if the given info string is an empty string.
	 */
	@SuppressWarnings("unchecked")
	public final OSE setInfoString(final String infoString) {
		
		//Checks if the given info string is null or an empty string.
		ZetaValidator.supposeThat(infoString).thatIsNamed("info string").isNotEmpty();
		
		this.infoString = infoString;
		
		return (OSE)this;
	}
}
