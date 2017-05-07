//package declaration
package ch.nolix.common.specification;

//own imports
import ch.nolix.common.interfaces.Resettable;
import ch.nolix.common.invalidArgumentException.Argument;
import ch.nolix.common.invalidArgumentException.ErrorPredicate;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;

//interface
/**
 * A specifiable object is a specified object:
 * -Whose all official attributes can be mutated uniquely.
 * -Whose all official attributes can be reset together.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 130
 */
public interface Specifiable extends Resettable, Specified {
	
	//abstract method
	/**
	 * Adds or change the given attribute to this specifiable object.
	 * 
	 * @param attribute
	 */
	public abstract void addOrChangeAttribute(final Specification attribute);
	
	//default method
	/**
	 * Adds or change the given attributes to this specifiable object.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void addOrChangeAttribute(final Specification... attributes) {
		
		//Iterates the given attributes.
		for (final Specification a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//default method
	/**
	 * Adds or change the given attribute to this specifiable object.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public default void addOrChangeAttribute(final String attribute) {
		addOrChangeAttribute(new Specification(attribute));
	}
	
	//default method
	/**
	 * Adds or change the given attributes to this specifiable object.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void addOrChangeAttribute(final String... attributes) {
		
		//Iterates the given attributes.
		for (final String a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//default method
	/**
	 * Adds or change the given attributes to this specifiable object.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void addOrChangeAttributes(final Iterable<Specification> attributes) {
		
		//Iterates the given attributes.
		for (final Specification a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//default method
	/**
	 * Loads the specification in the file or folder with the given path to this specifiable object.
	 * 
	 * @param path
	 */
	public default void loadFrom(final String path) {
		reset(Specification.loadSpecification(path));
	}
	
	//default method
	/**
	 * Resets this specifiable object with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void reset(final Iterable<Specification> attributes) {
		reset();
		addOrChangeAttributes(attributes);
	}
	
	//default method
	/**
	 * Resets this specifiable object with the given specification.
	 * 
	 * @param specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public default void reset(final Specification specification) {
		
		//Checks if the given specification is a specification for this specifiable object.
		if (!specification.hasHeader(getType())) {
			throw new InvalidArgumentException(
				new Argument(specification),
				new ErrorPredicate("is no " + getType() + " specification")
			);
		}
		
		reset();
		addOrChangeAttributes(specification.getRefAttributes());
	}
	
	//default method
	/**
	 * Resets this specifiable object with the given specification.
	 * 
	 * @param specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public default void reset(final String specification) {
		reset(new Specification(specification));
	}
}
