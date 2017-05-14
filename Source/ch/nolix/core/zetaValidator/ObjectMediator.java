package ch.nolix.core.zetaValidator;

import ch.nolix.core.invalidArgumentException.NullArgumentException;

public class ObjectMediator extends ElementMediator<Object> {

	ObjectMediator(Object argument) {
		super(argument);
	}
	
	//method
	/**
	 * @param class_
	 * @return a new named element mediator with the argument name of the given class and the argument of this element mediator.
	 * @throws NullArgumentException if the given class is null.
	 */
	public final NamedObjectMediator thatIsInstanceOf(final Class<?> class_) {
		
		//Checks if the given class is null.
		if (class_ == null) {
			throw new NullArgumentException("class");
		}
		
		return new NamedObjectMediator(class_.getSimpleName(), getRefArgument());
	}

	public NamedObjectMediator thatIsNamed(final String argumentName) {
		return new NamedObjectMediator(argumentName, getRefArgument());
	}
}
