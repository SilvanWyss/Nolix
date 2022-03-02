//package declaration
package ch.nolix.core.builder.base;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementGetter;

//class
public abstract class ArgumentCapturer<
	A,
	NAC extends BaseArgumentCapturer<?>
> extends BaseArgumentCapturer<A> {
	
	//attribute
	private final NAC nextArgumentCapturer;
	
	//constructor
	public ArgumentCapturer(final NAC nextArgumentCapturer) {
		
		Validator.assertThat(nextArgumentCapturer).thatIsNamed("next argument capturer").isNotNull();
		
		this.nextArgumentCapturer = nextArgumentCapturer;
	}
	
	//method
	public final NAC n() {
		return nextArgumentCapturer;
	}
	
	//method
	protected final NAC setArgumentAndGetRefNextArgumentCapturer(final A argument) {
		
		internalSetArgument(argument);
		
		return n();
	}
	
	//method
	@Override
	protected final void setBuilder(final IElementGetter<?> builder) {
		nextArgumentCapturer.setBuilder(builder);
	}
}
