//package declaration
package ch.nolix.element.core;

import ch.nolix.core.argument.Argument;
import ch.nolix.core.argument.ArgumentName;
//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.Statement;
import ch.nolix.core.entity.Entity;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.skillInterfaces.Castable;
import ch.nolix.core.skillInterfaces.TypeRequestable;

//abstract class
/**
 * An element is specified.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 100
 */
public abstract class Element extends Entity implements Castable, TypeRequestable {
	
	//requests
	public static final String TYPE_REQUEST = "Type";
	public static final String TYPES_REQUEST = "Types";

	//method
	/**
	 * @param object
	 * @return true if this element equals the given object.
	 */
	public final boolean equals(final Object object) {
	
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that this element is not of the same class as the given object.		
		if (getClass() != object.getClass()) {
			return false;
		}
		
		//Casts the given object to an element.
		final Element element = (Element)object;
		
		//Handles the case that the specification of this element
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
	public DocumentNode getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case TYPE_REQUEST:
				return new DocumentNode(getType());
			case TYPES_REQUEST:
				return new DocumentNode(getTypes().toString());
			default:
				throw new InvalidArgumentException(
					new ArgumentName("request"),
					new Argument(request)
				);
		}	
	}
	
	//method
	/**
	 * @return the type of this type element.
	 */
	public final String getType() {
		return TypeRequestable.super.getType();
	}
				
	//method
	/**
	 * @return a string representation of this element.
	 */
	public final String toString() {
		return getSpecification().toString();
	}
	
	//default method
	/**
	 * @return a formated string representation of this element.
	 */
	public final String toFormatedString() {
		return getSpecification().toFormatedString();
	}
}
