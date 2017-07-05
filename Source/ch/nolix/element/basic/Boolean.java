/*
 * file:	Boolean.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	130
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;

//class
public final class Boolean extends Element {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Boolean";
	
	//value names
	private static final String FALSE = "False";
	private static final String TRUE = "True";
	
	//attribute
	private boolean value = false;
	
	//constructor
	/**
	 * Creates new boolean with default attributes.
	 */
	public Boolean() {}
	
	//constructor
	/**
	 * Creates new boolean with the given value.
	 * 
	 * @param value
	 */
	public Boolean(boolean value) {
		this.value = value;
	}
	
	//method
	/**
	 * @return the attributes of this boolean
	 */
	public final List<StandardSpecification> getAttributes() {
		
		List<StandardSpecification> attributes = new List<StandardSpecification>();
		
		if (isTrue()) {
			attributes.addAtEnd(new StandardSpecification(TRUE));
		}
		else {
			attributes.addAtEnd(new StandardSpecification(FALSE));
		}

		return attributes;
	}
	
	//method
	/**
	 * @return a clone of this boolean
	 */
	public final Boolean getClone() {
		return new Boolean(getValue());
	}
	
	//method
	/**
	 * @return the value of this boolean
	 */
	public final boolean getValue() {
		return value;
	}
	
	//method
	/**
	 * @return true if this boolean is false
	 */
	public final boolean isFalse() {
		return (value == false);
	}
	
	//method
	/**
	 * @return true if this boolean is true
	 */
	public final boolean isTrue() {
		return (value == true);
	}
	
	//method
	/**
	 * Resets this boolean.
	 */
	public final void reset() {
		value = false;
	}
	
	//method
	/**
	 * Sets the given attribute to this boolean.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public final void addOrChangeAttribute(StandardSpecification attribute) {
		switch (attribute.toString()) {
			case FALSE:
				setFalse();
				break;
			case TRUE:
				setTrue();
				break;
			default:
				throw new InvalidArgumentException(
					new ArgumentName("attribute"),
					new Argument(attribute)
				);
		}
	}
	
	//method
	/**
	 * Sets this boolean as false.
	 */
	public final void setFalse() {
		value = false;
	}
	
	//method
	/**
	 * Sets this boolean as true.
	 */
	public final void setTrue() {
		value = true;
	}
}
