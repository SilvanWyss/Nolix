//package declaration
package ch.nolix.common.test;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//class
public final class BitMediator extends Mediator {
	
	//attribute
	private final boolean bit;
	
	//constructor
	BitMediator(final Test parentTest, final boolean bit) {
		
		super(parentTest);
		
		this.bit = bit;
	}
	
	//constructor
	public BitMediator(final Test parentTest, final int bit) {
		
		super(parentTest);
		
		switch (bit) {
			case 0:
				this.bit = false;
				break;
			case 1:
				this.bit = true;
				break;
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.BIT, bit, "is not valid");
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
