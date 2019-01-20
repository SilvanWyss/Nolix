//package declaration
package ch.nolix.core.validator2;

//class
public final class TerminalArgumentMediator<A> extends Mediator {
	
	//attribute
	private final A argument;
	
	//package-visible constructor
	TerminalArgumentMediator(final A argument) {
		this.argument = argument;
	}
	
	//package-visible constructor
	TerminalArgumentMediator(final String argumentName, final A argument) {
		
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	public A andReturn() {
		return argument;
	}
}
