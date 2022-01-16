//package declaration
package ch.nolix.common.builder;

//own imports
import ch.nolix.common.functionapi.IElementGetter;

//class
public abstract class BaseArgumentCapturer<A> {
	
	//attribute
	private A argument;
	
	//method
	public final A getRefArgument() {
		return argument;
	}
	
	//method
	final void internalSetArgument(final A argument) {
		this.argument = argument;
	}
	
	//method declaration
	abstract void internalSetBuilder(IElementGetter<?> builder);
}
