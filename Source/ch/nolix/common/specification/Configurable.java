/*
 * file:	Configurable.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	60
 */

//package declaration
package ch.nolix.common.specification;

//own import
import ch.nolix.common.container.List;

//interface
/**
 * A configurable object is a specifiable object that:
 * -has a type and super types
 * -can have a name
 * -can have a group
 * -can contain other configurable objects
 *   
 * The default methods of this interface need not to be overwritten.
 */
public interface Configurable extends Specifiable {
	
	//abstract method
	/**
	 * @param name
	 * @return true if this configurable has the given name
	 */
	public abstract boolean hasName(String name);
	
	//abstract method
	/**
	 * @param role
	 * @return true if this configurable has the given role
	 */
	public abstract boolean hasRole(String role);
	
	//abstract method
	/**
	 * @param group
	 * @return true if this configurable has the given group
	 */
	public abstract boolean hasToken(String group);
	
	//abstract method
	/**
	 * @param type
	 * @return true if this configurable object has the given type or super type
	 */
	public abstract boolean hasTypeOrSuperType(String type);	
	
	//abstract method
	/**
	 * @return the configurable objects of this configurable objects
	 */
	public abstract List<Configurable> getRefConfigurables();
	
	//default method
	/**
	 * @return the configurable objects of this configurable object recursively
	 */
	public default List<Configurable> getRefConfigurablesRecursively() {
		List<Configurable> elements = getRefConfigurables().getCopy();
		getRefConfigurables().forEach(r -> elements.addAtEnd(r.getRefConfigurablesRecursively()));
		return elements;
	}
	
	//abstract method
	/**
	 * Resets the configuration of this configurable object.
	 */
	public abstract void resetConfiguration();
}
