//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.exception.ArgumentName;
import ch.nolix.common.exception.EmptyArgumentException;
import ch.nolix.common.exception.NullArgumentException;

//package-visible abstract class
/**
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 40
 */
abstract class NamedArgumentMediator {
	
	//attribute
	private final String argumentName;
	
	//constructor
	/**
	 * Creates new named argument mediator with the given argument name.
	 * 
	 * @param argumentName
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is an empty string.
	 */
	public NamedArgumentMediator(final String argumentName) {
		
		if (argumentName == null) {
			throw new NullArgumentException("argument name");
		}
		
		if (argumentName.isEmpty()) {
			throw new EmptyArgumentException(new ArgumentName("argument name"));
		}
		
		this.argumentName = argumentName;
	}
	
	//method
	/**
	 * @return the argument name of htis named argument mediator.
	 */
	protected final String getArgumentName() {
		return argumentName;
	}
}
