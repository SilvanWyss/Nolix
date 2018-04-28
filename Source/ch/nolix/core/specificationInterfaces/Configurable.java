/*
 * file:	Configurable.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	60
 */

//package declaration
package ch.nolix.core.specificationInterfaces;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;

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
public interface Configurable<C extends Configurable<C>> extends Specifiable {
	
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
	public abstract ReadContainer<Configurable<?>> getRefConfigurables();
	
	//default method
	/**
	 * @return the configurable objects of this configurable object recursively
	 */
	public default ReadContainer<Configurable<?>> getRefConfigurablesRecursively() {
		final List<Configurable<?>> elements = new List<Configurable<?>>(getRefConfigurables());
		getRefConfigurables().forEach(r -> elements.addAtEnd(r.getRefConfigurablesRecursively()));
		return new ReadContainer<Configurable<?>>(elements);
	}
	
	//abstract method
	/**
	 * Resets the configuration of this configurable object.
	 * 
	 * @return this configurable object.
	 */
	public abstract C resetConfiguration();
}
