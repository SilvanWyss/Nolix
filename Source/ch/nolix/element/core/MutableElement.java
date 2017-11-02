//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.controllerInterfaces.IController;
import ch.nolix.core.entity.Entity;
import ch.nolix.core.interfaces.IFluentObject;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.specificationInterfaces.Specifiable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A mutable element is an element that can be mutated.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 80
 * @param <MU> The type of a mutable element.
 */
public abstract class MutableElement<MU extends MutableElement<MU>>
extends Entity
implements IController, IElement, IFluentObject<MU>, Specifiable {
	
	//command
	private static final String RESET = "Reset";
	
	//constant
	private static final String SET_ATTRIBUTE_COMMAND_PREFIX = "Set";

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
		
		//Handles the case if the given command is a set attribute command.
		if (header.startsWith(SET_ATTRIBUTE_COMMAND_PREFIX)) {
		
			//Checks if the header of the given command has a length that is bigger than 4.
			Validator.suppose(header).thatIsNamed("command").hasMinLength(4);
			
			addOrChangeAttribute(
				new StandardSpecification(header.substring(3), command.getRefAttributes())
			);
			
			return;
		}
		
		//Handles the case if the given command is a reset command.
		if (header.equals(RESET)) {
			reset(command.getRefAttributes());			
			return;
		}
		
		//Handles the case if the given command is not valid.
		throw new InvalidArgumentException(
			new ArgumentName("command"),
			new Argument(command)
		);
	}
	
	//method
	/**
	 * @return a string representation of this mutable element.
	 */
	public final String toString() {
		return getSpecification().toReproducingString();
	}
	
	//default method
	/**
	 * @return a formated string representation of this mutable element.
	 */
	public final String toFormatedString() {
		return getSpecification().toFormatedReproducingString();
	}
}
