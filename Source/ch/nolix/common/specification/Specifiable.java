/*
 * file:	Specifiable.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	140
 */

//package declaration
package ch.nolix.common.specification;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.interfaces.Resettable;
import ch.nolix.common.interfaces.Typed;
import ch.nolix.common.util.Validator;

//interface
/**
 * A specifiable object is a typed object with the properties:
 *  -All official attributes can be got from together.
 *  -All official attributes can be set to uniquely.
 *  -All official attributes can be reset together.
 */
public interface Specifiable extends Typed, Resettable {

	//abstract method
	/**
	 * @return the attributes of this specifiable
	 */
	public abstract List<Specification> getAttributes();
	
	//default method
	/**
	 * @return the specification of this specifiable
	 */
	public default Specification getSpecification() {
		return new Specification(getType(), getAttributes());
	}
	
	//default method
	/**
	 * @param type
	 * @return the specification of this specifiable as the given type
	 * @throws Exception if the given type is null or an empty string
	 */
	public default Specification getSpecificationAs(String type) {
		
		//Checks the given type.
		Validator.throwExceptionIfStringIsNullOrEmpty("type", type);
		
		return new Specification(type, getAttributes());
	}
	
	//default method
	/**
	 * Loads the specification in the file or folder with the given path to this specifiable.
	 * 
	 * @param path
	 * @throws Exception if an error occurs
	 */
	public default void load(String path) {
		Specification specification = new Specification();
		specification.loadFromFile(path);
		setSpecification(specification);
	}
	
	//default method
	/**
	 * Saves this specifiable object to the file or directory with the given path.
	 * 
	 * @param path
	 * @throws Exception if an error occurs
	 */
	public default void save(String path) {
		getSpecification().saveToFile(path);
	}
	
	public default void saveAs(String type, String path) {
		getSpecificationAs(type).saveToFile(path);
	}
	
	//abstract method
	/**
	 * Sets the given attribute to this specifiable.
	 * 
	 * @param attribute
	 */
	public abstract void addOrChangeAttribute(Specification attribute);
	
	//default method
	/**
	 * Sets the given attribute to this specifiable.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public default void addOrChangeAttribute(String attribute) {
		addOrChangeAttribute(new Specification(attribute));
	}
	
	//default method
	/**
	 * Sets the given attributes to this specifiable.
	 * 
	 * @param attributes
	 * @throws Exception if the given attributes are not valid
	 */
	public default void addOrChangeAttributes(Iterable<Specification> attributes) {
		for (Specification a: attributes) {
			addOrChangeAttribute(a);
		}
	}
	
	public default void reset(Iterable<Specification> attributes) {
		reset();
		addOrChangeAttributes(attributes);
	}
	
	//default method
	/**
	 * Resets this specifiable and sets the given specification to this specifiable.
	 * 
	 * @param specification
	 * @throws Exception if the given specification is not valid
	 */
	public default void setSpecification(Specification specification) {
		
		if (!specification.hasHeader(getType())) {
			System.out.println(specification);
		}
		
		reset();
		
		specification.getRefAttributes().forEach(a -> addOrChangeAttribute(a));
	}
	
	//default method
	/**
	 * Resets this specifiable and sets the given specification to this specifiable.
	 * 
	 * @param specification
	 * @throws Exception if the given specification is not valid
	 */
	public default void setSpecification(String specification) {
		setSpecification(new Specification(specification));
	}
}
