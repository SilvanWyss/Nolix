/*
 * file:	ObjectMediator.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	90
 */

//package declaration
package ch.nolix.core.test2;

//class
public final class ObjectMediator extends ElementMediator<Object> {

	//constructor
	/**
	 * Creates new object mediator with the given value.
	 * 
	 * @param value
	 */
	public ObjectMediator(final ZetaTest zetaTest, Object value) {
		
		//Calls constructor of the base class.
		super(zetaTest, value);
	}
}
