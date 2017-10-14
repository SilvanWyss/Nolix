//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.controllerInterfaces.IDataController;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.specificationInterfaces.Specified;

//abstract class
/**
 * An element is specified.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 140
 */
public abstract class Element implements IDataController, Specified {
	
	//requests
	public static final String TYPE_REQUEST = "Type";
	public static final String TYPES_REQUEST = "Types";

	//method
	/**
	 * @param object
	 * @return true if this element equals the given object.
	 */
	public final boolean equals(final Object object) {
	
		//Handles the option that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the option that this element is not of the same class as the given object.		
		if (getClass() != object.getClass()) {
			return false;
		}
		
		//Casts the given object to an element.
		final Element element = (Element)object;
		
		//Handles the option that the specification of this element
		//does not equal the specification of the given element.
		if (!element.getSpecification().equals(getSpecification())) {
			return false;
		}
		
		return true;
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests from this element.
	 * @throws InvalidArgumentException if the given request is not valid.
	 */
	public StandardSpecification getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case TYPE_REQUEST:
				return new StandardSpecification(getType());
			case TYPES_REQUEST:
				return new StandardSpecification(getTypes().toString());
			default:
				throw new InvalidArgumentException(
					new ArgumentName("request"),
					new Argument(request)
				);
		}	
	}
	
	//method
	/**
	 * @return the type of this element.
	 */
	public final String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return the types of this element ordered from deepest to highest.
	 */	
	public final List<String> getTypes() {
		
		final List<String> types = new List<String>();
		
		Class<?> class_ = getClass();
		while (class_.getSuperclass() != null) {
			types.addAtEnd(class_.getSimpleName());
			class_ = class_.getSuperclass();
		}
		
		return types;
	}
	
	//method
	/**
	 * @param type
	 * @return true if this element has the given type or super type
	 */
	public final boolean hasTypeOrSuperType(final String type) {
		
		Class<?> class_ = getClass();
		
		while (class_.getSuperclass() != null) {
			
			//Handles the case if this element has the current class as type or super type.
			if (class_.getSimpleName().equals(type)) {
				return true;
			}
			
			class_ = class_.getSuperclass();
		}
		
		return false;
	}
				
	//method
	/**
	 * @return a string representation of this element.
	 */
	public final String toString() {
		return getSpecification().toReproducingString();
	}
	
	//default method
	/**
	 * @return a formated string representation of this element.
	 */
	public final String toFormatedString() {
		return getSpecification().toFormatedReproducingString();
	}
}
