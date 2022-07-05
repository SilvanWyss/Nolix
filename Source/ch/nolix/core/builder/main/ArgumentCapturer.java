//package declaration
package ch.nolix.core.builder.main;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementGetter;

//class
public abstract class ArgumentCapturer<
	A,
	NAC extends BaseArgumentCapturer<?>
> extends BaseArgumentCapturer<A> {
	
	//attribute
	private final NAC nextArgumentCapturer;
	
	//constructor
	protected ArgumentCapturer(final NAC nextArgumentCapturer) {
		
		GlobalValidator.assertThat(nextArgumentCapturer).thatIsNamed("next argument capturer").isNotNull();
		
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
