//package declaration
package ch.nolix.common.generalSkillAPI;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.language.EnglishNounHelper;

//interface
/**
 * A {@link TypeRequestable} has a type.
 * 
 * @author Silvan Wyss
 * @month 2018-11
 * @lines 110
 */
public interface TypeRequestable {
	
	//method
	/**
	 * @return the type of the current {@link TypeRequestable}.
	 */
	default String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return the plural of the type of the current {@link TypeRequestable}.
	 */
	default String getTypeInPlural() {
		return EnglishNounHelper.getPlural(getType());
	}
	
	//method
	/**
	 * @return the types of the current {@link TypeRequestable}
	 * ordered from the most concrete to the most general.
	 */
	default LinkedList<String> getTypes() {
		
		final var types = new LinkedList<String>();
		
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
	
	//method
	/**
	 * @param concreteType
	 * @return true if the current {@link TypeRequestable} is of the given concrete type.
	 */
	default boolean isOfConcreteType(final Class<?> concreteType) {
		return (getClass() == concreteType);
	}
	
	//method
	/**
	 * @param concreteType
	 * @return true if the current {@link TypeRequestable} is of the given concrete type.
	 */
	default boolean isOfConcreteType(final String concreteType) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return getClass().getSimpleName().equals(concreteType);
	}
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link TypeRequestable} is of the given type.
	 */
	default boolean isOfType(final Class<?> type) {
		return type.isAssignableFrom(getClass());
	}
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link TypeRequestable} is of the given type.
	 */
	default boolean isOfType(final String type) {
		
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
