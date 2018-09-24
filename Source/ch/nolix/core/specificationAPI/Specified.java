//package declaration
package ch.nolix.core.specificationAPI;

//own imports
import ch.nolix.core.XMLDocument.XMLNode;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.skillInterfaces.Typed;

//interface
/**
 * A specified object is a typed object whose official attributes can be got together.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 */
public interface Specified extends Typed {

	//abstract method
	/**
	 * @return the attributes of this specified object.
	 */
	public abstract List<DocumentNode> getAttributes();
	
	//default method
	/**
	 * @return the specification of this specified object.
	 */
	public default DocumentNode getSpecification() {
		return new DocumentNode(getType(), getAttributes());
	}
	
	//default method
	/**
	 * @param type
	 * @return the specification of this specified as the given type.
	 * @throws NullArgumentException if the given type is not an instance.
	 * @throws EmptyArgumentException if the given type is empty.
	 */
	public default DocumentNode getSpecificationAs(final String type) {
		return new DocumentNode(type, getAttributes());
	}
	
	//default method
	/**
	 * @return the specification of this specified object without header.
	 */
	public default DocumentNode getSpecificationWithoutHeader() {
		return new DocumentNode(getAttributes());
	}
	
	//default method
	/**
	 * Saves this specified object to the file or directory with the given path.
	 * 
	 * @param path
	 * @throws Exception if an error occurs
	 */
	public default void saveTo(final String path) {
		getSpecification().saveToFile(path);
	}
	
	//default method
	/**
	 * Saves this specified object as the given type to the file or directory with the given path.
	 * 
	 * @param type
	 * @param path
	 */
	public default void saveAsTo(final String type, final String path) {
		getSpecificationAs(type).saveToFile(path);
	}
	
	//default method
	/**
	 * @return a XML specification of this specified object.
	 */
	public default XMLNode toXML() {
		return getSpecification().toXML();
	}
}
