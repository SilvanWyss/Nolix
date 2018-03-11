//package declaration
package ch.nolix.core.specificationInterfaces;

//own imports
import ch.nolix.core.interfaces.Resettable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;

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
	 * Adds or changes the given attribute to this specifiable object.
	 * 
	 * @param attribute
	 */
	public abstract void addOrChangeAttribute(final Specification attribute);
	
	//default method
	/**
	 * Adds or changes the given attributes to this specifiable object.
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
	 * Adds or changes the given attribute to this specifiable object.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public default void addOrChangeAttribute(final String attribute) {
		addOrChangeAttribute(new StandardSpecification(attribute));
	}
	
	//default method
	/**
	 * Adds or changes the given attributes to this specifiable object.
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
	 * Adds or changes the given attributes to this specifiable object.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void addOrChangeAttributes(final Iterable<? extends Specification> attributes) {
		
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
		reset(StandardSpecification.createSpecificationFromFile(path));
	}
	
	//default method
	/**
	 * Resets this specifiable object with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void reset(final Iterable<? extends Specification> attributes) {
		reset();
		addOrChangeAttributes(attributes);
	}
	
	//default method
	/**
	 * Resets this specifiable object with the given specification.
	 * 
	 * @param standardSpecification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public default void reset(final Specification standardSpecification) {
		
		//Checks if the given specification is a specification for this specifiable object.
		if (!standardSpecification.hasHeader(getType())) {
			throw new InvalidArgumentException(
				new Argument(standardSpecification),
				new ErrorPredicate("is no " + getType() + " specification")
			);
		}
		
		reset();
		addOrChangeAttributes(standardSpecification.getRefAttributes());
	}
	
	//default method
	/**
	 * Resets this specifiable object with the given specification.
	 * 
	 * @param specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public default void reset(final String specification) {
		reset(new StandardSpecification(specification));
	}
}
