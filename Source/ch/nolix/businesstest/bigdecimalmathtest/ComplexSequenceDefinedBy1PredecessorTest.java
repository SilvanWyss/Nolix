//package declaration
package ch.nolix.businesstest.bigdecimalmathtest;

import ch.nolix.business.math.bigdecimalmath.ComplexNumber;
import ch.nolix.business.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class ComplexSequenceDefinedBy1PredecessorTest extends Test {
	
	//method
	@TestCase
	public void test_getValueAtIndex() {
		
		//setup
		final var testUnit =
		new ComplexSequenceDefinedBy1Predecessor(
			new ComplexNumber(0.0, 0.0, 20),
			p -> p.getPower2().getSum(new ComplexNumber(1.0, 0.0))
		);
		
		//execution & verification
		expect(testUnit.getValueAtIndex(1)).isEqualTo(new ComplexNumber(0.0, 0.0, 20));
		expect(testUnit.getValueAtIndex(2)).isEqualTo(new ComplexNumber(1.0, 0.0, 20));
		expect(testUnit.getValueAtIndex(3)).isEqualTo(new ComplexNumber(2.0, 0.0, 20));
		expect(testUnit.getValueAtIndex(4)).isEqualTo(new ComplexNumber(5.0, 0.0, 20));
	}
}
