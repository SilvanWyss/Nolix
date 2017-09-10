//package declaration
package ch.nolix.system.netNeuron;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.system.client.StandardApplication;
import ch.nolix.system.client.StandardClient;
import ch.nolix.system.neuron.Neuron;
import ch.nolix.system.neuron.TriggerQueue;

//class
/**
 * A net back neuron is a neuron that can be connected
 * from net front neurons on other processes or machines.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 90
 * @param <I> The type of the input of a net back neuron.
 */
public final class NetBackNeuron<I>
extends Neuron<I, Object, NetBackNeuron<I>> {
		
	//constant
	private static final String APPLICATION_NAME = "NetBackNeuron";
	
	//attributes
	private final IElementTakerElementGetter<I, StandardSpecification> transformer;
	private final StandardApplication<StandardClient> application;
	
	//constructor
	/**
	 * Creates new net back neuron with the given port and transformer.
	 * 
	 * @param port
	 * @param transformer
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65'535].
	 * @throws NullArgumentException if the given transfromer is null.
	 */
	public NetBackNeuron(
		final int port,
		final IElementTakerElementGetter<I, StandardSpecification> transformer)
	{
		//Checks if the given transform is not null.
		Validator.supposeThat(transformer).thatIsNamed("transformer").isNotNull();
		
		//Sets the transformer of this net back neuron.
		this.transformer = transformer;
		
		//Creates the application of this net back neuron.
		application =
		new StandardApplication<StandardClient>(
			APPLICATION_NAME,
			NetBackNeuronSession.class,
			port
		);
	}

	//method
	/**
	 * @return the maximum number of input neurons of this net back neuron.
	 */
	protected int getMaxInputNeuronCount() {
		return 1;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this net back neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 0;
	}
	
	//method
	/**
	 * Triggers this net back neuron using the given processor.
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
