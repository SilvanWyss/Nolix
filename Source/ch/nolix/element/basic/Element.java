/*
 * file:	Element.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	180
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.controller.ILevel2Controller;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.specification.Specifiable;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;

//class
/**
 * An element is a specifiable object and a level 2 controller.
 */
public abstract class Element implements
	Specifiable,
	ILevel2Controller {
		
	//requests
	public final static String TYPE_REQUEST = "Type";
	public final static String TYPES_REQUEST = "Types";
	
	//command
	private final static String RESET = "Reset";
	
	//command prefix
	private final static String SET_COMMAND_PREFIX = "Set";
	
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
	public Object getRawData(Statement request) {
		switch (request.toString()) {
			case TYPE_REQUEST:
				return getType();
			case TYPES_REQUEST:
				return getTypes();
			default:
				throw new InvalidArgumentException("request", request);
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
		
	//element
	/**
	 * Runs the given command.
	 * 
	 * @param command
	 * @throws Exception if the given command is not valid
	 */
	public void run(Statement command) {
		
		//Extracts the header of the given command.
		String header = command.getHeader();
		
		//Handles the case when the given command is a set attribute command.
		if (header.startsWith(SET_COMMAND_PREFIX)) {
			
			if (header.length() < 4) {
				throw new InvalidArgumentException("command", command);
			}
			
			addOrChangeAttribute(new Specification(header.substring(3), command.getRefAttributes()));
			return;
		}
		
		//Handels the case when the given command is a reset command.
		if (header.equals(RESET)) {
			
			if (!command.containsAttributes()) {
				reset();
			}
			else {
				reset();
				addOrChangeAttributes(command.getRefAttributes());
			}
			
			return;
		}
		
		//Handles the case when the given command is not valid.
		throw new InvalidArgumentException("commands", command);
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
