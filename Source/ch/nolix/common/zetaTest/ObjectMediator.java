/*
 * file:	ObjectMediator.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	90
 */

//package declaration
package ch.nolix.common.zetaTest;

import ch.nolix.common.test.Test;

//class
public final class ObjectMediator {

	//attribute
	private final Test test;
	private final Object value;
	
	//constructor
	/**
	 * Creates new object mediator with the given value.
	 * 
	 * @param value
	 */
	public ObjectMediator(final Test test, Object value) {
		this.test = test;
		this.value = value;
	}
	
	//method
	/** 
	 * @param value
	 * @throws Error if the value of this object mediator is not equal to the given value
	 */
	public final boolean equals(Object value) {
		
		if (this.value != null && value == null) {
			test.addCurrentTestMethodError("'" + value + "' was expected, but null was received.");
		}
		
		if (this.value == null && value != null) {
			test.addCurrentTestMethodError("Null was expected, but '" + this.value + "' was received.");
		}
		
		if (!this.value.equals(value)) {
			test.addCurrentTestMethodError("'" + value + "' was expected, but '" + this.value + "' was received.");
		}
		
		return true;
	}
	
	//method
	/** 
	 * @param value
	 * @throws Error if the value of this object mediator is equal to the given value
	 */
	public final void isNotEqualTo(Object value) {
		
		if (this.value == null && value == null) {
			test.addCurrentTestMethodError("A value was expected, but null was received.");
		}
		
		if (this.value != null && value != null && this.value.equals(value)) {
			test.addCurrentTestMethodError("An other value than '" + value + "' was expected, but '" + this.value + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this object mediator is null
	 */
	public final void isNotNull() {
		if (value != null) {
			test.addCurrentTestMethodError("An object was expected, but null was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this object mediator is not null
	 */
	public final void isNull() {
		if (value != null) {
			test.addCurrentTestMethodError("Null was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Exception if the value of this object mediator is not the given value
	 */
	public final void isSameAs(Object value) {
		if (this.value != value) {
			test.addCurrentTestMethodError("The same object was expected, but an other object was received.");
		}
	}
}
