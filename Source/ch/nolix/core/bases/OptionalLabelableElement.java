//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.skillAPI.OptionalLabelable;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link OptionalLabelableElement} has an info string
 * that can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 100
 * @param <OLE> The type of a {@link OptionalLabelableElement}.
 */
public abstract class OptionalLabelableElement<OLE extends OptionalLabelableElement<OLE>>
implements ISmartObject<OLE>, OptionalLabelable<OLE> {
	
	//optional attribute
	private String infoString;
	
	//method
	/**
	 * @return the info string of the current {@link OptionalLabelableElement}.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link OptionalLabelableElement} does not have an info string.
	 */
	@Override
	public final String getInfoString() {
		
		supposeHasInfoString();
		
		return infoString;
	}
	
	//method
	/**
	 * @return true if the current {@link OptionalLabelableElement} has an info string.
	 */
	@Override
	public final boolean hasInfoString() {
		return (infoString != null);
	}
	
	//method
	/**
	 * Removes the info string of the current {@link OptionalLabelableElement}.
	 * 
	 * @return the current {@link OptionalLabelableElement}.
	 */
	@Override
	public final OLE removeInfoString() {
		
		infoString = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the info string of the current {@link OptionalLabelableElement}.
	 * 
	 * @param infoString
	 * @return the current {@link OptionalLabelableElement}.
	 * @throws NullArgumentException if the given info string is null.
	 * @throws InvalidArgumentException if the given info string is blank.
	 */
	@Override
	public final OLE setInfoString(final String infoString) {
		
		//Checks if the given info string is not null and not blank.
		Validator
		.suppose(infoString)
		.thatIsNamed(VariableNameCatalogue.INFO_STRING)
		.isNotBlank();
		
		//Sets the info string of the current OptionalLabelableElement.
		this.infoString = infoString;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link OptionalLabelableElement} does not have an info string.
	 */
	private void supposeHasInfoString() {
		if (!hasInfoString()) {
			throw
			new ArgumentMissesAttributeException(
				this,
				VariableNameCatalogue.INFO_STRING
			);
		}
	}
}
