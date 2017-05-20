//package declaration
package ch.nolix.system.netNeuron;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.system.application.StandardApplication;
import ch.nolix.system.application.StandardClient;
import ch.nolix.system.neuron.Neuron;
import ch.nolix.system.neuron.TriggerQueue;

//class
/**
 * A net neuron is a neuron that can be connected from front net neurons on other processes or machines.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 80
 * @param <I> - The type of the input of a net neuron.
 */
public final class NetNeuron<I>
extends Neuron<I, Object, NetNeuron<I>> {
	
	//constant
	static final String DEFAULT_NET_NEURON_APPLICATION_NAME = "FrontNetNeuronApplication";
	
	//attribute
	private final IElementTakerElementGetter<I, Specification> transformer;
	private final StandardApplication<StandardClient> application;
	
	//constructor
	/**
	 * Creates new net neuron with the given port.
	 * 
	 * @param port
	 * @throws NullArgumentException if the given transfromer is null.
	 */
	public NetNeuron(
		final int port,
		final IElementTakerElementGetter<I, Specification> transformer)
	{
		//Checks if the given transform function is not null.
		Validator.supposeThat(transformer).thatIsNamed("transformer").isNotNull();
		
		//Sets the transformer of this net neuron.
		this.transformer = transformer;
		
		//Creates application of this net neuron.
		application =
		new StandardApplication<StandardClient>(
			DEFAULT_NET_NEURON_APPLICATION_NAME,
			NetNeuronSession.class,
			port
		);
	}

	//method
	/**
	 * @return the maximum number of input neurons of this net neuron.
	 */
	protected int getMaxInputNeuronCount() {
		return 1;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this net neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 0;
	}
	
	//method
	/**
	 * Triggers this net neuron using the given processor.
	 */
	protected void trigger(final TriggerQueue processor) {
		
		if (getRefInputs().isEmpty()) {
			setOutput(null);
		}
		else {
			setOutput(transformer.getOutput(getRefOneInput()));
			application.getRefClients().forEach(
				c -> {
					c.run("SetOutput(" + getRefOutput() + ")");
					c.run("Trigger");
				}
			);
		}
		
		getRefTriggerableNeurons().forEach(tn -> processor.addNeuron(tn));
	}
}
