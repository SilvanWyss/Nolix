//package declaration
package ch.nolix.system.netNeuron;

//own imports
import ch.nolix.core.validator2.Validator;
import ch.nolix.system.client.Session;
import ch.nolix.system.client.StandardClient;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 50
 * @param <O> The type of the output
 * of the net front neuron of a net front neuron session.
 */
final class FrontNetNeuronSession<O>
extends Session<StandardClient> {
	
	//attribute
	private final FrontNetNeuron<O> frontNetNeuron;

	//constructor
	/**
	 * Creates a new net front neuron session
	 * with the given netFrontNeuron.
	 * 
	 * @param netFrontNeuron
	 * @throws NullArgumentException if the given net front neuron is null.
	 */
	public FrontNetNeuronSession(final FrontNetNeuron<O> netFrontNeuron) {
		
		Validator
		.suppose(netFrontNeuron)
		.isOfType(FrontNetNeuronSession.class);
		
		this.frontNetNeuron = netFrontNeuron;
	}
	
	//method
	/**
	 * Initializes this net front neuron session.
	 */
	@Override
	public void initialize() {}
	
	//method
	/**
	 * Runs the net front neuron of this net front neuron session.
	 */
	public void Fire() {
		frontNetNeuron.fire();
	}
	
	//method
	/**
	 * Sets the output of the net front neuron of this net front neuron session.
	 * 
	 * @param output
	 */
	public void SetOutput(final String output) {
		frontNetNeuron.setOutput(output);
	}
}
