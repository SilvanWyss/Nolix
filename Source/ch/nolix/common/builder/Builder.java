//package declaration
package ch.nolix.common.builder;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public abstract class Builder<
	SAC extends ArgumentCapturer<?, ?>,
	O
> {
	
	//attribute
	private final SAC startArgumentCapturer;
	
	//constructor
	public Builder(final SAC startArgumentCapturer) {
		
		Validator.assertThat(startArgumentCapturer).thatIsNamed("start argument capturer").isNotNull();
		
		startArgumentCapturer.internalSetBuilder(this::build);
		
		this.startArgumentCapturer = startArgumentCapturer;
	}
	
	//method
	public final SAC getRefStart() {
		return startArgumentCapturer;
	}
	
	//method declaration
	protected abstract O build(SAC startArgumentCapturer);
	
	//method
	private O build() {
		return build(startArgumentCapturer);
	}
}
