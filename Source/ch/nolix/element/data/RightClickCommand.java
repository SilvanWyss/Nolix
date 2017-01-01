/*
 * file:	RightClickCommand.java
 * author:	Silvan Wyss
 * month:	2016-06
 * lines:	52
 */

//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.common.controller.ILevel1Controller;
import ch.nolix.element.basic.Command;

//class
/**
 * A right click command is a command.
 */
public final class RightClickCommand extends Command {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "RightClickCommand";

	//constructor
	/**
	 * Creates new right click command with the given controller.
	 * 
	 * @param controller
	 * @throws Exception if the given controller is null
	 */
	public RightClickCommand(ILevel1Controller controller) {
		
		//Calls constructor of the base class.
		super(controller);
	}
	
	//constructor
	/**
	 * Creates new right click command with the given controller and value
	 * 
	 * @param controller
	 * @throws Exception if:
	 *  -the given controller is null
	 *  -the given value is not valid
	 */
	public RightClickCommand(ILevel1Controller controller, String value) {
		
		//Calls constructor of the base class.
		super(controller);
		
		setValue(value);
	}
}

