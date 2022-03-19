//package declaration
package ch.nolix.core.functionapi;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;

//functional interface
/**
 * A {@link IBooleanGetter} has a method that returns a boolean.
 * 
 * @author Silvan Wyss
 * @date 2016-07-01
 */
@FunctionalInterface
public interface IBooleanGetter {
	
	//static method
	/**
	 * 
	 * @param condition
	 * @return a new {@link IBooleanGetter} that will return always the negation of the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	static IBooleanGetter createNegator(final IBooleanGetter condition) {
		
		//Asserts that the given condition is not null.
		Validator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();
		
		return (() -> !condition.getOutput());
	}
	
	//method declaration
	/**
	 * @return a boolean.
	 */
	boolean getOutput();
}
