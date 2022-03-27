//package declaration
package ch.nolix.core.builder.argumentcapturer;

//own imports
import ch.nolix.core.builder.base.ArgumentCapturer;
import ch.nolix.core.builder.base.BaseArgumentCapturer;

//class
public class WithDatabaseCapturer<
	D,
	NAC extends BaseArgumentCapturer<?>
> extends ArgumentCapturer<D, NAC> {
	
	//constructor
	public WithDatabaseCapturer(final NAC nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final D getRefDatabase() {
		return getRefArgument();
	}
	
	//method
	public final NAC withDatabase(final D database) {
		return setArgumentAndGetRefNextArgumentCapturer(database);
	}
}
