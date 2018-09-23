//package declaration
package ch.nolix.systemTutorial.netNeuronTutorial;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.system.netNeuron.FrontNetNeuron;
import ch.nolix.system.neuronoid.SourceNeuron;
import ch.nolix.system.netNeuron.BackNetNeuron;

//class
/**
* This class provides a tutorial for the net front neuron class.
* 
* @author Silvan Wyss
* @month 2017-01
* @lines 60
*/
public final class FrontNetNeuronTutorial {

	//main method
	/**
	 * 1. Creates a net back neuron and adds a source neuron as input neuron to it.
	 * 3. Creates net front neurons that will connect to the net back neuron.
	 * 4. Lets the net back neuron fire.
	 * 5. Prints out to the console the outputs of the net front neurons.
	 * 6. Closes the net back neuron and the net front neurons.
	 * 
	 * @param arguments
	 */
	public static final void main(String[] arguments) {
		
		//Defines the port of the net back neuron.
		final int port = 20000;
		
		//Creates net back neuron and adds a source neuron as input neuron to it.
		final BackNetNeuron<String> netBackNeuron =
		new BackNetNeuron<String>(port, s -> new StandardSpecification(s));
		netBackNeuron.addInputNeuron(new SourceNeuron<String>("Hello_World!"));
		
		//Creates net front neurons that will connect to the net back neuron.
		final FrontNetNeuron<String> netFrontNeuron1
		= new FrontNetNeuron<String>(port, s -> s.toString());
		final FrontNetNeuron<String> netFronNeuron2
		= new FrontNetNeuron<String>(port, s -> s.toString());
		
		//Lets the net back neuron fire.
		Sequencer.waitForMilliseconds(200);
		netBackNeuron.fire();
		Sequencer.waitForMilliseconds(200);
		
		//Prints out to the console the output of the front neurons.
		System.out.println("net front neuron 1 output: " + netFrontNeuron1.getRefOutput());
		System.out.println("net front neuron 2 output: " + netFronNeuron2.getRefOutput());
		
		//Closes the net back neuron and the net front neurons.
		netBackNeuron.close();
		netFrontNeuron1.close();
		netFronNeuron2.close();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private FrontNetNeuronTutorial() {}
}
