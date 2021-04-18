package ch.nolix.systemtutorial.neuronalnettutorial;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.math.Calculator;
import ch.nolix.system.neuron.base.SourceNeuron;
import ch.nolix.system.neuron.neuronalnet.ForwardMultiLayerNetCreator;
import ch.nolix.system.neuron.neuronalnet.NeuronalNet;

/**
 * The {@link ForwardMultiLayerNetCreatorTutorial} is a tutorial for a {@link ForwardMultiLayerNetCreator}s.
 * Of the {@link ForwardMultiLayerNetCreatorTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-01-15
 * @lines 50
 */
public final class ForwardMultiLayerNetCreatorTutorial {
	
	/**
	 * 1. Creates a {@link ForwardMultiLayerNetCreator}.
	 * 2. Lets the {@link ForwardMultiLayerNetCreator} create a {@link NeuronalNet}.
	 * 3. Adds inputs for the {@link NeuronalNet}.
	 * 4. Fires the {@link NeuronalNet}.
	 * 5. Prints out the output of the {@link NeuronalNet} to the console.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		
		//Creates a ForwardMultiLayerNetCreator.
		final var forwardMultiLayerNetCreator =
		new ForwardMultiLayerNetCreator<Double>()
		.setLayerCount(4)
		.setNeuronsPerLayer(5)
		.setOutputFunction(Calculator::getSum);
		
		//Lets the ForwardMultiLayerNetCreator create a NeuronalNet.
		final var neuronalNet = forwardMultiLayerNetCreator.createNeuronalNet();
		
		//Creates inputs for the NeuronalNet.
		final var inputs = LinkedList.withElements(0.01, 0.01, 0.01, 0.01, 0.01);
		
		//Adds the inputs to the NeuronalNet.
		neuronalNet.addInputNeuron(new SourceNeuron<Iterable<Double>>(inputs));
		
		//Fires the NeuronalNet.
		neuronalNet.fire();
		
		//Prints out the output of the NeuronalNet to the console.
		System.out.println("output values:");
		neuronalNet.getRefOutput().forEach(System.out::println);
	}
	
	/**
	 * Prevents that an instance of the {@link ForwardMultiLayerNetCreatorTutorial} can be created.
	 */
	private ForwardMultiLayerNetCreatorTutorial() {}
}
