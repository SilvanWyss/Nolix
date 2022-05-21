//package declaration
package ch.nolix.core.testing.validation;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.functionapi.IElementTaker;

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
		
		switch (bit) {
			case 0:
				this.bit = false;
				break;
			case 1:
				this.bit = true;
				break;
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.BIT, bit, "is not valid");
		}
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
