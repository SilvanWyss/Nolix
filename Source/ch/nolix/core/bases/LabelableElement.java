//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.skillAPI.Labelable;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link LabelableElement} has an info string.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
 * @param <OSE> The type of a {@link LabelableElement}.
 */
public abstract class LabelableElement<SE extends LabelableElement<SE>>
implements ISmartObject<SE>, Labelable<SE> {
	
	//attribute
	private String infoString;
	
	//constructor
	/**
	 * Creates a new {@link LabelableElement} with the given info string.
	 * 
	 * @param infoString
	 * @throws NullArgumentException if the given info string is null.
	 * @throws EmptyArgumentException if the given info string is empty.
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
	 * @throws EmptyArgumentException if the given info string is empty.
	 */
	@Override
	public final SE setInfoString(final String infoString) {
		
		//Checks if the given info string is not null and not empty.
		Validator.suppose(infoString).thatIsNamed("info string").isNotEmpty();
		
		//Sets the info string of the current optional signable element.
		this.infoString = infoString;
		
		return asConcreteType();
	}
}
