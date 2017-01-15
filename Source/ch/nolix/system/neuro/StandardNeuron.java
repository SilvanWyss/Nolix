//package declaration
package ch.nolix.system.neuro;

//own imports
import ch.nolix.common.functional.IElementTakerElementGetter;
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * A standard neuron is a neuron with an output or an output function that can be set to dynamically.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 80
 * @param <O> The type of the output of the standard neuron.
 */
public final class StandardNeuron<O>
extends Neuron<O, O, StandardNeuron<O>> {
	
	//attribute
	private IElementTakerElementGetter<Iterable<O>, O> outputFunction;
	
	public StandardNeuron() {}
	
	public StandardNeuron(final O output) {
		setOutputFunction(output);
	}
	
	//method
	/**
	 * Triggers this standard neuron using the given processor.
	 * 
	 * @param processor
	 */
	protected void trigger(final Processor processor) {
		setOutput(outputFunction.getOutput(getRefInputs()));
		getRefTriggerableNeurons().forEach(tn -> processor.addNeuronToTrigger(tn));
	}
	
	//method
	/**
	 * Sets the output function of this standard neuron.
	 * This method overrides any previous output that was set to this standard neuron.
	 * 
	 * @param outputFunction
	 * @return this standard neuron.
	 * @throws NullArgumentException if the given output function is null.
	 */
	public StandardNeuron<O> setOutputFunction(
		final IElementTakerElementGetter<Iterable<O>, O> outputFunction
	) {
		//Checks if the given output function is not null.
		ZetaValidator.supposeThat(outputFunction).thatIsNamed("output function").isNotNull();
		
		this.outputFunction = (IElementTakerElementGetter<Iterable<O>, O>)outputFunction;

		return this;
	}
	
	//method
	/**
	 * Sets the output of this standard neuron.
	 * This method overrided any previous output function that was set to this standard neuron.
	 * 
	 * @param output
	 * @return this standard neuron.
	 */
	public StandardNeuron<O> setOutputFunction(final O output) {
		
		setOutputFunction(c -> output);
		
		return this;
	}

	//method
	/**
	 * @return the maximal number of input neurons of this standard neuron.
	 */
	protected int getMaxInputNeuronCount() {
		return Integer.MAX_VALUE;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this standard neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 0;
	}
}
