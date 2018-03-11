//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specifiable;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * 
 * A specifiable entity is an entity that is specifiable..
 * 
 * @author Silvan Wyss
 * @month 2018-02
 * @lines 40
 */
public abstract class SpecifiableEntity
extends Entity
implements Specifiable {

	//class
	/**
	 * Creates a new entity with mutables.
	 * 
	 * @throws InvalidArgumentException
	 * if this entity with mutable contains no property that is mutable.
	 */
	public SpecifiableEntity() {
		
		//Checks if this entity with mutables contains no property that is mutable.
		Validator.suppose(getRefProperties()).contains(p -> p.isMutable());
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this specifiable object.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
	}
}
