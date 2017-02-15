//package declaration
package ch.nolix.element.basic;

import ch.nolix.common.controller.ILevel2Controller;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.ArgumentName;
import ch.nolix.common.exception.InvalidArgumentException;
//own import
import ch.nolix.common.specification.Specifiable;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;

//class
public abstract class MutableElement
extends Element
implements ILevel2Controller, Specifiable {
	
	//command
	private static final String RESET = "Reset";
	
	//command prefix
	private static final String SET_COMMAND_PREFIX = "Set";

	//element
	/**
	 * Runs the given command.
	 * 
	 * @param command
	 * @throws Exception if the given command is not valid
	 */
	public void run(Statement command) {
		
		//Extracts the header of the given command.
		String header = command.getHeader();
		
		//Handles the case when the given command is a set attribute command.
		if (header.startsWith(SET_COMMAND_PREFIX)) {
			
			if (header.length() < 4) {
				throw new InvalidArgumentException(
					new ArgumentName("command"),
					new Argument(command)
				);
			}
			
			addOrChangeAttribute(new Specification(header.substring(3), command.getRefAttributes()));
			return;
		}
		
		//Handels the case when the given command is a reset command.
		if (header.equals(RESET)) {
			
			if (!command.containsAttributes()) {
				reset();
			}
			else {
				reset();
				addOrChangeAttributes(command.getRefAttributes());
			}
			
			return;
		}
		
		//Handles the case when the given command is not valid.
		throw new InvalidArgumentException(
			new ArgumentName("command"),
			new Argument(command)
		);
	}
}
