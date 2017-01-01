/*
 * file:	CloseCommand.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	52
 */

//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.common.controller.ILevel1Controller;
import ch.nolix.element.basic.Command;

//class
/**
 * A click command is a command.
 */
public final class CloseCommand extends Command {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "CloseCommand";

	//constructor
	/**
	 * Creates new click command with the given controller.
	 * 
	 * @param controller
	 * @throws Exception if the given controller is null
	 */
	public CloseCommand(ILevel1Controller controller) {
		
		//Calls constructor of the base class.
		super(controller);
	}
	
	//constructor
	/**
	 * Creates new close command with the given controller and value
	 * 
	 * @param controller
	 * @throws Exception if:
	 *  -the given controller is null
	 *  -the given value is not valid
	 */
	public CloseCommand(ILevel1Controller controller, String value) {
		
		//Calls constructor of the base class.
		super(controller);
		
		setValue(value);
	}
}
