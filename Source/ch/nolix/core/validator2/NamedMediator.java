//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//package-visible abstract class
/**
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 40
 */
abstract class NamedMediator {
	
	//attribute
	private final String argumentName;
	
	//constructor
	/**
	 * Creates new named argument mediator with the given argument name.
	 * 
	 * @param argumentName
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public NamedMediator(final String argumentName) {
		
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
