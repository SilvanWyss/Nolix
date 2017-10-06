//package declaration
package ch.nolix.system.neuron;


//class
/**
 * A bundle neuron is a neuron whose output is a container of its inputs.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 * @param <O> - The type of the elements of the input container of a bundle neuron.
 */
public final class BundleNeuron<O>
extends Neuronoid<O, Iterable<O>, BundleNeuron<O>> {
	
	//method
	/**
	 * @return the maximum number of input neurons of this bundle neuron.
	 */
	protected int getMaxInputNeuronCount() {
		return Integer.MAX_VALUE;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this bundle neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 0;
	}

	//method
	/**
	 * Triggers this bundle neuron using the given processor.
	 * 
	 * @param processor
	 */
	protected void internal_fire() {
		setOutput(getRefInputs());
	}
}
