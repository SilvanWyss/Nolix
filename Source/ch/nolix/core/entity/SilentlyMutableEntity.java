//package declaration
package ch.nolix.core.entity;

//own import
import ch.nolix.core.specification.StandardSpecification;

//abstract class
/**
 * A silently mutable entity is like a mutable entity
 * whose mutation method is protected.
 * The mutation method can be published optionally and individually by inheriting classes.
 * 
* @author Silvan Wyss
* @month 2018-02
* @lines 30
*/
public abstract class SilentlyMutableEntity extends Entity {
	
	//method
	/**
	 * Adds or changes the given attribute to this silent mutable entity.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	protected void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
	}
}
