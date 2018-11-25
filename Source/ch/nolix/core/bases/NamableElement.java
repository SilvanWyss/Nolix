//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.skillAPI.Namable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A {@link NamableElement} has a name.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 60
 * @parma <NE> The type of a {@link NamableElement}.
 */
public abstract class NamableElement<NE extends NamableElement<NE>>
implements ISmartObject<NE>, Namable<NE> {

	//attribute
	private String name;
	
	//constructor
	/**
	 * Creates a new {@link NamableElement} with the given name.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public NamableElement(final String name) {
		setName(name);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}
	
	//method
	/**
	 * Sets the name of the current {@link NamableElement}.
	 * 
	 * @param name
	 * @return the current {@link NamableElement}.
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public final NE setName(final String name) {
		
		//Checks if the given name is not null and not empty.
		Validator
		.suppose(name)
		.thatIsNamed(VariableNameCatalogue.NAME)
		.isNotEmpty();
		
		//Sets the name of the current namable element.
		this.name = name;
		
		return asConcreteType();
	}
}
