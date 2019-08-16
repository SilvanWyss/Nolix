//package declaration
package ch.nolix.element.baseAPI;

import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.node.Node;
import ch.nolix.core.node.BaseNode;
import ch.nolix.core.skillAPI.Resettable;

//interface
/**
 * A {@link IMutableElement} is a {@link IElement}:
 * -Whose attributes can be mutated uniquely.
 * -Whose attributes can be reset together.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 140
 */
public interface IMutableElement<S extends IMutableElement<S>> extends Resettable<S>, IElement {
	
	//abstract method
	/**
	 * Adds or changes the given attribute to the current {@link IMutableElement}.
	 * This method is not fluent.
	 * 
	 * @param attribute
	 * @return the current {@link IMutableElement}.
	 */
	public abstract void addOrChangeAttribute(BaseNode attribute);
	
	//default method
	/**
	 * Adds or changes the given attributes to the current {@link IMutableElement}.
	 * This method is not fluent.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void addOrChangeAttribute(final BaseNode... attributes) {
		
		//Iterates the given attributes.
		for (final var a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//default method
	/**
	 * Adds or changes the given attributes to the current {@link IMutableElement}.
	 * This method is not fluent.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void addOrChangeAttributes(final Iterable<? extends BaseNode> attributes) {
		
		//Iterates the given attributes.
		for (final var a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//default method
	/**
	 * Adds or changes the given attribute to the current {@link IMutableElement}.
	 * This method is not fluent.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public default void addOrChangeAttribute(final String attribute) {
		addOrChangeAttribute(Node.fromString(attribute));
	}
	
	//default method
	/**
	 * Adds or changes the given attributes to the current {@link IMutableElement}.
	 * This method is not fluent.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public default void addOrChangeAttribute(final String... attributes) {
		
		//Iterates the given attributes.
		for (final var a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//default method
	/**
	 * Resets this {@link IMutableElement} with the given specification.
	 * 
	 * @param specification
	 * @return the current {@link IMutableElement}.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public default S reset(final BaseNode specification) {
		return reset(specification.getRefAttributes());
	}
	
	//default method
	/**
	 * Resets this {@link IMutableElement} with the given attributes.
	 * 
	 * @param attributes
	 * @return the current {@link IMutableElement}.
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	@SuppressWarnings("unchecked")
	public default S reset(final Iterable<? extends BaseNode> attributes) {
		
		reset();
		addOrChangeAttributes(attributes);
		
		return (S)this;
	}
	
	//default method
	/**
	 * Resets this {@link IMutableElement} with the given specification.
	 * 
	 * @param specification
	 * @return the current {@link IMutableElement}.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public default S reset(final String specification) {
		return reset(Node.fromString(specification));
	}
	
	//default method
	/**
	 * Resets the current {@link IMutableElement} with the specification
	 * from the file with the given file path.
	 * 
	 * @param filePath
	 * @return the current {@link IMutableElement}.
	 * @throws InvalidArgumentException if the given file path is not valid.
	 * @throws InvalidArgumentException
	 * if the file with the given file path does not represent a {@link Node}.
	 */
	public default S resetFrom(final String filePath) {
		return reset(Node.fromFile(filePath));
	}
}
