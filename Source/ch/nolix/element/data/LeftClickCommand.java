/*
 * file:	LeftClickCommand.java
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
 * A left click command is a command.
 */
public final class LeftClickCommand extends Command {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "LeftClickCommand";

	//constructor
	/**
	 * Creates new left click command with the given controller.
	 * 
	 * @param controller
	 * @throws Exception if the given controller is null
	 */
	public LeftClickCommand(ILevel1Controller controller) {
		
		//Calls constructor of the base class.
		super(controller);
	}
	
	//constructor
	/**
	 * Creates new left click command with the given controller and value
	 * 
	 * @param controller
	 * @throws Exception if:
	 *  -the given controller is null
	 *  -the given value is not valid
	 */
	public LeftClickCommand(ILevel1Controller controller, String value) {
		
		//Calls constructor of the base class.
		super(controller);
		
		setValue(value);
	}
}
