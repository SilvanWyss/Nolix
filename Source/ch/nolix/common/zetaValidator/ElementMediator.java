/*
 * file:	ElementMediator.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	30
 */

//package declaration
package ch.nolix.common.zetaValidator;

import ch.nolix.common.exception.NullArgumentException;

//class
public abstract class ElementMediator<E> {

	//attribute
	private final E argument;
	
	//package-visible constructor
	/**
	 * Creates new element mediator with the given argument.
	 * 
	 * @param argument		The argument of this element mediator.
	 */
	ElementMediator(final E argument) {
		this.argument = argument;
	}
	
	//method
	/**
	 * @return the argument of this element mediator
	 */
	protected final E getArgument() {
		return argument;
	}
	
	public final void isNotNull() {
		if (argument == null) {
			throw new NullArgumentException();
		}
	}
}
