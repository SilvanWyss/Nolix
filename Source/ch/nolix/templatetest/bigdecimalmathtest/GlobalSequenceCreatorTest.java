//package declaration
package ch.nolix.templatetest.bigdecimalmathtest;

import ch.nolix.business.math.bigdecimalmath.ComplexNumber;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.template.bigdecimalmath.GlobalSequenceCreator;

//class
public final class GlobalSequenceCreatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_createMandelbrotSequenceForIncrement_whenIncrementIs0plus0i() {
		
		//execution
		final var result = GlobalSequenceCreator.createMandelbrotSequenceForIncrement(new ComplexNumber(0.0, 0.0));
		
		//verification
		expect(result.getValueAt1BasedIndex(1)).isEqualTo(new ComplexNumber(0.0, 0.0));
		expect(result.getValueAt1BasedIndex(2)).isEqualTo(new ComplexNumber(0.0, 0.0));
		expect(result.getValueAt1BasedIndex(3)).isEqualTo(new ComplexNumber(0.0, 0.0));
		expect(result.getValueAt1BasedIndex(4)).isEqualTo(new ComplexNumber(0.0, 0.0));
		expect(result.getValueAt1BasedIndex(5)).isEqualTo(new ComplexNumber(0.0, 0.0));
	}
	
	//method
	@TestCase
	public void testCase_createMandelbrotSequenceForIncrement_whenIncrementIs1plus0i() {
		
		//execution
		final var result = GlobalSequenceCreator.createMandelbrotSequenceForIncrement(new ComplexNumber(1.0, 0.0));
		
		//verification
		expect(result.getValueAt1BasedIndex(1)).isEqualTo(new ComplexNumber(0.0, 0.0));
		expect(result.getValueAt1BasedIndex(2)).isEqualTo(new ComplexNumber(1.0, 0.0));
		expect(result.getValueAt1BasedIndex(3)).isEqualTo(new ComplexNumber(2.0, 0.0));
		expect(result.getValueAt1BasedIndex(4)).isEqualTo(new ComplexNumber(5.0, 0.0));
		expect(result.getValueAt1BasedIndex(5)).isEqualTo(new ComplexNumber(26.0, 0.0));
	}
	
	//method
	@TestCase
	public void testCase_createMandelbrotSequenceForIncrement_whenIncrementIs1plus1i() {
		
		//execution
		final var result = GlobalSequenceCreator.createMandelbrotSequenceForIncrement(new ComplexNumber(1.0, 1.0));
		
		//verification
		expect(result.getValueAt1BasedIndex(1)).isEqualTo(new ComplexNumber(0.0, 0.0));
		expect(result.getValueAt1BasedIndex(2)).isEqualTo(new ComplexNumber(1.0, 1.0));
		expect(result.getValueAt1BasedIndex(3)).isEqualTo(new ComplexNumber(1.0, 3.0));
		expect(result.getValueAt1BasedIndex(4)).isEqualTo(new ComplexNumber(-7.0, 7.0));
		expect(result.getValueAt1BasedIndex(5)).isEqualTo(new ComplexNumber(1.0, -97.0));
	}
}
