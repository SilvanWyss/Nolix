//package declaration
package ch.nolix.system.neuronoid;

//class
/**
 * A bundle neuron is a neuron whose output is a container of its inputs.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 * @param <O> The type of the inputs of a bundle neuron.
 */
public final class BundleNeuron<O>
extends Neuronoid<BundleNeuron<O>, O, Iterable<O>> {
	
	//limits
	public static final int MIN_INPUT_NEURON_COUNT = 0;
	public static final int MAX_INPUT_NEURON_COUNT = Integer.MAX_VALUE;
	
	//method
	/**
	 * @return the maximum number of input neurons of this bundle neuron.
	 */
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this bundle neuron.
	 */
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * Lets this bundle neuron fire.
	 * 
	 * @param processor
	 */
	protected void internal_fire() {
		internal_setOutput(getRefInputs());
	}
}
