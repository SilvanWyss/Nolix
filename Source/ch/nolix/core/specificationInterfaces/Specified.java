//package declaration
package ch.nolix.core.specificationInterfaces;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.skillInterfaces.Typed;
import ch.nolix.core.specification.StandardSpecification;

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
	public abstract List<StandardSpecification> getAttributes();
	
	//default method
	/**
	 * @return the specification of this specified object.
	 */
	public default StandardSpecification getSpecification() {
		return new StandardSpecification(getType(), getAttributes());
	}
	
	//default method
	/**
	 * @param type
	 * @return the specification of this specified as the given type.
	 * @throws NullArgumentException if the given type is not an instance.
	 * @throws EmptyArgumentException if the given type is empty.
	 */
	public default StandardSpecification getSpecificationAs(final String type) {
		return new StandardSpecification(type, getAttributes());
	}
	
	//default method
	/**
	 * @return the specification of this specified object without header.
	 */
	public default StandardSpecification getSpecificationWithoutHeader() {
		return new StandardSpecification(getAttributes());
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
}
