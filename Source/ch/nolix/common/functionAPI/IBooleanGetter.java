//package declaration
package ch.nolix.common.functionAPI;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;

//functional interface
/**
 * A {@link IBooleanGetter} has a method that returns a boolean.
 * 
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 30
 */
public interface IBooleanGetter {
	
	//static method
	/**
	 * 
	 * @param condition
	 * @return a new {@link IBooleanGetter} that will return always the negation of the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public static IBooleanGetter createNegator(final IBooleanGetter condition) {
		
		//Checks if the given condition is not null.
		Validator.suppose(condition).thatIsNamed(VariableNameCatalogue.CONDITION).isNotNull();
		
		return (() -> !condition.getOutput());
	}
	
	//method declaration
	/**
	 * @return a boolean.
	 */
	public abstract boolean getOutput();
}
