//package declaration
package ch.nolix.common.controllerAPI;

//own import
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.constants.MultiVariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 40
 */
public interface IController {
	
	//method declaration
	/**
	 * Lets the current {@link IController} run the given command.
	 * 
	 * @param command
	 */
	public abstract void run(ChainedNode command);
	
	//method
	/**
	 * Lets the current {@link IController} run the given commands.
	 * 
	 * @param commands
	 * @throws ArgumentIsNullException if the given commands is null.
	 */
	public default void run(final ChainedNode... commands) {
		
		//Checks if the given commands is not null.
		Validator.suppose(commands).thatIsNamed(MultiVariableNameCatalogue.COMMANDS).isNotNull();
		
		//Iterates the given commands.
		for (final var c : commands) {
			run(c);
		}
	}
}
