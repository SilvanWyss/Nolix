//package declaration
package ch.nolix.core.builder.terminalargumentcapturer;

//own imports
import ch.nolix.core.builder.main.TerminalArgumentCapturer;

//class
public final class UsingSchemaTerminalCapturer<
	S,
	O
> extends TerminalArgumentCapturer<S, O> {
	
	//method
	public S getRefSchema() {
		return getRefArgument();
	}
	
	//method
	public O usingSchema(final S schema) {
		return setArgumentAndBuild(schema);
	}
}
