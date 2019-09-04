//package declaration
package ch.nolix.system.netNeuron;

//own imports
import ch.nolix.system.client.Session;
import ch.nolix.system.client.StandardClient;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 20
 */
final class BackNetNeuronSession extends Session<StandardClient> {

	//method
	/**
	 * Initializes this net back neuron session.
	 */
	@Override
	public void initialize() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void updateCounterpart() {
		//TODO
	}
}
