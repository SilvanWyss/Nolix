//package declaration
package ch.nolix.core.testing.validation;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;

//class
public final class BitMediator extends Mediator {
	
	//attribute
	private final boolean bit;
	
	//constructor
	public BitMediator(final IElementTaker<String> expectationErrorTaker, final boolean bit) {
		
		super(expectationErrorTaker);
		
		this.bit = bit;
	}
	
	//constructor
	public BitMediator(final IElementTaker<String> expectationErrorTaker, final int bit) {
		
		super(expectationErrorTaker);
		
		this.bit =
		switch (bit) {
			case 0 ->
				false;
			case 1 ->
				true;
			default ->
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.BIT, bit);
		};
	}
	
	//method
	public void isCleared() {
		if (bit) {
			addCurrentTestCaseError("A cleared bit was expected, but a set bit was received.");
		}
	}
	
	//method
	public void isSet() {
		if (!bit) {
			addCurrentTestCaseError("A set bit was expected, but a cleared bit was received.");
		}
	}
}
