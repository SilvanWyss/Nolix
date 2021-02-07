//package declaration
package ch.nolix.element.elementapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.processproperty.WriteMode;
import ch.nolix.common.xml.XMLNode;

//interface
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 160
 */
public interface IElement {
	
	//method declaration
	/**
	 * Fills up the attributes into the given list.
	 * 
	 * @param list
	 */
	void fillUpAttributesInto(LinkedList<Node> list);
	
	//method
	/**
	 * @return the attributes of the current {@link IElement}.
	 */
	default LinkedList<Node> getAttributes() {
		
		final var attributes = new LinkedList<Node>();
		
		fillUpAttributesInto(attributes);
		
		return attributes;
	}
	
	//method
	/**
	 * @return the specification of the current {@link IElement}.
	 */
	default Node getSpecification() {
		return Node.withHeaderAndAttributes(getType(), getAttributes());
	}
	
	//method
	/**
	 * @param type
	 * @return the specification of the current {@link IElement} as the given type.
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws InvalidArgumentException if the given type is blank.
	 */
	default Node getSpecificationAs(final String type) {
		return Node.withHeaderAndAttributes(type, getAttributes());
	}
	
	//method
	/**
	 * @return the specification of the current {@link IElement} without header.
	 */
	default Node getSpecificationWithoutHeader() {
		return Node.withAttributes(getAttributes());
	}
	
	//method
	/**
	 * Saves the current {@link IElement} as the given type to the file with the given path.
	 * 
	 * @param type
	 * @param path
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws InvalidArgumentException if the given type is blank.
	 * @throws ArgumentIsNullException if the given path is null.
	 * @throws InvalidArgumentException if the given path is blank.
	 * @throws InvalidArgumentException if there exists already a file system item with the given path.
	 */
	default void saveAsTo(final String type, final String path) {
		
		//Calls other method.
		saveAsTo(type, path, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY);
	}
	
	//method
	/**
	 * Saves the current {@link IElement} as the given type to the file with the given path.
	 * 
	 * @param type
	 * @param path
	 * @param writeMode
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws InvalidArgumentException if the given type is blank.
	 * @throws ArgumentIsNullException if the given path is null.
	 * @throws InvalidArgumentException if the given path is blank.
	 * @throws InvalidArgumentException
	 * if the given writeMode = {@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
	 * and there exists already a file system item with the given path.
	 */
	default void saveAsTo(final String type, final String path, final WriteMode writeMode) {
		getSpecificationAs(type).saveToFile(path, writeMode);
	}
	
	//method
	/**
	 * Saves the current {@link IElement} to the file with the given file path.
	 * 
	 * @param filePath
	 * @throws InvalidArgumentException
	 * if a file system item with the given file path exists already.
	 */
	default void saveTo(final String filePath) {
		
		//Calls other method.
		saveTo(filePath, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY);
	}
	
	//method
	/**
	 * Saves the current {@link IElement} to the file with the given path.
	 * 
	 * @param path
	 * @param writeMode
	 * @throws ArgumentIsNullException if the given path is null.
	 * @throws InvalidArgumentException if the given path is blank.
	 * @throws InvalidArgumentException
	 * if the given writeMode = {@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
	 * and there exists already a file system item with the given path.
	 */
	default void saveTo(final String path, final WriteMode writeMode) {
		getSpecification().saveToFile(path, writeMode);
	}
	
	//method
	/**
	 * @return a formated string representation of the current {@link IElement}.
	 */
	default String toFormatedString() {
		return getSpecification().toFormatedString();
	}
	
	//method
	/**
	 * @return a XML representation of the current {@link IElement}.
	 */
	default XMLNode toXML() {
		return getSpecification().toXML();
	}
	
	//method
	/**
	 * @return a XML representation of the current {@link IElement} as the given type.
	 * @param type
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws EmptyArgumentException if the given type is empty.
	 */
	default XMLNode toXMLAs(final String type) {
		return getSpecificationAs(type).toXML();
	}
	
	//method
	private String getType() {
		return getClass().getSimpleName();
	}
}
