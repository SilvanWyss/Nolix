//package declaration
package ch.nolix.core.entity;

import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.specificationAPI.Specifiable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * 
 * A specifiable entity is an entity that is specifiable..
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
	 * Creates a new entity with mutables.
	 * 
	 * @throws InvalidArgumentException
	 * if this entity with mutable does not contain a property that is mutable.
	 */
	public SpecifiableEntity() {
		
		//Checks if this entity with mutables does not contain a property that is mutable.
		Validator.suppose(getRefProperties()).contains(p -> p.isMutable());
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this specifiable object.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
	}
}
