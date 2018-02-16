//package declaration
package ch.nolix.core.entity;

//own import
import ch.nolix.core.specification.StandardSpecification;

//abstract class
/**
 * 
 * An immutable entity is an entity that is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2018-02
 * @lines 20
 */
public abstract class ImmutableEntity extends Entity {

	//constructor
	/**
	 * Creates new immutable entity with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public ImmutableEntity(final Iterable<StandardSpecification> attributes) {
		attributes.forEach(a -> addOrChangeAttribute(a));
	}
}
