//package declaration
package ch.nolix.element.elementAPI;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.generalskillapi.TypeRequestable;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.processproperty.WriteMode;
import ch.nolix.common.xml.XMLNode;

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
	LinkedList<Node> getAttributes();
	
	//method
	/**
	 * @return the specification of the current {@link IElement}.
	 */
	default Node getSpecification() {
		return new Node(getType(), getAttributes());
	}
	
	//method
	/**
	 * @param type
	 * @return the specification of the current {@link IElement} as the given type.
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws InvalidArgumentException if the given type is blank.
	 */
	default Node getSpecificationAs(final String type) {
		return new Node(type, getAttributes());
	}
	
	//method
	/**
	 * @return the specification of the current {@link IElement} without header.
	 */
	default Node getSpecificationWithoutHeader() {
		return new Node(getAttributes());
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
	 * if the given writeMode flag={@link WriteMode#THROW_EXCEPTION_WHEN_EXISTS_ALREADY}
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
	 * if the given writeMode flag={@link WriteMode#THROW_EXCEPTION_WHEN_EXISTS_ALREADY}
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
}
