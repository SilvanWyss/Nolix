/*
 * file:	ElementTest.java
 * author:	Silvan Wyss
 * month:	2016-01
 * lines:	20
 */

//package declaration
package ch.nolix.elementTest;

//class
public final class Launcher {
	
	//main method
	/**
	 * Creates new element test pool and lets execute its tests.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ElementTestPool().execute();
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be instantiated.
	 */
	private Launcher() {}
}
