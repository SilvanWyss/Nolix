//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.skillAPI.OptionalNamable;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link OptionalNamableElement} can have a name.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 90
 * @param <ONE> The type of a {@link OptionalNamableElement}.
 */
public abstract class OptionalNamableElement<ONE extends OptionalNamableElement<ONE>>
implements ISmartObject<ONE>, OptionalNamable<ONE> {
	
	//optional attribute
	private String name;
	
	//method
	/**
	 * @return the name of the current {@link OptionalNamableElement}.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link OptionalNamableElement} does not have a name.
	 */
	@Override
	public final String getName() {
		
		supposeHasName();
		
		return name;
	}
	
	//method
	/**
	 * @return true if the current {@link OptionalNamableElement} has a name.
	 */
	@Override
	public final boolean hasName() {
		return (name != null);
	}
	
	//method
	/**
	 * Removes the name of the current {@link OptionalNamableElement}.
	 * 
	 * @return the current {@link OptionalNamableElement}.
	 */
	@Override
	public final ONE removeName() {
		
		name = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the name of the current {@link OptionalNamableElement}.
	 * 
	 * @param name
	 * @return the current {@link OptionalNamableElement} element.
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 */
	@Override
	public final ONE setName(final String name) {
		
		//Checks if the given name is not null and not blank.
		Validator
		.suppose(name)
		.thatIsNamed(VariableNameCatalogue.NAME)
		.isNotBlank();
		
		//Sets the name of the current OptionalNamableElement.
		this.name = name;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link OptionalNamableElement} does not have a name.
	 */
	private void supposeHasName() {
		if (!hasName()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.NAME);
		}
	}
}
