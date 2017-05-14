//package declaration
package ch.nolix.core.specification;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Typed;

//interface
/**
 * A specified object is a typed object all official attributes can be got from together.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public interface Specified extends Typed {

	//abstract method
	/**
	 * @return the attributes of this specified object.
	 */
	public abstract List<Specification> getAttributes();
	
	//default method
	/**
	 * @return the specification of this specified object.
	 */
	public default Specification getSpecification() {
		return new Specification(getType(), getAttributes());
	}
	
	//default method
	/**
	 * @param type
	 * @return the specification of this specified as the given type.
	 * @throws NullArgumentException if the given type is null.
	 * @throws EmptyArgumentException if the given type is empty.
	 */
	public default Specification getSpecificationAs(final String type) {
		return new Specification(type, getAttributes());
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
