/*
 * file:	Module.java
 * author:	Silvan Wyss
 * month:	2016-04
 * lines:	140
 */

//package declaration
package ch.nolix.common.module;

//own imports
import java.io.File;

import ch.nolix.common.controller.ILevel3Controller;
import ch.nolix.common.interfaces.CodeNamed;
import ch.nolix.common.util.Validator;

//class
/**
 * A module is a level 3 controller that has:
 *  -a code name
 *  -a directory.
 *  -a central controller
 */
public abstract class Module implements CodeNamed, ILevel3Controller {
	
	//attributes
	private CentralController centralController;
	
	public Module(CentralController centralController) {
		
		Validator.throwExceptionIfValueIsNull("central controller", centralController);
		
		this.centralController = centralController;
		centralController.addModule(this);
	}
	
	//default method
	/**
	 * @return the code name of this module
	 */
	public final String getCodeName() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return the directory of this module
	 * @throws Exception if this module has no directory
	 */
	public final String getDirectory() {
		
		final String directory = getRefCentralController().getDirectory() + "/" + getCodeName();
		
		File file = new File(directory);
		if (file.exists()) {
			if (!file.isDirectory()) {
				throw new RuntimeException("The directory '" + directory + "' is a file.");
			}
		}
		else {
			file.mkdir();
		}
		return directory;
	}
	
	//method
	/**
	 * @return the central controller of this module
	 * @throws Excepion if this module has no central controller
	 */
	public final CentralController getRefCentralController() {		
		return centralController;
	}
	
	//method
	/**
	 * @return true if this module has a central controller
	 */
	public final boolean hasCentralController() {
		return (centralController != null);
	}
	
	//package-visible method
	/**
	 * Sets the central controller of this module.
	 * 
	 * @param centralController
	 * @throws Exception if the given central controller is null
	 */
	final void setCentralController(CentralController centralController) {
		
		Validator.throwExceptionIfValueIsNull("central controller", centralController);
			
		this.centralController = centralController;
	}
}
