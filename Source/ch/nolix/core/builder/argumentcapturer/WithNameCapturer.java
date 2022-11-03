//package declaration
package ch.nolix.core.builder.argumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;

//class
public class WithNameCapturer<N> extends ArgumentCapturer<String, N> {
	
	//constructor
	public WithNameCapturer(final N nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final String getName() {
		return getRefArgument();
	}
	
	//method
	public final N withName(final String name) {
		return setArgumentAndGetNext(name);
	}
}
