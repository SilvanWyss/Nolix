//package declaration
package ch.nolix.system.baseneuron;

//class
/**
 * A source neuron is a neuron that provides a source.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 60
 * @param <O> is the type of the output of a source neuron.
 */
public final class SourceNeuron<O>
extends BaseNeuron<SourceNeuron<O>, Object, O> {
	
	//constants
	public static final int MIN_INPUT_NEURON_COUNT = 0;
	public static final int MAX_INPUT_NEURON_COUNT = 0;
	
	//constructor
	/**
	 * Creates a new source neuron with the given output.
	 * 
	 * @param output
	 */
	public SourceNeuron(final O output) {
		internalSetOutput(output);
	}
	
	//method
	/**
	 * @return the maximum number of input neurons of this source neuron.
	 */
	@Override
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this source neuron.
	 */
	@Override
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}
	
	//method
	/**
	 * Lets this source neuron fire.
	 */
	@Override
	public void internalUpdate() {}
}
