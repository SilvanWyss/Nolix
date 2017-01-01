//package declaration
package ch.nolix.system.neuro;

//own imports
import ch.nolix.common.functional.IElementTakerElementGetter;
import ch.nolix.common.specification.Specification;
import ch.nolix.system.application.FrontStandardClient;

//class
/**
 * A front net neuron is a neuron that connects to a net neuron to get its output.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 10
 * @param <O>
 */
public final class FrontNetNeuron<O>
extends Neuron<O, FrontNetNeuron<O>> {

	private final IElementTakerElementGetter<Specification, O> transformer;
	private final FrontStandardClient client;
	
	public FrontNetNeuron(
		final String ip,
		final int port,
		IElementTakerElementGetter<Specification, O> transformer
	) {
		client = new FrontStandardClient(ip, port, "Neuron");
		
		this.transformer = transformer;
	}

	/**
	 * Triggers this front net neuron.
	 * 
	 * @param processor
	 */
	protected void trigger(Processor processor) {
		setOutput(transformer.getOutput(client.getDataFromOriginMachine("Output")));
	}
}
