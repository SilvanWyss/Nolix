package ch.nolix.common.zetaValidator;

public class ObjectMediator extends ElementMediator<Object> {

	ObjectMediator(Object argument) {
		super(argument);
	}

	//method
	/**
	 * @param argumentName		The name of the argument of this element mediator.
	 * @return new named element mediator with the given argument name and the argument of this element mediator
	 */
	public NamedElementMediator<Object> thatIsNamed(final String argumentName) {
		return new NamedElementMediator<Object>(argumentName, getArgument());
	}
}
