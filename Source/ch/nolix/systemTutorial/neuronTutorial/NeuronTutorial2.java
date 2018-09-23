//package declaration
package ch.nolix.systemTutorial.neuronTutorial;

//own import
import ch.nolix.system.neuron.Neuron;

//class
/**
 * The {@link NeuronTutorial2} is a tutorial for a {@link Neuron}.
 * Of the {@link NeuronTutorial2} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 60
 */
public final class NeuronTutorial2 {

	//main method
	/**
	 * 1. Creates 4 neurons that are connected linearly.
	 * 2. Fires the first neuron.
	 * 3. Prints out the output of all neurons to the console.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates neuron 1.
		final var neuron1 =	new Neuron<Double>().setOutput(1.0);
		
		//Creates neuron 2.
		final var neuron2 =
		new Neuron<Double>()
		.setOutputFunction(v -> 2.0 * v.iterator().next())
		.addInputNeuron(neuron1);
		
		//Creates neuron 3.
		final var neuron3 =
		new Neuron<Double>()
		.setOutputFunction(v -> 2.0 * v.iterator().next())
		.addInputNeuron(neuron2);
		
		//Creates neuron 4.
		final var neuron4 =
		new Neuron<Double>()
		.setOutputFunction(v -> 2.0 * v.iterator().next())
		.addInputNeuron(neuron3);
		
		//Fires neuron 1.
		neuron1.fire();
		
		//Prints out the output of the neurons to the console.
		System.out.println("neuron 1 output: " + neuron1.getRefOutput());
		System.out.println("neuron 2 output: " + neuron2.getRefOutput());
		System.out.println("neuron 3 output: " + neuron3.getRefOutput());
		System.out.println("neuron 4 output: " + neuron4.getRefOutput());
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link NeuronTutorial2} can be created.
	 */
	private NeuronTutorial2() {}
}
