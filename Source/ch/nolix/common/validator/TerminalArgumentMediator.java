//package declaration
package ch.nolix.common.validator;

//class
public final class TerminalArgumentMediator<A> extends Mediator {
	
	//attribute
	private final A argument;
	
	//constructor
	TerminalArgumentMediator(final A argument) {
		this.argument = argument;
	}
	
	//constructor
	TerminalArgumentMediator(final String argumentName, final A argument) {
		
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	public A andReturn() {
		return argument;
	}
}
