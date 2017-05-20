//package declaration
package ch.nolix.systemTutorial.netNeuronTutorial;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.system.netNeuron.FrontNetNeuron;
import ch.nolix.system.netNeuron.NetNeuron;
import ch.nolix.system.neuron.SourceNeuron;

//class
/**
* This class provides a tutorial for the net neuron class.
* 
* @author Silvan Wyss
* @month 2017-01
* @lines 50
*/
public final class NetNeuronTutorial {

	//main method
	/**
	 * 1. Creates net neuron and adds an input neuron to it.
	 * 2. Creates front net neurons that are connected to the net neuron.
	 * 3. Triggers the net neuron.
	 * 4. Prints out to the console the outputs of the front net neurons.
	 * 
	 * @param arguments
	 */
	public static final void main(String[] arguments) {
		
		//Defines port of net neuron.
		final int port = 20000;
		
		//Creates net neuron and adds a source neuron as input neuron to it.
		final NetNeuron<String> netNeuron =
		new NetNeuron<String>(port, s -> new Specification(s))
		.addInputNeuron(new SourceNeuron<String>("Hello_World!"));
		
		//Creates front net neurons that are connected to the net neuron.
		final FrontNetNeuron<String> frontNetNeuron1
		= new FrontNetNeuron<String>("::1", port, s -> s.toString());
		final FrontNetNeuron<String> frontNetNeuron2
		= new FrontNetNeuron<String>("::1", port, s -> s.toString());
		
		//Triggers the net neuron and prints out to the console the output of the front neurons 
		netNeuron.trigger();
		System.out.println("front net neuron 1, output: " + frontNetNeuron1.getRefOutput());
		System.out.println("front net neuron 2, output: " + frontNetNeuron2.getRefOutput());
		
		//Exits the program successfully.
		System.exit(0);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private NetNeuronTutorial() {}
}
