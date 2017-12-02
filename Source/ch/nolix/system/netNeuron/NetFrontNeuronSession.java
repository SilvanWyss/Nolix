//package declaration
package ch.nolix.system.netNeuron;

//own imports
import ch.nolix.system.client.ContextSession;
import ch.nolix.system.client.StandardClient;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 50
 * @param <O> The type of the output
 * of the net front neuron of a net front neuron session.
 */
final class NetFrontNeuronSession<O>
extends ContextSession<StandardClient, NetFrontNeuron<O>> {

	//constructor
	/**
	 * Creates new net front neuron session
	 * with the given netFrontNeuron.
	 * 
	 * @param netFrontNeuron
	 * @throws NullArgumentException if the given net front neuron is null.
	 */
	public NetFrontNeuronSession(final NetFrontNeuron<O> netFrontNeuron) {
		
		//Calls constructor the base class.
		super(netFrontNeuron);
	}
	
	//method
	/**
	 * Initializes this net front neuron session.
	 */
	public void initialize() {}
	
	//method
	/**
	 *  Runs the net front neuron of this net front neuron session.
	 */
	public void Fire() {
		getRefContext().fire();
	}
	
	//method
	/**
	 * Sets the output of the net front neuron of this net front neuron session.
	 * 
	 * @param output
	 */
	public void SetOutput(final String output) {
		getRefContext().setOutput(output);
	}
}
