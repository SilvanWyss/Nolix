/*
 * file:	Launcher.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	20
 */

//package declaration
package ch.nolix.commonTest.containerTest;

//package-visible class
final class Launcher {

	//main method
	/**
	 * Creates new container test pool and lets it execute its tests.
	 * 
	 * @param args
	 */
	public final static void main(String[] args) {
		new ContainerTestPool().execute();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
