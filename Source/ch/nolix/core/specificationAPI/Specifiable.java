//package declaration
package ch.nolix.core.specificationAPI;

import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.skillInterfaces.Resettable;

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
public interface Specifiable<S extends Specifiable<S>> extends Resettable<S>, Specified {
	
	//abstract method
	/**
	 * Adds or changes the given attribute to this specifiable object.
	 * 
	 * @param attribute
	 */
	public abstract void addOrChangeAttribute(final DocumentNodeoid attribute);
	
	//default method
	/**
	 * Adds or changes the given attributes to this specifiable object.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void addOrChangeAttribute(final DocumentNodeoid... attributes) {
		
		//Iterates the given attributes.
		for (final DocumentNodeoid a : attributes) {
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
		addOrChangeAttribute(new DocumentNode(attribute));
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
	public default void addOrChangeAttributes(final Iterable<? extends DocumentNodeoid> attributes) {
		
		//Iterates the given attributes.
		for (final DocumentNodeoid a : attributes) {
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
		reset(DocumentNode.createFromFile(path));
	}
	
	//default method
	/**
	 * Resets this specifiable object with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void reset(final Iterable<? extends DocumentNodeoid> attributes) {
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
	public default void reset(final DocumentNodeoid standardSpecification) {
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
		reset(new DocumentNode(specification));
	}
}
