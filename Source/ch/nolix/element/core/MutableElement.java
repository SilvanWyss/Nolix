//package declaration
package ch.nolix.element.core;

import ch.nolix.core.controllerAPI.IController;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.documentNode.Statement;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.skillInterfaces.IFluentObject;
import ch.nolix.core.specificationAPI.Specifiable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 110
 * @param <MU> The type of a mutable element.
 */
public abstract class MutableElement<MU extends MutableElement<MU>>
extends Element
implements IController, IFluentObject<MU>, Specifiable<MU> {
	
	//command
	private static final String RESET = "Reset";
	
	//constant
	private static final String SET_ATTRIBUTE_COMMAND_PREFIX = "Set";
	
	//method
	/**
	 * Adds or changes the given attribute to this entity.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
	}

	//element
	/**
	 * Lets this mutable element run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	public void run(final Statement command) {
		
		//Extracts the header of the given command.
		final String header = command.getHeader();
		
		//Handles the case that the given command is a set attribute command.
		if (header.startsWith(SET_ATTRIBUTE_COMMAND_PREFIX)) {
		
			//Checks if the header of the given command has a length that is bigger than 4.
			Validator.suppose(header).thatIsNamed("command").hasMinLength(4);
			
			addOrChangeAttribute(
				new DocumentNode(header.substring(3), command.getRefAttributes())
			);
			
			return;
		}
		
		//Handles the case that the given command is a reset command.
		if (header.equals(RESET)) {
			reset(command.getRefAttributes());			
			return;
		}
		
		//Handles the case that the given command is not valid.
		throw new InvalidArgumentException(
			new ArgumentName("command"),
			new Argument(command)
		);
	}
}
