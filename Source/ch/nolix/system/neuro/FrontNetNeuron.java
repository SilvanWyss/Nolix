//package declaration
package ch.nolix.system.neuro;

//own imports
import ch.nolix.common.functional.IElementTakerElementGetter;
import ch.nolix.common.specification.Specification;

//class
/**
 * A front net neuron is a neuron that connects to a net neuron on an other process or machine.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 10
 * @param <O>
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
	 */
	public FrontNetNeuron(
		final String ip,
		final int port,
		IElementTakerElementGetter<Specification, O> transformer
	) {
		//TODO
		
		this.transformer = transformer;
	}

	/**
	 * Triggers this front net neuron using the given processor.
	 * 
	 * @param processor
	 */
	protected void trigger(Processor processor) {
		getRefTriggerableNeurons().forEach(tn -> processor.addNeuronToTrigger(tn));
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
	 * @return the minimum number of input neuronso f this front net neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 0;
	}
	
	//package-visible method
	/**
	 * Sets the output of this front net neuron and triggers this front net neuron.
	 * 
	 * @param output
	 */
	void setOutputAndTrigger(final Specification output) {
		setOutput(transformer.getOutput(output));
		trigger();
	}
	
	//package-visible method
	/**
	 * Sets the output of this front net neuron and triggers this front net neuron.
	 * 
	 * @param output
	 */
	void setOutputAndTrigger(final String output) {
		setOutputAndTrigger(new Specification(output));
	}
}
