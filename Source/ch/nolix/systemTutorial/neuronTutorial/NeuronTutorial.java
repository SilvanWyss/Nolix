//package declaration
package ch.nolix.systemTutorial.neuronTutorial;

//own imports
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.system.neuron.Neuron;
import ch.nolix.system.neuronoid.SourceNeuron;

//class
/**
 * This class provides a tutorial for the neuron class.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 60
 */
public final class NeuronTutorial {

	//main method
	/**
	 * 1. Creates 3 source neurons and a result neuron. The output type of all neurons is a floating point number.
	 * 2. Adds the source neurons as input neurons to the result neuron.
	 * 3. Sets the output function of the result neuron.
	 * 4. Let the result neuron fire that it calculates its output.
	 * 5. Prints out the output of the result neuron to the console.
	 * 
	 * @param args
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
		
		//Creates result neuron.
		final Neuron<Double> resultNeuron =
		new Neuron<Double>()
		.addInputNeuron(sourceNeuron1)
		.addInputNeuron(sourceNeuron2)
		.addInputNeuron(sourceNeuron3)
		.setOutputFunction(Calculator.DOUBLE_AVERAGE);

		//Lets the result neuron fire.
		resultNeuron.fire();
		
		//Prints out the output of the result neuron to the console.
		System.out.println("output: " + resultNeuron.getRefOutput());
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private NeuronTutorial() {}
}
