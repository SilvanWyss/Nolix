//package declaration
package ch.nolix.systemTest.neuronTest;

//own imports
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.test.Test;
import ch.nolix.system.neuronoid.SourceNeuron;

//test class
public final class SourceNeuronTest extends Test {
	
	//test case
	public void testCase_constructor() {
		
		//setup
		final var output = "test output";
		
		//execution
		final var sourceNeuron = new SourceNeuron<String>(output);
		
		//verification
		expect(sourceNeuron.getRefOutput()).isEqualTo(output);
	}
	
	//test case
	public void testCase_constructor_whenTheGivenOutputIsNull() {
		
		//execution & verification
		expect(() -> new SourceNeuron<String>(null))
		.throwsException()
		.ofType(NullArgumentException.class)
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
