/*
 * file:	IterableHelper.java
 * author:	Silvan Wyss
 * month:	02.03.2016
 * lines:	30
 */

//package declaration
package ch.nolix.common.helper;

//class
/**
 * This class provides some methods to handle iterable objects.
 */
public final class IterableHelper {

	//static method
	/**
	 * @param iterable
	 * @return true if the given iterable object is empty
	 */
	public final static boolean isEmpty(Iterable<?> iterable) {
		return !iterable.iterator().hasNext();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private IterableHelper() {}
}
