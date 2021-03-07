//package declaration
package ch.nolix.systemtest.dynamicmathtest;

//Java import
import java.math.BigDecimal;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.system.dynamicmath.ClosedInterval;

//class
public final class ClosedIntervalTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation_whenTheGivenMinIsNull() {
		expectRunning(() -> new ClosedInterval(null, BigDecimal.valueOf(1.0)))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given minimum is null.");
	}
	
	//method
	@TestCase
	public void testCase_creation_whenTheGivenMaxIsNull() {
		expectRunning(() -> new ClosedInterval(BigDecimal.valueOf(1.0), null))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given maximum is null.");
	}
	
	//method
	@TestCase
	public void testCase_getHalfs_1A() {
		
		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(-1.0, 1.0, scale);
		
		//execution
		final var result = testUnit.getHalfs();
		
		//verification
		expect(result.getRefElement1().getMin()).isEqualTo(BigDecimal.valueOf(-1.0).setScale(scale));
		expect(result.getRefElement1().getMax()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
		expect(result.getRefElement2().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
		expect(result.getRefElement2().getMax()).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getHalfs_1B() {
		
		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(0.0, 1.0, scale);
		
		//execution
		final var result = testUnit.getHalfs();
		
		//verification
		expect(result.getRefElement1().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
		expect(result.getRefElement1().getMax()).isEqualTo(BigDecimal.valueOf(0.5).setScale(scale));
		expect(result.getRefElement2().getMin()).isEqualTo(BigDecimal.valueOf(0.5).setScale(scale));
		expect(result.getRefElement2().getMax()).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getHalfs_whenHasLength0() {
		
		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(0.0, 0.0, scale);
		
		//execution
		final var result = testUnit.getHalfs();
		
		//verification
		expect(result.getRefElement1().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
		expect(result.getRefElement1().getMax()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
		expect(result.getRefElement2().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
		expect(result.getRefElement2().getMax()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getLength_1A() {
		
		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(-1.0, -1.0, scale);
		
		//execution
		final var result = testUnit.getLength();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getLength_1B() {

		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(-1.0, 0.0, scale);
		
		//execution
		final var result = testUnit.getLength();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getLength_1C() {

		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(-1.0, 1.0, scale);
		
		//execution
		final var result = testUnit.getLength();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(2.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getLength_whenHasLength0() {
		
		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(0.0, 0.0, scale);
		
		//execution
		final var result = testUnit.getLength();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getMidpoint_1A() {
		
		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(-1.0, 1.0, scale);
		
		//execution
		final var result = testUnit.getMidPoint();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getMidpoint_1B() {
		
		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(0.0, 1.0, scale);
		
		//execution
		final var result = testUnit.getMidPoint();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(0.5).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_getMidpoint_whenHasLength0() {
		
		//parameter definition
		final var scale = 20;
		
		//setup
		final var testUnit = new ClosedInterval(0.0, 0.0, scale);
		
		//execution
		final var result = testUnit.getMidPoint();
		
		//verification
		expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
	}
	
	//method
	@TestCase
	public void testCase_toString() {
		
		//setup
		final var testUnit = new ClosedInterval(-1.0, 1.0, 5);
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("[-1.00000, 1.00000]");
	}
}
