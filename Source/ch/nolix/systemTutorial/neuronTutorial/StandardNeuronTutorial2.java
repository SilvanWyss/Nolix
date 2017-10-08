//package declaration
package ch.nolix.systemTutorial.neuronTutorial;

import ch.nolix.system.neuron.Neuron;

//class
/**
 * This class provides a tutorial for the standard neuron class.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 60
 */
public final class StandardNeuronTutorial2 {

	//main method
	/**
	 * 1. Creates 4 standard neurons that are connected in 1 line.
	 * 2. Triggers the first neuron and prints the output of all neurons out to the console.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates neuron 1.
		final Neuron<Double> neuron1 =
		new Neuron<Double>().setOutputFunction(1.0);
		
		//Creates neuron 2.
		final Neuron<Double> neuron2 =
		new Neuron<Double>()
		.setOutputFunction(v -> 2.0 * v.iterator().next())
		.addInputNeuron(neuron1);
		
		//Creates neuron 3.
		final Neuron<Double> neuron3 =
		new Neuron<Double>()
		.setOutputFunction(v -> 2.0 * v.iterator().next())
		.addInputNeuron(neuron2);
		
		//Creates neuron 4.
		final Neuron<Double> neuron4 =
		new Neuron<Double>()
		.setOutputFunction(v -> 2.0 * v.iterator().next())
		.addInputNeuron(neuron3);
		
		//Triggers neuron 1.
		neuron1.fire();
		
		//Prints the output of the neurons out to the console.
		System.out.println("neuron 1 output: " + neuron1.getRefOutput());
		System.out.println("neuron 2 output: " + neuron2.getRefOutput());
		System.out.println("neuron 3 output: " + neuron3.getRefOutput());
		System.out.println("neuron 4 output: " + neuron4.getRefOutput());
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private StandardNeuronTutorial2() {}
}
