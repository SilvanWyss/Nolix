//package declaration
package ch.nolix.element.baseAPI;

//own imports
import ch.nolix.common.XML.XMLNode;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.generalSkillAPI.TypeRequestable;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.Node;

//interface
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 140
 */
public interface IElement extends TypeRequestable {
		
	//method declaration
	/**
	 * @return the attributes of the current {@link IElement}.
	 */
	public abstract LinkedList<Node> getAttributes();
	
	//method
	/**
	 * @return the specification of the current {@link IElement}.
	 */
	public default Node getSpecification() {
		return new Node(getType(), getAttributes());
	}
	
	//method
	/**
	 * @param type
	 * @return the specification of the current specified as the given type.
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws EmptyArgumentException if the given type is empty.
	 */
	public default Node getSpecificationAs(final String type) {
		return new Node(type, getAttributes());
	}
	
	//method
	/**
	 * @return the specification of the current {@link IElement} without header.
	 */
	public default Node getSpecificationWithoutHeader() {
		return new Node(getAttributes());
	}
	
	//method
	/**
	 * Saves the current {@link IElement} as the given type
	 * to the file with the given file path.
	 * 
	 * @param type
	 * @param filePath
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws EmptyArgumentException if the given type is empty.
	 * @throws InvalidArgumentException
	 * if a file system item with the given file path exists already.
	 */
	public default void saveAsTo(final String type, final String filePath) {
		
		//Calls other method.
		saveAsTo(type, filePath, false);
	}
	
	//method
	/**
	 * Saves the current {@link IElement} as the given type
	 * to the file with the given path.
	 * 
	 * @param type
	 * @param filePath
	 * @param overwrite
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws EmptyArgumentException if the given type is empty.
	 * @throws InvalidArgumentException if the given overwrite flag is false
	 * and a file system item with the given file path exists already.
	 */
	public default void saveAsTo(
		final String type,
		final String filePath,
		final boolean overwrite
	) {
		getSpecificationAs(type).saveToFile(filePath, overwrite);
	}
	
	//method
	/**
	 * Saves the current {@link IElement} to the file with the given file path.
	 * 
	 * @param filePath
	 * @throws InvalidArgumentException
	 * if a file system item with the given file path exists already.
	 */
	public default void saveTo(final String filePath) {
		
		//Calls other method.
		saveTo(filePath, false);
	}
	
	//method
	/**
	 * Saves the current {@link IElement} to the file with the given file path.
	 * 
	 * @param filePath
	 * @param overwrite
	 * @throws InvalidArgumentException if the given overwrite flag is false
	 * and a file system item with the given file path exists already.
	 */
	public default void saveTo(final String filePath, final boolean overwrite) {
		getSpecification().saveToFile(filePath, overwrite);
	}
	
	//method
	/**
	 * @return a formated string representation of the current {@link IElement}.
	 */
	public default String toFormatedString() {
		return getSpecification().toFormatedString();
	}
	
	//method
	/**
	 * @return a XML representation of the current {@link IElement}.
	 */
	public default XMLNode toXML() {
		return getSpecification().toXML();
	}
	
	//method
	/**
	 * @return a XML representation of the current {@link IElement} as the given type.
	 * @param type
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws EmptyArgumentException if the given type is empty.
	 */
	public default XMLNode toXMLAs(final String type) {
		return getSpecificationAs(type).toXML();
	}
}
