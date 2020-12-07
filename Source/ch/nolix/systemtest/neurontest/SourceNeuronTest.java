//package declaration
package ch.nolix.systemtest.neurontest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.test.Test;
import ch.nolix.system.baseneuron.SourceNeuron;

//class
public final class SourceNeuronTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//setup
		final var output = "test output";
		
		//execution
		final var sourceNeuron = new SourceNeuron<String>(output);
		
		//verification
		expect(sourceNeuron.getRefOutput()).isEqualTo(output);
	}
	
	//method
	@TestCase
	public void testCase_creation_whenTheGivenOutputIsNull() {
		
		//execution & verification
		expect(() -> new SourceNeuron<String>(null))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given output is null.");
	}
	
	//method
	@TestCase
	public void testCase_getMaxInputNeuronCount() {
		
		//setup
		final var sourceNeuron = new SourceNeuron<String>("test output");
		
		//execution & verification
		expect(sourceNeuron.getMaxInputNeuronCount()).isEqualTo(0);
	}
}
