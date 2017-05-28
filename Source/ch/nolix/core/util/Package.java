/*
 * file:	Package.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	60
 */

//package declaration
package ch.nolix.core.util;

//class
/**
 * A package bundles a message with an index.
 */
public class Package<M> {

	//attributes
	private final int index;
	private final M message;
	
	//constructor
	/**
	 * Creates new package with the given index and the given message.
	 * 
	 * @param index
	 * @param message
	 * @throws Exception if the given message is null
	 */
	public Package(int index, M message) {
		this.index = index;
		this.message = message;
	}
	
	//method
	/**
	 * @return the index of this package
	 */
	public final int getIndex() {
		return index;
	}
	
	//method
	/**
	 * @return the message of this package
	 */
	public final M getMessage() {
		return message;
	}
	
	//method
	/**
	 * @param index
	 * @return true if this package has the given index
	 */
	public final boolean hasIndex(int index) {
		return (getIndex() == index);
	}
}
