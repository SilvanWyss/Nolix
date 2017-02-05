/*
 * file:	TimerLauncher.java
 * author:	Silvan Wyss
 * month:	2016-06
 * lines:	30
 */

//package declaration
package ch.nolix.application.timer;

//own imports
import ch.nolix.common.application.StandardApplication;
import ch.nolix.system.dialogClient.DialogClient;
import ch.nolix.system.dialogClient.FrontDialogClient;

//class
/**
 * This class provides a main method to launch a timer application.
 */
public final class Launcher {

	//main method
	/**
	 * Launches new timer application.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		
		//Creates new front end dialog and connects it to a newly created timer application.
		new FrontDialogClient(new StandardApplication<DialogClient>("Timer", MainSession.class));
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created from outside.
	 */
	private Launcher() {}
}
