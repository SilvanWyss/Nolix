//package declaration
package ch.nolix.systemTutorial.netNeuronTutorial;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.system.netNeuron.NetFrontNeuron;
import ch.nolix.system.neuronoid.SourceNeuron;
import ch.nolix.system.netNeuron.NetBackNeuron;

//class
/**
* This class provides a tutorial for the net back neuron class.
* 
* @author Silvan Wyss
* @month 2017-01
* @lines 50
*/
public final class NetFrontNeuronTutorial {

	//main method
	/**
	 * 1. Creates net back neuron and adds an input neuron to it.
	 * 2. Creates net front neurons that are connected to the net back neuron.
	 * 3. Triggers the net back neuron.
	 * 4. Prints out to the console the outputs of the net front neurons.
	 * 
	 * @param arguments
	 */
	public static final void main(String[] arguments) {
		
		//Defines port of net back neuron.
		final int port = 20000;
		
		//Creates net back neuron and adds a source neuron as input neuron to it.
		final NetBackNeuron<String> netBackNeuron
		= new NetBackNeuron<String>(port, s -> new StandardSpecification(s));
		netBackNeuron.addInputNeuron(new SourceNeuron<String>("Hello_World!"));
		
		//Creates net front neurons that are connected to the net back neuron.
		final NetFrontNeuron<String> netFrontNeuron1
		= new NetFrontNeuron<String>(port, s -> s.toString());
		final NetFrontNeuron<String> netFronNeuron2
		= new NetFrontNeuron<String>(port, s -> s.toString());
		
		//Triggers the net back neuron and prints out to the console the output of the front neurons 
		netBackNeuron.fire();
		Sequencer.waitForMilliseconds(500);
		System.out.println("net front neuron 1, output: " + netFrontNeuron1.getRefOutput());
		System.out.println("net front neuron 2, output: " + netFronNeuron2.getRefOutput());
		
		//Quits program.
		netBackNeuron.close();
		netFrontNeuron1.close();
		netFronNeuron2.close();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private NetFrontNeuronTutorial() {}
}
