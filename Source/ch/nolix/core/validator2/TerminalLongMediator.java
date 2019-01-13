//package declaration
package ch.nolix.core.validator2;

//class
public final class TerminalLongMediator extends Mediator {
	
	//attribute
	private final long argument;
	
	//package-visible constructor
	TerminalLongMediator(final long argument) {
		this.argument = argument;
	}
	
	//package-visible constructor
	TerminalLongMediator(final String argumentName, final long argument) {
		
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	public double andReturn() {
		return argument;
	}
}
