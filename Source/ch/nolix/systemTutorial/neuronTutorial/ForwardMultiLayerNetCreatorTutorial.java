//package declaration
package ch.nolix.systemTutorial.neuronTutorial;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.system.neuron.ForwardMultiLayerNetCreator;
import ch.nolix.system.neuron.NeuronalNet;
import ch.nolix.system.neuron.SourceNeuron;

//class
/**
 * This class provides a tutorial for the forward multi layer net creator class.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 60
 */
public final class ForwardMultiLayerNetCreatorTutorial {

	//main method
	/**
	 * 1. Creates neuronal net creator.
	 * 2. Lets the neuronal net creator create a neuronal net.
	 * 3. Creates the inputs for the neuronal net.
	 * 4. Triggers the neuronal net and prints out its output to the console.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		
		//Creates neuronal net creator.
		final ForwardMultiLayerNetCreator<Double> neuronalNetCreator =
		new ForwardMultiLayerNetCreator<Double>()
		.setLayerCount(4)
		.setNeuronsPerLayer(5)
		.setOutputFunction(Calculator.DOUBLE_SUM);
		
		//Lets the neuronal net creator create a neuronal net.
		final NeuronalNet<Double> neuronalNet = neuronalNetCreator.createNeuronalNet();
		
		//Creates inputs
		final List<Double> inputs = new List<Double>();
		Sequencer.forCount(5).run(() -> inputs.addAtEnd(0.01));
		
		//Sets the inputs to the neuronal net.
		neuronalNet.addInputNeuron(new SourceNeuron<Iterable<Double>>(inputs));
		
		//Triggers the neuronal net.
		neuronalNet.fire();
		
		//Prints the output of the neuronal net out to the console.
		System.out.println("output values:");
		neuronalNet.getRefOutput().forEach(v -> System.out.println(v));
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private ForwardMultiLayerNetCreatorTutorial() {}
}
