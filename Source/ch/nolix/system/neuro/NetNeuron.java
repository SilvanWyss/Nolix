//package declaration
package ch.nolix.system.neuro;

//own imports
import ch.nolix.common.functional.IElementTakerElementGetter;
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.system.application.StandardApplication;
import ch.nolix.system.application.StandardClient;

//class
/**
 * A net neuron is a neuron that can be connected from net front neurons on other processes or machines.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 80
 * @param <I> - The type of the input of a net neuron.
 */
public final class NetNeuron<I, O>
extends Neuron<I, O, NetNeuron<I, O>> {
	
	//attribute
	private final IElementTakerElementGetter<I, O> transformer;
	private final StandardApplication<StandardClient> application;
	
	//constructor
	/**
	 * Creates new net neuron with the given port.
	 * 
	 * @param port
	 */
	public NetNeuron(
		final int port,
		final IElementTakerElementGetter<I, O> transformer)
	{
		//Checks if the given transform function is not null.
		ZetaValidator.supposeThat(transformer).thatIsNamed("transformer").isNotNull();
		
		//TODO
		application =
		new StandardApplication<StandardClient>(
			"Neuron",
			port,
			NetNeuronSession.class
		);
		
		this.transformer = transformer;
	}
	
	//method
	/**
	 * Triggers this net neuron using the given processor.
	 */
	protected void trigger(final Processor processor) {
		
		if (getRefInputs().isEmpty()) {
			setOutput(null);
		}
		else {
			setOutput(transformer.getOutput(getRefOneInput()));
			application.getRefClients().forEach(c -> c.invokeOnOriginMachine("SetOutputAndTrigger(" + getRefOutput() + ")"));
		}
		
		getRefTriggerableNeurons().forEach(tn -> processor.addNeuronToTrigger(tn));
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
}
