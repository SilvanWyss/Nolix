//package declaration
package ch.nolix.system.netNeuron;

//own imports
import ch.nolix.core.application.StandardClient;
import ch.nolix.core.functional.IElementTakerElementGetter;
import ch.nolix.core.neuron.Neuron;
import ch.nolix.core.neuron.TriggerQueue;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A front net neuron is a neuron that connects to a net neuron on an other process or machine.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 10
 * @param <O> - The type of the output of a front net neuron.
 */
public final class FrontNetNeuron<O>
extends Neuron<Object, O, FrontNetNeuron<O>> {

	//attribute
	private final IElementTakerElementGetter<Specification, O> transformer;
	
	//constructor
	/**
	 * Creates new front net neuron that connects to the net neuron on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param transformer
	 * @throws NullArgumentException if the given transformer is null.
	 */
	public FrontNetNeuron(
		final String ip,
		final int port,
		IElementTakerElementGetter<Specification, O> transformer
	) {
		
		//Checks if the given transform function is not null.
		Validator.supposeThat(transformer).thatIsNamed("transformer").isNotNull();
				
		//Sets the transformer of this net neuron.
		this.transformer = transformer;
		
		new StandardClient(
			ip,
			port,
			NetNeuron.DEFAULT_NET_NEURON_APPLICATION_NAME,
			new FrontNetNeuronSession<O>(this)
		);
	}

	//method
	/**
	 * @return the maximum number of input neurons of this front net neuron.
	 */
	protected int getMaxInputNeuronCount() {
		return 0;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this front net neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 0;
	}
	
	//method
	/**
	 * Triggers this front net neuron using the given processor.
	 * 
	 * @param processor
	 */
	protected void trigger(TriggerQueue processor) {
		getRefTriggerableNeurons().forEach(tn -> processor.addNeuron(tn));
	}
	
	//package-visible method
	/**
	 * Sets the output of this front net neuron.
	 * 
	 * @param output
	 */
	void setOutput(final Specification output) {
		setOutput(transformer.getOutput(output));
	}
	
	//package-visible method
	/**
	 * Sets the output of this front net neuron.
	 * 
	 * @param output
	 */
	void setOutput(final String output) {
		setOutput(transformer.getOutput(new Specification(output)));
	}
}
