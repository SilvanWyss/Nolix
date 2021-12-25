//package declaration
package ch.nolix.common.builder;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementGetter;

//class
public abstract class ArgumentCapturer<
	A,
	NAC extends BaseArgumentCapturer<?, O>,
	O
> extends BaseArgumentCapturer<A, O> {
	
	//attribute
	private final NAC nextArgumentCapturer;
	
	//constructor
	public ArgumentCapturer(final NAC nextArgumentCapturer) {
		
		Validator.assertThat(nextArgumentCapturer).thatIsNamed("next argument capturer").isNotNull();
		
		this.nextArgumentCapturer = nextArgumentCapturer;
	}
	
	//method
	public final NAC getRefNextArgumentCapturer() {
		return nextArgumentCapturer;
	}
	
	//method
	protected final NAC setArgumentAndGetRefNextArgumentCapturer(final A argument) {
		
		internalSetArgument(argument);
		
		return getRefNextArgumentCapturer();
	}
	
	//method
	@Override
	final void internalSetBuilder(final IElementGetter<O> builder) {
		nextArgumentCapturer.internalSetBuilder(builder);
	}
}
