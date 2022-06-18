//package declaration
package ch.nolix.core.builder.terminalargumentcapturer;

import ch.nolix.core.builder.main.TerminalArgumentCapturer;

//class
public final class AndSchemaTerminalCapturer<
	S,
	O
> extends TerminalArgumentCapturer<S, O> {
	
	//method
	public O andSchema(final S schema) {
		return setArgumentAndBuild(schema);
	}
	
	//method
	public S getRefSchema() {
		return getRefArgument();
	}
}
