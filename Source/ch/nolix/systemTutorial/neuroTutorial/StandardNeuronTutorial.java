/*
 * file:	StandardNeuronTutorial.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	70
 */

//package declaration
package ch.nolix.systemTutorial.neuroTutorial;

//own imports
import ch.nolix.common.mathematics.Calculator;
import ch.nolix.system.neuro.StandardNeuron;

//package-visible class
/**
 * This class provides a tutorial for the standard neuron class.
 */
final class StandardNeuronTutorial {

	//main method
	/**
	 * Creates 4 standard neurons, whereas 3 neurons are connected as input neurons to the 4th neuron.
	 * The output type of the neurons is a floating point number.
	 * Neuron 1 - 3 will return a fixed value as output
	 * Neuron 4 will calculate its output as the average of the outputs of its input neurons.
	 * First, neuron 1 is processed, that will process neuron 2 - 4 because these neurons are triggered from neuron 1 in the order neuron 1 was added as triggering neuron to them.
	 * A neuron will calculate or update its output when it is processed.
	 * Finally, the output of neuron 4 is print out to the console.
	 */
	public static void main(String[] args) {
			
		//Creates neuron 1, that will serve as input neuron.
		final StandardNeuron<Double> neuron1 
		= new StandardNeuron<Double>()
		.setOutputFunction(c -> 6.0);
		
		//Creates neuron 2, that will serve as input neuron.
		final StandardNeuron<Double> neuron2 
		= new StandardNeuron<Double>()
		.setOutputFunction(c -> 8.0)
		.addTriggeringNeuron(neuron1);
		
		//Creates neuron 3, that will serve as input neuron.
		final StandardNeuron<Double> neuron3
		= new StandardNeuron<Double>()
		.setOutputFunction(c -> 16.0)
		.addTriggeringNeuron(neuron1);
				
		//Creates neuron 4, that will calculate its output as the average of the output of its input neurons.
		final StandardNeuron<Double> neuron4
		= new StandardNeuron<Double>()
		.addInputNeuron(neuron1)
		.addInputNeuron(neuron2)
		.addInputNeuron(neuron3)
		.setOutputFunction(Calculator.DOUBLE_AVERAGE)
		.addTriggeringNeuron(neuron1);

		//Processes neuron 1 and prints out the output of neuron 4 to the console.
		neuron1.trigger();
		System.out.println("neuron 4 output: " + neuron4.getRefOutput());
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private StandardNeuronTutorial() {}
}
