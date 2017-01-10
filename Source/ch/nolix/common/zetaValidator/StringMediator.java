//package declaration
package ch.nolix.common.zetaValidator;

//own imports;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.BiggerArgumentException;
import ch.nolix.common.exception.EmptyArgumentException;
import ch.nolix.common.exception.NonEmptyArgumentException;
import ch.nolix.common.exception.NullArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 */
public final class StringMediator extends ElementMediator<String> {

	//package-visible constructor
	/**
	 * Creates new string mediator with the given argument.
	 * 
	 * @param argument
	 */
	StringMediator(final String argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//method
	/**
	 * @param maxLength
	 * @throws NullArgumentException if the argument of this string mediator is null
	 * @throws BiggerArgumentException if the argument of this string mediator has a bigger length than the given max length.
	 */
	public void hasMaxLength(final int maxLength) {
		
		//Checks if the argument of this string mediator is not null.
		isNotNull();
		
		//Checks if the argument of this string mediator has a bigger length than the given max length.
		if (getRefArgument().length() > maxLength) {
			throw new BiggerArgumentException("length", getRefArgument().length(), maxLength);
		}
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this string mediator is null.
	 * @throws NonEmptyArgumentException if the argument of this string mediator is not empty.
	 */
	public void isEmpty() {
		
		//Checks if the argument of this string mediator is not null.
		isNotNull();
		
		//Checks if the argument of this string mediator is empty.
		if (!getRefArgument().isEmpty()) {
			throw new NonEmptyArgumentException(new Argument(getRefArgument()));
		}
	}

	//method
	/**
	 * @throws NullArgumentException if the argument of this string mediator is null.
	 * @throws EmptyArgumentException if the argument of this string mediator is empty.
	 */
	public void isNotEmpty() {
		
		//Checks if the argument of this string mediator is not null.
		isNotNull();
		
		//Checks if the argument of this string mediator is not empty.
		if (getRefArgument().isEmpty()) {
			throw new EmptyArgumentException();
		}
	}
	
	//method
	/**
	 * @param argumentName
	 * @return a new named string mediator with the given argument name and the argument of this string mediator.
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is an empty string.
	 */
	public final NamedStringMediator thatIsNamed(final String argumentName) {
		return new NamedStringMediator(argumentName, getRefArgument());
	}
}
