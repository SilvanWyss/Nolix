//package declaration
package ch.nolix.core.validator2;

//class
public final class LongTerminalMediator extends Mediator {
	
	//attribute
	private final long argument;
	
	//constructor
	public LongTerminalMediator(final long argument) {
		this.argument = argument;
	}
	
	//constructor
	public LongTerminalMediator(final String argumentName, final long argument) {
		
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	public double andReturn() {
		return argument;
	}
}
