//package declaration
package ch.nolix.core.builder.base;

//class
public abstract class Builder<
	SAC extends ArgumentCapturer<?, ?>,
	O
> {
	
	//attribute
	private final SAC startArgumentCapturer;
	
	//constructor
	public Builder() {
		
		startArgumentCapturer = createStartArgumentCapturer();
		
		startArgumentCapturer.internalSetBuilder(this::build);
	}
	
	//method
	public final SAC getRefStart() {
		return startArgumentCapturer;
	}
	
	//method declaration
	protected abstract O build(SAC startArgumentCapturer);
	
	//method declaration
	protected abstract SAC createStartArgumentCapturer();
	
	//method
	private O build() {
		return build(startArgumentCapturer);
	}
}
