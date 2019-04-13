//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.specificationAPI.Specifiable;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * 
 * A {@link SpecifiableEntity} is a {@link Entity} that is specifiable..
 * 
 * @author Silvan Wyss
 * @month 2018-02
 * @lines 40
 */
public abstract class SpecifiableEntity<SE extends SpecifiableEntity<SE>>
extends Entity<SE>
implements Specifiable<SE> {
	
	//class
	/**
	 * Creates a new {@link SpecifiableEntity}.
	 * 
	 * @throws InvalidArgumentException
	 * if the current {@link SpecifiableEntity} does not contain a mutable property.
	 */
	public SpecifiableEntity() {
		
		//Checks if the current SpecifiableEntity does not contain a a mutable property.
		Validator.suppose(getRefProperties()).contains(p -> p.isMutable());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
	}
}
