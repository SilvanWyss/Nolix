/*
 * file:	Launcher.java
 * author:	Silvan Wyss
 * month:	2016-07
 * lines:	30
 */

//package declaration
package ch.nolix.application.performanceDetector;

//own imports
import ch.nolix.system.application.DialogClient;
import ch.nolix.system.application.FrontDialogClient;
import ch.nolix.system.application.StandardApplication;

//class
/**
 * This class provides a main method to launch a performance detector.
 */
public final class Launcher {

	//main method
	/**
	 * Launches new performance detector application.
	 * 
	 * @param args
	 */
	public final static void main(String[] args) {
		
		//Creates new front end dialog and connects it to a newly created performance detector application.
		new FrontDialogClient(
			new StandardApplication<DialogClient>("Performance Detector", MainSession.class)
		);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created from outside.
	 */
	private Launcher() {}
}
