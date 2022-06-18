//package declaration
package ch.nolix.core.builder.argumentcapturer;

import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.builder.main.BaseArgumentCapturer;

//class
public class WithNameCapturer<NAC extends BaseArgumentCapturer<?>> extends ArgumentCapturer<String, NAC> {
	
	//constructor
	public WithNameCapturer(final NAC nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final String getName() {
		return getRefArgument();
	}
	
	//method
	public final NAC withName(final String name) {
		return setArgumentAndGetRefNextArgumentCapturer(name);
	}
}
