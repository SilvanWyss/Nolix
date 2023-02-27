//package declaration
package ch.nolix.businesstest.mathtest.bigdecimalmathtest;

//own imports
import ch.nolix.business.math.bigdecimalmath.ComplexNumber;
import ch.nolix.business.math.bigdecimalmath.ComplexSequenceDefinedBy2Predecessor;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class ComplexSequenceDefinedBy2PredecessorTest extends Test {
	
	//method
	@TestCase
	public void test_getValueAt1BasedIndex() {
		
		//setup
		final var testUnit =
		new ComplexSequenceDefinedBy2Predecessor(
			new ComplexNumber(1.0, 0.0, 20),
			new ComplexNumber(0.0, 2.0, 20),
			(z1, z2) -> z1.getSum(z2)
		);
		
		//execution & verification
		expect(testUnit.getValueAt1BasedIndex(1)).isEqualTo(new ComplexNumber(1.0, 0.0, 20));
		expect(testUnit.getValueAt1BasedIndex(2)).isEqualTo(new ComplexNumber(0.0, 2.0, 20));
		expect(testUnit.getValueAt1BasedIndex(3)).isEqualTo(new ComplexNumber(1.0, 2.0, 20));
		expect(testUnit.getValueAt1BasedIndex(4)).isEqualTo(new ComplexNumber(1.0, 4.0, 20));
		expect(testUnit.getValueAt1BasedIndex(5)).isEqualTo(new ComplexNumber(2.0, 6.0, 20));
		expect(testUnit.getValueAt1BasedIndex(6)).isEqualTo(new ComplexNumber(3.0, 10.0, 20));
		expect(testUnit.getValueAt1BasedIndex(7)).isEqualTo(new ComplexNumber(5.0, 16.0, 20));
		expect(testUnit.getValueAt1BasedIndex(8)).isEqualTo(new ComplexNumber(8.0, 26.0, 20));
	}
}
