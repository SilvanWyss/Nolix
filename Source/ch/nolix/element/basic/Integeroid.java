/*
 * file:	Integeroid.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	100
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.helper.StringHelper;
import ch.nolix.core.specification.StandardSpecification;

//package-visible class
public abstract class Integeroid extends Element {

	//attribute
	private int value;
	
	//constructor
	/**
	 * Creates new integeroid with the given value.
	 * @param value
	 */
	public Integeroid(int value) {
		setValue(value);
	}
	
	//method
	/**
	 * Decrements this integeroid.
	 * 
	 * @throws Exception if this integer cannot be decremented
	 */
	public final void decrement() {
		setValue(getValue() - 1);
	}
	
	//method
	/**
	 * Increments this integeroid.
	 * 
	 * @throws Exception if this integeroid cannot be incremented
	 */
	public final void increment() {
		setValue(getValue() + 1);
	}
	
	//method
	/**
	 * @return the attributes of this integeroid
	 */
	public final List<StandardSpecification> getAttributes() {
		return new List<StandardSpecification>().addAtEnd(new StandardSpecification(java.lang.Integer.toString(getValue())));
	}
	
	//method
	/**
	 * @return the value of this integer
	 */
	public final int getValue() {
		return value;
	}
	
	//method
	/**
	 * @param value
	 * @return true if this integeroid has the given value
	 */
	public final boolean hasValue(int value) {
		return (getValue() == value);
	}
	
	//method
	/**
	 * Sets the given attribute to this integeroid.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public final void addOrChangeAttribute(StandardSpecification attribute) {
		setValue(attribute.toString());
	}
	
	//method
	/**
	 * Sets the value of this integeroid.
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	//method
	/**
	 * Sets the value of this integeroid.
	 * 
	 * @param value
	 * @throws Exception if the given value is not valid
	 */
	public final void setValue(String value) {
		setValue(StringHelper.toInt(value));
	}
}
