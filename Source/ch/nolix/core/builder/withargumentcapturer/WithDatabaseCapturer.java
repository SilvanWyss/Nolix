//package declaration
package ch.nolix.core.builder.withargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;

//class
public class WithDatabaseCapturer<
	D,
	N
>
extends ArgumentCapturer<D, N> {
	
	//constructor
	public WithDatabaseCapturer(final N nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final D getOriDatabase() {
		return getOriArgument();
	}
	
	//method
	public final N withDatabase(final D database) {
		return setArgumentAndGetNext(database);
	}
}
