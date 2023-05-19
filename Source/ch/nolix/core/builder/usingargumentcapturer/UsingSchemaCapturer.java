//package declaration
package ch.nolix.core.builder.usingargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;

//class
public class UsingSchemaCapturer<
	S,
	N
>
extends ArgumentCapturer<S, N> {
	
	public UsingSchemaCapturer(final N nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final S getOriSchema() {
		return getOriArgument();
	}
	
	//method
	public final N usingSchema(final S schema) {
		return setArgumentAndGetNext(schema);
	}
}
