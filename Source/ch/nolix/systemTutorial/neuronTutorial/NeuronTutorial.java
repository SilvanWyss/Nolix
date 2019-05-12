//package declaration
package ch.nolix.systemTutorial.neuronTutorial;

import ch.nolix.core.math.Calculator;
import ch.nolix.system.neuron.Neuron;
import ch.nolix.system.neuronoid.SourceNeuron;

//class
/**
 * The {@link NeuronTutorial} is a tutorial for a {@link Neuron}.
 * Of the {@link NeuronTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 60
 */
public final class NeuronTutorial {

	//main method
	/**
	 * 1. Creates 3 source neurons and a target neuron.
	 * The output type of all neurons is a floating point number.
	 * 2. Adds the source neurons as input neurons to the target neuron.
	 * 3. Defines the output function of the target neuron.
	 * 4. Fires the target neuron for that it calculates its output.
	 * 5. Prints out the output of the target neuron to the console.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates source neuron 1.
		final var sourceNeuron1 = new SourceNeuron<Double>(5.0);
		
		//Creates source neuron 2.
		final var sourceNeuron2 = new SourceNeuron<Double>(7.0);
		
		//Creates source neuron 3.
		final var sourceNeuron3 = new SourceNeuron<Double>(18.0);
		
		//Creates target neuron.
		final var targetNeuron =
		new Neuron<Double>()
		.addInputNeuron(sourceNeuron1)
		.addInputNeuron(sourceNeuron2)
		.addInputNeuron(sourceNeuron3)
		.setOutputFunction(Calculator.DOUBLE_AVERAGE);

		//Fires the target neuron.
		targetNeuron.fireTransitively();
		
		//Prints out the output of the target neuron to the console.
		System.out.println("output: " + targetNeuron.getRefOutput());
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link NeuronTutorial} can be created.
	 */
	private NeuronTutorial() {}
}
