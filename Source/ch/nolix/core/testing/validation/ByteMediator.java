//package declaration
package ch.nolix.core.testing.validation;

//own imports
import ch.nolix.core.commontype.commontypewrapper.ByteWrapper;
import ch.nolix.core.testing.test.Mediator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;

//class
public final class ByteMediator extends Mediator {
	
	//attribute
	private final byte value;
	
	//constructor
	public ByteMediator(final IElementTaker<String> expectationErrorTaker, final byte value) {
		
		super(expectationErrorTaker);
		
		this.value = value;
	}
	
	//method
	public void consistsOfBits(final String expectedBits) {
		
		final var actualBits = new ByteWrapper(value).toBitString();
		
		if (!actualBits.equals(expectedBits)) {
			addCurrentTestCaseError(
				"The byte '" + expectedBits + "' was expected, but the byte '" + actualBits + "' was received."
			);
		}
	}
	
	//method
	public void isEqualTo(final byte expectedValue) {
		if (value != expectedValue) {
			addCurrentTestCaseError(
				new ByteWrapper(expectedValue).toBitString()
				+ " was expected, but "
				+ new ByteWrapper(value).toBitString()
				+ " was received."
			);
		}
	}
	
	//method
	public void isEqualTo(final int expectedValue) {
		if (value != expectedValue) {
			addCurrentTestCaseError(
				ByteWrapper.fromNumber(expectedValue).toBitString()
				+ " was expected, but "
				+ new ByteWrapper(value).toBitString()
				+ " was received."
			);
		}
	}
}
