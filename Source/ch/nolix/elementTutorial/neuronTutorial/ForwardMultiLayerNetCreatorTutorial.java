//package declaration
package ch.nolix.elementTutorial.neuronTutorial;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.mathematics.Calculator;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.neuron.ForwardMultiLayerNetCreator;
import ch.nolix.element.neuron.NeuronalNet;
import ch.nolix.element.neuron.SourceNeuron;

//class
/**
 * This class provides a tutorial for the forward multi layer net creator class.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 50
 */
public final class ForwardMultiLayerNetCreatorTutorial {

	//main method
	/**
	 * 1. Creates neuronal net creator.
	 * 2. Lets the neuronal net creator create a neuronal net.
	 * 3. Creates the inputs for the neuronal net.
	 * 4. Triggers the neuronal net and prints out its output to the console.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] aruments) {
		
		//Creates neuronal net creator.
		final ForwardMultiLayerNetCreator<Double> neuronalNetCreator =
		new ForwardMultiLayerNetCreator<Double>()
		.setLayerCount(4)
		.setNeuronsPerLayer(5)
		.setOutputFunction(Calculator.DOUBLE_SUM);
		
		//Lets the neuronal net creator create a neuronal net.
		final NeuronalNet<Double> neuronalNet = neuronalNetCreator.createNeuronalNet();
		
		//Creates the inputs and sets them to the neuronal net.
		final List<Double> inputs = new List<Double>();
		Sequencer.forCount(5).run(() -> inputs.addAtEnd(0.01));
		neuronalNet.addInputNeuron(new SourceNeuron<Iterable<Double>>(inputs));
		
		//Triggers the neuronal net prints out its output to the console.
		neuronalNet.trigger();
		System.out.println("output values:");
		for (Double v: neuronalNet.getRefOutput()) {
			System.out.println(v);
		}
	}
}
