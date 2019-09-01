//package declaration
package ch.nolix.common.generalSkillAPI;

import ch.nolix.common.containers.List;

//interface
/**
 * A {@link TypeRequestable} has a type.
 * 
 * @author Silvan Wyss
 * @month 2018-11
 * @lines 100
 */
public interface TypeRequestable {
	
	//default method
	/**
	 * @return the type of the current {@link TypeRequestable}.
	 */
	public default String getType() {
		return getClass().getSimpleName();
	}
	
	//default method
	/**
	 * @return the types of the current {@link TypeRequestable}
	 * ordered from the most concrete to the most general.
	 */
	public default List<String> getTypes() {
		
		final var types = new List<String>();
		
		/*
		 * Iterates the classes of the current type requestable
		 * ordered from the most concrete to the most general.
		 */
		Class<?> c = getClass();
		while (c.getSuperclass() != null) {
				types.addAtEnd(c.getSimpleName());
				c = c.getSuperclass();
		}
		
		return types;
	}
	
	//default method
	/**
	 * @param concreteType
	 * @return true if the current {@link TypeRequestable} is of the given concrete type.
	 */
	public default boolean isOfConcreteType(final Class<?> concreteType) {
		return (getClass() == concreteType);
	}
	
	//default method
	/**
	 * @param concreteType
	 * @return true if the current {@link TypeRequestable} is of the given concrete type.
	 */
	public default boolean isOfConcreteType(final String concreteType) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return getClass().getSimpleName().equals(concreteType);
	}
	
	//default method
	/**
	 * @param type
	 * @return true if the current {@link TypeRequestable} is of the given type.
	 */
	public default boolean isOfType(final Class<?> type) {
		return type.isAssignableFrom(getClass());
	}
	
	//default method
	/**
	 * @param type
	 * @return true if the current {@link TypeRequestable} is of the given type.
	 */
	public default boolean isOfType(final String type) {
		
		/*
		 * For a better performance, this implementation does not use all comfortable methods.
		 * 
		 * shorter implementation:
		 * 
		 * return getTypes().contains(typeOrSuperType);
		 */
		
		//Iterates the classes of this type requestable object.
		Class<?> c = getClass();
		while (c.getSuperclass() != null) {
			
			//Handles the case that the current class is the given type or super type.
			if (c.getSimpleName().equals(type)) {
				return true;
			}
			
			c = c.getSuperclass();
		}
		
		return false;
	}
}
