//package declaration
package ch.nolix.core.entity;

//own import
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Specifiable;

//abstract class
/**
 * 
 * A mutable entity is an entity that is mutable.
 * 
 * @author Silvan Wyss
 * @month 2018-02
 * @lines 30
 */
public abstract class MutableEntity
extends Entity
implements Specifiable {

	//method
	/**
	 * Adds or changes the given attribute to this specifiable object.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
	}
}
