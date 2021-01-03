//package declaration
package ch.nolix.element.elementapi;

//own imports
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillapi.Resettable;

//interface
/**
 * A {@link IMutableElement} is a {@link IElement}:
 * -Whose attributes can be mutated separately.
 * -Whose attributes can be reset together.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 130
 * @param <S> is the type of a {@link IMutableElement}.
 */
public interface IMutableElement<S extends IMutableElement<S>> extends Resettable, IElement {
	
	//method declaration
	/**
	 * Adds or changes the given attribute to the current {@link IMutableElement}.
	 * This method is not fluent.
	 * 
	 * @param attribute
	 */
	void addOrChangeAttribute(BaseNode attribute);
	
	//method
	/**
	 * Adds or changes the given attributes to the current {@link IMutableElement}.
	 * This method is not fluent.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	default void addOrChangeAttribute(final BaseNode... attributes) {
		
		//Iterates the given attributes.
		for (final var a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//method
	/**
	 * Adds or changes the given attributes to the current {@link IMutableElement}.
	 * This method is not fluent.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	default void addOrChangeAttributes(final Iterable<? extends BaseNode> attributes) {
		
		//Iterates the given attributes.
		for (final var a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link IMutableElement}.
	 * This method is not fluent.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	default void addOrChangeAttribute(final String attribute) {
		addOrChangeAttribute(Node.fromString(attribute));
	}
	
	//method
	/**
	 * Adds or changes the given attributes to the current {@link IMutableElement}.
	 * This method is not fluent.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	default void addOrChangeAttribute(final String... attributes) {
		
		//Iterates the given attributes.
		for (final var a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//method
	/**
	 * Resets the current {@link IMutableElement} from the given specification.
	 * 
	 * @param specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	default void resetFrom(final BaseNode specification) {
		resetFrom(specification.getRefAttributes());
	}
	
	//method
	/**
	 * Resets the current {@link IMutableElement} from the given attributes.
	 * 
	 * @param attributes
	 * @param <BN> is the type of the given attributes.
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	default <BN extends BaseNode> void resetFrom(final Iterable<BN> attributes) {
		reset();
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Resets the current {@link IMutableElement} from the given specification.
	 * 
	 * @param specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	default void resetFrom(final String specification) {
		resetFrom(Node.fromString(specification));
	}
	
	//method
	/**
	 * Resets the current {@link IMutableElement} from the file with the given filePath.
	 * 
	 * @param filePath
	 * @throws InvalidArgumentException if the given filePath is not valid.
	 * @throws InvalidArgumentException if the file with the given filePath does not represent a {@link Node}.
	 */
	default void resetFromFile(final String filePath) {
		resetFrom(Node.fromFile(filePath));
	}
}
