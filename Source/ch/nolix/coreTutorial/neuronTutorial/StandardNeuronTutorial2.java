//package declaration
package ch.nolix.coreTutorial.neuronTutorial;

//own imports
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.neuron.StandardNeuron;

//class
/**
 * This class provides a tutorial for the standard neuron class.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 60
 */
public final class StandardNeuronTutorial2 {

	//main method
	/**
	 * 1. Creates 4 standard neurons. The output type of the neurons is a floating point number.
	 * 2. Sets an output function of the neuron 1, 2, 3, so that these neurons will return a constant value as output.
	 * 3. Adds neuron 1 as triggering neuron to neuron 1, 2, 3.
	 * 4. Adds neuron 1, 2, 3 as input neurons to neuron 4.
	 * 5. Adds an output function to neuron 4, so that neuron 4 will calculate its output as the average of the outputs of its input neurons.
	 * 6. Triggers neuron 1, that then will trigger neuron 2, 3, 4. The neurons calculate their outputs when they are triggered.
	 * 7. Prints out the output of neuron 4 out to the console.
	 */
	public static void main(String[] args) {
		
		//Creates neuron 1, that will serve as input neuron.
		final StandardNeuron<Double> neuron1 
		= new StandardNeuron<Double>()
		.setOutputFunction(6.0);
		
		//Creates neuron 2, that will serve as input neuron.
		final StandardNeuron<Double> neuron2 
		= new StandardNeuron<Double>()
		.setOutputFunction(8.0)
		.addTriggeringNeuron(neuron1);
		
		//Creates neuron 3, that will serve as input neuron.
		final StandardNeuron<Double> neuron3
		= new StandardNeuron<Double>()
		.setOutputFunction(16.0)
		.addTriggeringNeuron(neuron1);
				
		//Creates neuron 4, that will calculate its output as the average of the output of its input neurons.
		final StandardNeuron<Double> neuron4
		= new StandardNeuron<Double>()
		.addInputNeuron(neuron1)
		.addInputNeuron(neuron2)
		.addInputNeuron(neuron3)
		.setOutputFunction(Calculator.DOUBLE_AVERAGE)
		.addTriggeringNeuron(neuron1);

		//Triggers neuron 1 and prints out the output of neuron 4 to the console.
		neuron1.trigger();
		System.out.println("neuron 4 output: " + neuron4.getRefOutput());
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private StandardNeuronTutorial2() {}
}
