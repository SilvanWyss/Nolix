//package declaration
package ch.nolix.systemTutorial.neuronTutorial;

//own import
import ch.nolix.system.neuron.StandardNeuron;

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
		final StandardNeuron<Double> neuron1 =
		new StandardNeuron<Double>().setOutputFunction(1.0);
		
		//Creates neuron 2.
		final StandardNeuron<Double> neuron2 =
		new StandardNeuron<Double>()
		.setOutputFunction(v -> 2.0 * v.iterator().next())
		.addInputNeuron(neuron1)
		.addTriggeringNeuron(neuron1);
		
		//Creates neuron 3.
		final StandardNeuron<Double> neuron3 =
		new StandardNeuron<Double>()
		.setOutputFunction(v -> 2.0 * v.iterator().next())
		.addInputNeuron(neuron2)
		.addTriggeringNeuron(neuron2);
		
		//Creates neuron 4.
		final StandardNeuron<Double> neuron4 =
		new StandardNeuron<Double>()
		.setOutputFunction(v -> 2.0 * v.iterator().next())
		.addInputNeuron(neuron3)
		.addTriggeringNeuron(neuron3);
		
		//Triggers neuron 1.
		neuron1.trigger();
		
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
