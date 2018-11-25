//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;

//class
/**
 * A boolean is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 120
 */
public final class Boolean extends Element<Boolean> {
	
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
	public static Boolean createFromSpecification(final DocumentNodeoid specification) {
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
	public final List<DocumentNode> getAttributes() {
		
		final List<DocumentNode> attributes = new List<DocumentNode>();
		
		//Handles the case that this boolean is false.
		if (isFalse()) {
			attributes.addAtEnd(new DocumentNode(FALSE_HEADER));
		}
		
		//Handles the case that this boolean ist true.
		else {
			attributes.addAtEnd(new DocumentNode(TRUE_HEADER));
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
