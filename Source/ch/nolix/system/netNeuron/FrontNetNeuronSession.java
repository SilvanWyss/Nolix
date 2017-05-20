//package declaration
package ch.nolix.system.netNeuron;

//own imports
import ch.nolix.system.application.ContextSession;
import ch.nolix.system.application.StandardClient;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 * @param <O> - The type of the output of the front net neuron of a front net neuron session.
 */
final class FrontNetNeuronSession<O>
extends ContextSession<StandardClient, FrontNetNeuron<O>> {

	//constructor
	/**
	 * Creates new front net neuron session with the given context.
	 * 
	 * @param context
	 * @throws NullArgumentException if the given context is null.
	 */
	public FrontNetNeuronSession(final FrontNetNeuron<O> context) {
		
		//Calls constructor the base class.
		super(context);
	}
	
	//method
	/**
	 * Initializes this front net neuron session.
	 */
	public void initialize() {}
	
	//run method
	public void SetOutput(final String output) {
		getRefContext().setOutput(output);
	}
	
	//run method
	public void Trigger() {
		getRefContext().trigger();
	}
}
