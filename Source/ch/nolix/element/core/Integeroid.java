//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;

//abstract class
/**
 * An integeroid is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 50
 */
public abstract class Integeroid extends Element {
	
	//attribute
	private final int value;
	
	//constructor
	/**
	 * Creates new integeroid with the given value.
	 * 
	 * @param value
	 */
	public Integeroid(final int value) {
		this.value = value;
	}
	
	//method
	/**
	 * @return the attributes of this integeroid.
	 */
	public final List<StandardSpecification> getAttributes() {
		return
		new List<StandardSpecification>(
			new StandardSpecification(java.lang.Integer.toString(getValue()))
		);
	}
	
	//method
	/**
	 * @return the value of this integeroid.
	 */
	public final int getValue() {
		return value;
	}
	
	//method
	/**
	 * @param value
	 * @return true if this integeroid has the given value.
	 */
	public final boolean hasValue(final int value) {
		return (getValue() == value);
	}
}
