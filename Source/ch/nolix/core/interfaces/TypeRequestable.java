//package declaration
package ch.nolix.core.interfaces;

//own import
import ch.nolix.core.container.List;

//interface
/**
 * A type requestable object can return information about its type.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 60
 */
public interface TypeRequestable {

	//default method
	/**
	 * @return the type of this type requestable object.
	 */
	public default String getType() {
		return getClass().getSimpleName();
	}
	
	//default method
	/**
	 * @return the types of this type requestable object ordered from deepest to highest.
	 */	
	public default List<String> getTypes() {
		
		final List<String> types = new List<>();
		
		//Iterates the classes of this type requestable object ordered from deepest to highest.
		Class<?> c = getClass();
		while (c.getSuperclass() != null) {
			types.addAtEnd(c.getSimpleName());
			c = c.getSuperclass();
		}
		
		return types;
	}
	
	//default method
	/**
	 * @param type
	 * @return true if this type requestable element has the given type or super type.
	 */
	public default boolean hasTypeOrSuperType(final String type) {
		
		//Iterates the classes of this type requestable object ordered from deepest to highest.
		Class<?> class_ = getClass();
		while (class_.getSuperclass() != null) {
			
			// Handles the option that the current class is the given type.
			if (class_.getSimpleName().equals(type)) {
				return true;
			}
			
			class_ = class_.getSuperclass();
		}
		
		return false;
	}
}
