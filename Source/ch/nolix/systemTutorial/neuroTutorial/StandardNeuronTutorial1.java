//package declaration
package ch.nolix.systemTutorial.neuroTutorial;

//own imports
import ch.nolix.common.mathematics.Calculator;
import ch.nolix.system.neuro.SourceNeuron;
import ch.nolix.system.neuro.StandardNeuron;

//package-visible class
/**
 * This class provides a tutorial for the standard neuron class.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 50
 */
final class StandardNeuronTutorial1 {

	//main method
	/**
	 * 1. Creates 3 source neurons and 1 standard neurons. The output type of the neurons is a floating point number.
	 * 2. Adds the source neurons as input neurons to the standard neuron.
	 * 3. Sets the output function of the standard neuron that it will calculate its output as the average of the outputs of its input neurons.
	 * 4. Triggers the standard neuron that it calculates its output.
	 * 5. Prints out the output of the standard neuron out to the console.
	 */
	public static void main(String[] args) {
		
		//Creates source neuron 1.
		final SourceNeuron<Double> sourceNeuron1 =
		new SourceNeuron<Double>(6.0);
		
		//Creates source neuron 2.
		final SourceNeuron<Double> sourceNeuron2 =
		new SourceNeuron<Double>(8.0);
		
		//Creates source neuron 3.
		final SourceNeuron<Double> sourceNeuron3 =
		new SourceNeuron<Double>(16.0);
		
		//Creates neuron.
		final StandardNeuron<Double> neuron =
		new StandardNeuron<Double>()
		.addInputNeuron(sourceNeuron1)
		.addInputNeuron(sourceNeuron2)
		.addInputNeuron(sourceNeuron3)
		.setOutputFunction(Calculator.DOUBLE_AVERAGE);

		//Triggers neuron and prints out its output to the console.
		neuron.trigger();
		System.out.println("neuron output: " + neuron.getRefOutput());
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private StandardNeuronTutorial1() {}
}
