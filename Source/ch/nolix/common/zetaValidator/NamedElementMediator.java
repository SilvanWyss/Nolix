package ch.nolix.common.zetaValidator;

import ch.nolix.common.exception.NullArgumentException;

public class NamedElementMediator<E> extends NamedArgumentMediator {

	private final E argument;
	
	public NamedElementMediator(String argumentName, E argument) {
		
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this named object mediator is null
	 */
	public void isNotNull() {
		if (argument == null) {
			throw new NullArgumentException(getArgumentName());
		}
	}
}
