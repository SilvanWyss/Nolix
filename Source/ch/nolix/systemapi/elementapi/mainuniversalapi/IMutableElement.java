//package declaration
package ch.nolix.systemapi.elementapi.mainuniversalapi;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Resettable;
import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IMutableElement} is a {@link Specified}.
 * The attributes of a {@link IMutableElement} can be mutated separately.
 * The attributes of a {@link IMutableElement} can be reset together.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @param <ME> is the type of a {@link IMutableElement}.
 */
@AllowDefaultMethodsAsDesignPattern
public interface IMutableElement<ME extends IMutableElement<ME>> extends Resettable, Specified {
	
	//method declaration
	/**
	 * Adds or changes the given attribute to the current {@link IMutableElement}.
	 * 
	 * @param attribute
	 */
	void addOrChangeAttribute(INode<?> attribute);
	
	//method
	/**
	 * Adds or changes the given attributes to the current {@link IMutableElement}.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	default void addOrChangeAttribute(final INode<?>... attributes) {
		
		//Iterates the given attributes.
		for (final var a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link IMutableElement}.
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
	 * Adds or changes the given attributes to the current {@link IMutableElement}.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	default void addOrChangeAttributes(final Iterable<? extends INode<?>> attributes) {
		
		//Iterates the given attributes.
		for (final var a : attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	//method
	/**
	 * Resets the current {@link IMutableElement} from the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	default void resetFromAttributes(final Iterable<? extends INode<?>> attributes) {
		
		reset();
		
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Resets the current {@link IMutableElement} from the file with the given filePath.
	 * 
	 * @param filePath
	 * @throws InvalidArgumentException if the given filePath is not valid.
	 * @throws InvalidArgumentException if the file with the given filePath does not represent a {@link Node}.
	 */
	default void resetFromFileWithFilePath(final String filePath) {
		resetFromSpecification(Node.fromFile(filePath));
	}
	
	//method
	/**
	 * Resets the current {@link IMutableElement} from the given specification.
	 * 
	 * @param specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	default void resetFromSpecification(final INode<?> specification) {
		resetFromAttributes(specification.getRefChildNodes());
	}
	
	//method
	/**
	 * Resets the current {@link IMutableElement} from the given specification.
	 * 
	 * @param specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	default void resetFromSpecification(final String specification) {
		resetFromSpecification(Node.fromString(specification));
	}
}
