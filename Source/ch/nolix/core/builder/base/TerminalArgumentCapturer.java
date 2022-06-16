//package declaration
package ch.nolix.core.builder.base;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementGetter;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public abstract class TerminalArgumentCapturer<
	A,
	O
> extends BaseArgumentCapturer<A> {
	
	//optional attribute
	private IElementGetter<O> builder;
	
	//method
	protected final O setArgumentAndBuild(final A argument) {
		
		internalSetArgument(argument);
		
		return internalGetRefBuilder().getOutput();
	}
	
	//method
	final IElementGetter<O> internalGetRefBuilder() {
		
		assertHasBuilder();
		
		return builder;
	}
	
	//method
	@SuppressWarnings("unchecked")
	protected final void setBuilder(final IElementGetter<?> builder) {
		
		GlobalValidator.assertThat(builder).thatIsNamed(LowerCaseCatalogue.BUILDER).isNotNull();
		
		assertDoesNotHaveBuilder();
		
		this.builder = (IElementGetter<O>)builder;
	}
	
	//method
	private void assertDoesNotHaveBuilder() {
		if (hasBuilder()) {
			throw new ArgumentHasAttributeException(this, LowerCaseCatalogue.BUILDER);
		}
	}
	
	//method
	private void assertHasBuilder() {
		if (!hasBuilder()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.BUILDER);
		}
	}
	
	//method
	private boolean hasBuilder() {
		return (builder != null);
	}
}
