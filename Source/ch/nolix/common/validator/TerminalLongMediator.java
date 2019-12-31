//package declaration
package ch.nolix.common.validator;

//class
public final class TerminalLongMediator extends Mediator {
	
	//attribute
	private final long argument;
	
	//constructor
	TerminalLongMediator(final long argument) {
		this.argument = argument;
	}
	
	//constructor
	TerminalLongMediator(final String argumentName, final long argument) {
		
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	public long andReturn() {
		return argument;
	}
}
