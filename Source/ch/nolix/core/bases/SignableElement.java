//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.interfaces.Signable;
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
	 * Creates new signable element with the given info stirng.
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
	 * @throws NullArgumentException if the given info string is null.
	 * @throws EmptyArgumentException if the given info string is empty.
	 */
	@SuppressWarnings("unchecked")
	public final SE setInfoString(final String infoString) {
		
		//Checks if the given info string is not null or empty.
		Validator.suppose(infoString).thatIsNamed("info string").isNotEmpty();
		
		//Sets the info string of this optional signable element.
		this.infoString = infoString;
		
		return (SE)this;
	}
}
