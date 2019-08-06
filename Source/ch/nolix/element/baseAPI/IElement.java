//package declaration
package ch.nolix.element.baseAPI;

import ch.nolix.core.XML.XMLNode;
import ch.nolix.core.containers.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.generalSkillAPI.TypeRequestable;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//interface
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 140
 */
public interface IElement extends TypeRequestable {
		
	//abstract method
	/**
	 * @return the attributes of the current {@link IElement}.
	 */
	public abstract List<DocumentNode> getAttributes();
	
	//default method
	/**
	 * @return the specification of the current {@link IElement}.
	 */
	public default DocumentNode getSpecification() {
		return new DocumentNode(getType(), getAttributes());
	}
	
	//default method
	/**
	 * @param type
	 * @return the specification of the current specified as the given type.
	 * @throws NullArgumentException if the given type is null.
	 * @throws EmptyArgumentException if the given type is empty.
	 */
	public default DocumentNode getSpecificationAs(final String type) {
		return new DocumentNode(type, getAttributes());
	}
	
	//default method
	/**
	 * @return the specification of the current {@link IElement} without header.
	 */
	public default DocumentNode getSpecificationWithoutHeader() {
		return new DocumentNode(getAttributes());
	}
	
	//default method
	/**
	 * Saves the current {@link IElement} as the given type
	 * to the file with the given file path.
	 * 
	 * @param type
	 * @param filePath
	 * @throws NullArgumentException if the given type is null.
	 * @throws EmptyArgumentException if the given type is empty.
	 * @throws InvalidArgumentException
	 * if a file system item with the given file path exists already.
	 */
	public default void saveAsTo(final String type, final String filePath) {
		
		//Calls other method.
		saveAsTo(type, filePath, false);
	}
	
	//default method
	/**
	 * Saves the current {@link IElement} as the given type
	 * to the file with the given path.
	 * 
	 * @param type
	 * @param filePath
	 * @param overwrite
	 * @throws NullArgumentException if the given type is null.
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
	
	//default method
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
	
	//default method
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
	
	//default method
	/**
	 * @return a formated string representation of the current {@link IElement}.
	 */
	public default String toFormatedString() {
		return getSpecification().toFormatedString();
	}
	
	//default method
	/**
	 * @return a XML representation of the current {@link IElement}.
	 */
	public default XMLNode toXML() {
		return getSpecification().toXML();
	}
	
	//default method
	/**
	 * @return a XML representation of the current {@link IElement} as the given type.
	 * @param type
	 * @throws NullArgumentException if the given type is null.
	 * @throws EmptyArgumentException if the given type is empty.
	 */
	public default XMLNode toXMLAs(final String type) {
		return getSpecificationAs(type).toXML();
	}
}
