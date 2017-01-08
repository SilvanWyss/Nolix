/*
 * file:	Launcher.java
 * author:	Silvan Wyss
 * month:	2016-01
 * lines.	20
 */

//package declaration
package ch.nolix.commonTest;

//class
public final class Launcher {
	
	//main method
	/**
	 * Creates new common test pool and lets execute it its tests.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		new CommonTestPool().execute();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
