/*
 * file:	Launcher.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	20
 */

//package declaration
package ch.nolix.elementTest.dialogTest;

//package-visible class
final class Launcher {

	//main method
	/**
	 * Creates new dialog test pool and lets it execute its tests.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		new DialogTestPool().execute();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
