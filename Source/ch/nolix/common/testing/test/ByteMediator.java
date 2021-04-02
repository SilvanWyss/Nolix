//package declaration
package ch.nolix.common.testing.test;

//own import
import ch.nolix.common.commontype.commontypewrapper.ByteWrapper;

//class
public final class ByteMediator extends Mediator {
	
	//attribute
	private final byte value;
	
	//constructor
	ByteMediator(final Test parentTest, final byte value) {
		
		super(parentTest);
		
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
