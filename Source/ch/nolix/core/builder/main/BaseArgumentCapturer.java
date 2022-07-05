//package declaration
package ch.nolix.core.builder.main;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementGetter;

//class
public abstract class BaseArgumentCapturer<A> {
	
	//boolean
	private boolean hasArgument;
	
	//optional attribute
	private A argument;
	
	//method
	protected final A getRefArgument() {
		
		assertHasArgument();
		
		return argument;
	}
		
	//method declaration
	protected abstract void setBuilder(IElementGetter<?> builder);
	
	//method
	final void internalSetArgument(final A argument) {
		
		hasArgument = true;
		
		this.argument = argument;
	}
	
	//method
	private void assertHasArgument() {
		if (!hasArgument()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "argument");
		}
	}
	
	//method
	private boolean hasArgument() {
		return hasArgument;
	}
}
