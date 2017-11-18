//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;

//class
/**
 * A boolean is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 100
 */
public final class Boolean extends Element {
	
	//type name
	public static final String TYPE_NAME = "Boolean";
	
	//default value
	public static final boolean DEFAULT_VALUE = false;
	
	//constants
	private static final String FALSE = "False";
	private static final String TRUE = "True";
	
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
	public final List<StandardSpecification> getAttributes() {
		
		final List<StandardSpecification> attributes = new List<StandardSpecification>();
		
		//Handles the case that this boolean is false.
		if (isFalse()) {
			attributes.addAtEnd(new StandardSpecification(FALSE));
		}
		
		//Handles the case that this boolean ist true.
		else {
			attributes.addAtEnd(new StandardSpecification(TRUE));
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
		return (value == false);
	}
	
	//method
	/**
	 * @return true if this boolean is true.
	 */
	public final boolean isTrue() {
		return (value == true);
	}
}
