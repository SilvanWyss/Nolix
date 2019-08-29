//package declaration
package ch.nolix.element.core;

import ch.nolix.core.containers.List;
import ch.nolix.core.node.Node;
import ch.nolix.core.node.BaseNode;
import ch.nolix.element.baseAPI.IElement;

//class
/**
 * A boolean is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 120
 */
public final class Boolean implements IElement {
	
	//type name
	public static final String TYPE_NAME = "Boolean";
	
	//default value
	public static final boolean DEFAULT_VALUE = false;
	
	//constants
	public static final Boolean FALSE = new Boolean(false);
	public static final Boolean TRUE = new Boolean(true);
	
	//constants
	private static final String FALSE_HEADER = "False";
	private static final String TRUE_HEADER = "True";
	
	//method
	/**
	 * @param specification
	 * @return a new boolean from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Boolean fromSpecification(final BaseNode specification) {
		return new Boolean(specification.getOneAttributeAsBoolean());
	}
	
	//attribute
	private final boolean value;
	
	//constructor
	/**
	 * Creates a new boolean that is false.
	 */
	public Boolean() {
		
		//Calls other constructor.
		this(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new boolean with the given value.
	 * 
	 * @param value
	 */
	public Boolean(final boolean value) {
		
		//Sets the value of this boolean.
		this.value = value;
	}
	
	//method
	/**
	 * @return the attributes of this boolean.
	 */
	@Override
	public final List<Node> getAttributes() {
		
		final var attributes = new List<Node>();
		
		//Handles the case that this boolean is false.
		if (isFalse()) {
			attributes.addAtEnd(Node.fromString(FALSE_HEADER));
		}
		
		//Handles the case that this boolean ist true.
		else {
			attributes.addAtEnd(Node.fromString(TRUE_HEADER));
		}

		return attributes;
	}
	
	//method
	/**
	 * @return a new copy of this boolean.
	 */
	public final Boolean getCopy() {
		return new Boolean(getValue());
	}
	
	//method
	/**
	 * @return the value of this boolean.
	 */
	public final boolean getValue() {
		return value;
	}
	
	//method
	/**
	 * @return true if this boolean is false.
	 */
	public final boolean isFalse() {
		return !value;
	}
	
	//method
	/**
	 * @return true if this boolean is true.
	 */
	public final boolean isTrue() {
		return value;
	}
}
