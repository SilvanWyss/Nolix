//package declaration
package ch.nolix.systemTest.neuronTest;

import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.test.Test;
import ch.nolix.system.neuronoid.SourceNeuron;

//test class
public final class SourceNeuronTest extends Test {
	
	//test case
	public void testCase_creation() {
		
		//setup
		final var output = "test output";
		
		//execution
		final var sourceNeuron = new SourceNeuron<String>(output);
		
		//verification
		expect(sourceNeuron.getRefOutput()).isEqualTo(output);
	}
	
	//test case
	public void testCase_creation_whenTheGivenOutputIsNull() {
		
		//execution & verification
		expect(() -> new SourceNeuron<String>(null))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given output is null.");
	}
	
	//test case
	public void testCase_getMaxInputNeuronCount() {
		
		//setup
		final var sourceNeuron = new SourceNeuron<String>("test output");
		
		//execution & verification
		expect(sourceNeuron.getMaxInputNeuronCount()).isZero();
	}
}
