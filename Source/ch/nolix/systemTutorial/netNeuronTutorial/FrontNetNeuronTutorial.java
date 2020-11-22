package ch.nolix.systemTutorial.netNeuronTutorial;

import ch.nolix.system.netNeuron.FrontNetNeuron;
import ch.nolix.common.node.Node;
import ch.nolix.system.baseNeuron.SourceNeuron;
import ch.nolix.system.netNeuron.BackNetNeuron;

//class
/**
* The {@link FrontNetNeuronTutorial} is a tutorial for {FrontNetNeuron}s.
* 
* @author Silvan Wyss
* @date 2017-01-22
* @lines 50
*/
public final class FrontNetNeuronTutorial {
	
	/**
	 * 1. Creates a {@link BackNetNeuron} and adds an {@link InputNeuron} to it.
	 * 3. Creates some {@link FrontNetNeuron} that will connect to the back net neuron.
	 * 4. Fires the {@link BackNetNeuron}.
	 * 5. Prints out the outputs of the {@link FrontNetNeuron}s to the console.
	 * 6. Closes the {@link BackNetNeuron} and the {@link FrontNetNeuron}s.
	 * 
	 * @param arguments
	 */
	public static final void main(String[] arguments) {
		
		//Defines the port for the BackNetNeuron.
		final var port = 20000;
		
		//Creates a BackNeNeuron.
		final var netBackNeuron = new BackNetNeuron<String>(port, Node::fromString);
		
		//Creates and adds an InpuNeuron to the BackNetNeuron.
		netBackNeuron.addInputNeuron(new SourceNeuron<String>("Hello_World!"));
		
		//Creates some FrontNetNeuron that will connect to the BackNetNeuron.
		final var frontNetNeuron1 = new FrontNetNeuron<String>(port, Node::toString);
		final var frontNetNeuron2 = new FrontNetNeuron<String>(port, Node::toString);
		
		//Fires the BackNetNeuron.
		netBackNeuron.fire();
		
		//Prints out the output of the FrontNetNeurons to the console.
		System.out.println("front net neuron 1 output: " + frontNetNeuron1.getRefOutput());
		System.out.println("front net neuron 2 output: " + frontNetNeuron2.getRefOutput());
		
		//Closes the BackNetNeuron and the FrontNetNeurons.
		netBackNeuron.close();
		frontNetNeuron1.close();
		frontNetNeuron2.close();
	}
	
	/**
	 * Avoids that an instance of the {@link FrontNetNeuronTutorial} can be created.
	 */
	private FrontNetNeuronTutorial() {}
}
