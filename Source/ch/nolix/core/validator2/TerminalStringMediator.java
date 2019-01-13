//package declaration
package ch.nolix.core.validator2;

//class
public final class TerminalStringMediator extends Mediator {
	
	//attribute
	private final String argument;
	
	//package-visible constructor
	TerminalStringMediator(final String argument) {
		this.argument = argument;
	}
	
	//package-visible constructor
	TerminalStringMediator(final String argumentName, final String argument) {
		
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	public String andReturn() {
		return argument;
	}
}
