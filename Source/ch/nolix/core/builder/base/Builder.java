//package declaration
package ch.nolix.core.builder.base;

//class
public abstract class Builder<
	SAC extends ArgumentCapturer<?, ?>,
	O
> {
	
	//method
	public final SAC getRefStart() {
		
		final var startArgumentCapturer = createStartArgumentCapturer();
		
		startArgumentCapturer.setBuilder(() -> build(startArgumentCapturer));
		
		return startArgumentCapturer;
	}
	
	//method declaration
	protected abstract O build(SAC startArgumentCapturer);
	
	//method declaration
	protected abstract SAC createStartArgumentCapturer();
}
