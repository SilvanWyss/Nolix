//package declaration
package ch.nolix.core.zetaValidator;

//own imports
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.core.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.invalidArgumentException.SmallerArgumentException;
import ch.nolix.core.invalidArgumentException.ZeroArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 100
 */
public final class NamedDoubleMediator extends NamedArgumentMediator {
	
	//attribute
	private final double argument;

	//package-visible constructor
	/**
	 * Creates new named double mediator with the given argument name and argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is an empty string.
	 */
	NamedDoubleMediator(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	/**
	 * @param value
	 * @throws NonBiggerArgumentException if the argument of this named double mediator is not bigger than the given value.
	 */
	public void isBiggerThan(final double value) {
		
		//Checks if the argument of this named double mediator is bigger than the given value.
		if (argument <= value) {
			throw new NonBiggerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException if the argumetn of this named double mediator is not smaller than or equals the given value.
	 */
	public void isBiggerThanOrEquals(final double value) {
		
		//Checks if the argument of this named double mediator is bigger than or equals the given value.
		if (argument < value) {
			throw new SmallerArgumentException(value, value);
		}
	}

	//method
	/**
	 * @throws NegativeArgumentException if the argument of this named double mediator is negative.
	 */
	public void isNotNegative() {
		
		//Checks if the argument of this named double mediator is not negative.
		if (argument < 0) {
			throw new NegativeArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws ZeroArgumentException if the argument of this named double mediator is 0.
	 */
	public void isNotZero() {
		
		//Checks if the argument of this named double mediator is not zero.
		if (argument == 0) {
			throw new ZeroArgumentException(getArgumentName());
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if the argument of this named double mediator is not positive.
	 */
	public void isPositive() {
		
		//Checks if the argument of this named double mediator is positive.
		if (argument <= 0) {
			throw new NonPositiveArgumentException(getArgumentName(), argument);
		}
	}
}
