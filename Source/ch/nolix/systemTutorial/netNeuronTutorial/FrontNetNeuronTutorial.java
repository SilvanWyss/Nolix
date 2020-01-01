//package declaration
package ch.nolix.systemTutorial.netNeuronTutorial;

//own imports
import ch.nolix.system.netNeuron.FrontNetNeuron;
import ch.nolix.common.node.Node;
import ch.nolix.system.baseNeuron.SourceNeuron;
import ch.nolix.system.netNeuron.BackNetNeuron;

//class
/**
* The {@link FrontNetNeuronTutorial} is a tutorial for {FrontNetNeuron}.
* 
* @author Silvan Wyss
* @month 2017-01
* @lines 60
*/
public final class FrontNetNeuronTutorial {

	//main method
	/**
	 * 1. Creates a back net neuron and adds an input neuron to it.
	 * 3. Creates front net neurons that will connect to the back net neuron.
	 * 4. Fires the back net neuron.
	 * 5. Prints out the outputs of the front net neurons to the console.
	 * 6. Closes the back net neuron and the front net neurons.
	 * 
	 * @param arguments
	 */
	public static final void main(String[] arguments) {
		
		//Defines the port for the back net neuron.
		final var port = 20000;
		
		//Creates a back net neuron.
		final var netBackNeuron =
		new BackNetNeuron<String>(port, s -> Node.fromString(s));
		
		//Creates and adds an input neuron to the back net neuron.
		netBackNeuron.addInputNeuron(new SourceNeuron<String>("Hello_World!"));
		
		//Creates front net neurons that will connect to the back net neuron.
		final var frontNetNeuron1 = new FrontNetNeuron<String>(port, s -> s.toString());
		final var frontNetNeuron2 = new FrontNetNeuron<String>(port, s -> s.toString());
		
		//Fires the back net neuron.
		netBackNeuron.fireTransitively();
		
		//Prints out the output of the front net neurons to the console.
		System.out.println("front net neuron 1 output: " + frontNetNeuron1.getRefOutput());
		System.out.println("front net neuron 2 output: " + frontNetNeuron2.getRefOutput());
		
		//Closes the back net neuron and the fron net neurons.
		netBackNeuron.close();
		frontNetNeuron1.close();
		frontNetNeuron2.close();
	}
	
	//access-reducing constructor
	/**
	 * Avoids that an instance of the {@link FrontNetNeuronTutorial} can be created.
	 */
	private FrontNetNeuronTutorial() {}
}
