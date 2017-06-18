/*
 * file:	Element.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	180
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.specificationInterfaces.Specified;

//class
/**
 * An element is a specifiable object and a level 2 controller.
 */
public abstract class Element implements Specified {
		
	//requests
	public static final String TYPE_REQUEST = "Type";
	public static final String TYPES_REQUEST = "Types";
	
	//method
	/**
	 * @param object
	 * @return true if this element equals the given object
	 */
	public final boolean equals(Object object) {
	
		//Returns false if the given object is null.
		if (object == null) {
			return false;
		}
		
		if (!object.getClass().equals(object.getClass())) {
			return false;
		}
		
		//Casts the given object to an element.
		Element element = (Element)object;
		
		//Return false if the given element has not the same type as this element.
		if (!element.hasType(getType())) {
			return false;
		}
		
		//Returns false if the specification of the given element does not equal to the specification of this element.
		if (!element.getSpecification().equals(getSpecification())) {
			return false;
		}
		
		return true;
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests
	 * @throws Exception if the given request is not valid
	 */
	public Specification getData(Statement request) {
		switch (request.toString()) {
			case TYPE_REQUEST:
				return new Specification(getType());
			case TYPES_REQUEST:
				return new Specification(getTypes());
			default:
				throw new InvalidArgumentException(
					new ArgumentName("request"),
					new Argument(request)
				);
		}	
	}
	
	//method
	/**
	 * @return the type of this element
	 */
	public final String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return the types of this element ordered from deepest to highest
	 */	
	public final List<String> getTypes() {
		List<String> types = new List<String>();
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
	 * @return true if this element has the given type as own type or super type
	 */
	public final boolean hasTypeOrSuperType(String type) {
		Class<?> class_ = getClass();
		while (class_.getSuperclass() != null) {
			if (class_.getSimpleName().equals(type)) {
				return true;
			}
			class_ = class_.getSuperclass();
		}
		return false;
	}
		

		

			
	//method
	/**
	 * @return a string representation of this element
	 */
	public final String toString() {
		return getSpecification().toReproducingString();
	}
	
	//default method
	/**
	 * @return a formated string representation of this element
	 */
	public final String toFormatedString() {
		return getSpecification().toFormatedReproducingString();
	}
}
