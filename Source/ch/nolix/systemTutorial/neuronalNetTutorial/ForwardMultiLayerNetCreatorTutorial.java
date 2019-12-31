//package declaration
package ch.nolix.systemTutorial.neuronalNetTutorial;

import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.math.Calculator;
import ch.nolix.system.neuronalNet.ForwardMultiLayerNetCreator;
import ch.nolix.system.neuronoid.SourceNeuron;

//class
/**
 * The {@link ForwardMultiLayerNetCreatorTutorial}
 * is a tutorial for a {@link ForwardMultiLayerNetCreator}
 * .
 * Of the {@link ForwardMultiLayerNetCreatorTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 60
 */
public final class ForwardMultiLayerNetCreatorTutorial {

	//main method
	/**
	 * 1. Creates a forward multi layer net creator.
	 * 2. Lets the forward multi layer net creator create a neuronal net.
	 * 3. Creates the inputs for the neuronal net.
	 * 4. Fires the neuronal net.
	 * 5. Prints out the output of the neuronal net to the console.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		
		//Creates a forward multi layer net creator.
		final var forwardMultiLayerNetCreator =
		new ForwardMultiLayerNetCreator<Double>()
		.setLayerCount(4)
		.setNeuronsPerLayer(5)
		.setOutputFunction(Calculator::getSum);
		
		//Lets the forward multi layer net creator create a neuronal net.
		final var neuronalNet = forwardMultiLayerNetCreator.createNeuronalNet();
		
		//Creates inputs for the neuronal net.
		final var inputs = new LinkedList<>(0.01, 0.01, 0.01, 0.01, 0.01);
		
		//Sets the inputs to the neuronal net.
		neuronalNet.addInputNeuron(new SourceNeuron<Iterable<Double>>(inputs));
		
		//Fires the neuronal net.
		neuronalNet.fireTransitively();
		
		//Prints out the output of the neuronal net to the console.
		System.out.println("output values:");
		neuronalNet.getRefOutput().forEach(v -> System.out.println(v));
	}
	
	//access-reducing constructor
	/**
	 * Avoids that an instance of the {@link ForwardMultiLayerNetCreatorTutorial} can be created.
	 */
	private ForwardMultiLayerNetCreatorTutorial() {}
}
