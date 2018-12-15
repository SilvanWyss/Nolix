//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.Statement;
import ch.nolix.core.entity.Entity;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//abstract class
/**
 * An element is specified.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 100
 */
public abstract class Element<E extends Element<E>> extends Entity<E> {
	
	//requests
	public static final String TYPE_REQUEST = "Type";
	public static final String TYPES_REQUEST = "Types";

	//method
	/**
	 * @param object
	 * @return true if this element equals the given object.
	 */
	@Override
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
		final var element = (Element<?>)object;
		
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
				throw new InvalidArgumentException(VariableNameCatalogue.REQUEST, request, "is not valid");
		}
	}
	
	//method
	/**
	 * @return the type of this type element.
	 */
	@Override
	public String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return a string representation of this element.
	 */
	@Override
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
