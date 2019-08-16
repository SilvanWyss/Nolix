//package declaration
package ch.nolix.element.core;

import ch.nolix.core.containers.List;
import ch.nolix.core.node.Node;
import ch.nolix.element.baseAPI.IElement;

//class
/**
 * An integer is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 */
public class Integer implements IElement {
	
	//default value
	private static final int DEFAULT_VALUE = 0;
	
	//attribute
	private final int value;
	
	//constructor
	/**
	 * Creates a new integer with a default value.
	 */
	public Integer() {
		
		//Calls other constructor.
		this(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new integer with the given value.
	 * 
	 * @param value
	 */
	public Integer(final int value) {
		
		//Sets the value of this integer.
		this.value = value;
	}
	
	//method
	/**
	 * @return the attributes of this integer.
	 */
	@Override
	public final List<Node> getAttributes() {
		return new List<>(Node.createFromString(java.lang.Integer.toString(getValue())));
	}
	
	//method
	/**
	 * @return the value of this integer.
	 */
	public final int getValue() {
		return value;
	}
	
	//method
	/**
	 * @param value
	 * @return true if this integer has the given value.
	 */
	public final boolean hasValue(final int value) {
		return (getValue() == value);
	}
	
	//method
	/**
	 * @return true if the value of this integer is negative.
	 */
	public final boolean isNegative() {
		return (getValue() < 0);
	}
	
	//method
	/**
	 * @return true if the value of this integer is positive.
	 */
	public final boolean isPositive() {
		return (getValue() > 0);
	}
}
