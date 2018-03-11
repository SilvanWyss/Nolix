//package declaration
package ch.nolix.system.neuronoid;

//class
/**
 * A source neuron is a neuron that provides a source.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 60
 * @param <O> The type of the output of a source neuron.
 */
public final class SourceNeuron<O>
extends Neuronoid<SourceNeuron<O>, Object, O> {
	
	//limits
	public static final int MIN_INPUT_NEURON_COUNT = 0;
	public static final int MAX_INPUT_NEURON_COUNT = 0;
	
	//constructor
	/**
	 * Creates a new source neuron with the given output.
	 * 
	 * @param output
	 */
	public SourceNeuron(final O output) {
		internal_setOutput(output);
	}
	
	//method
	/**
	 * @return the maximal number of input neurons of this source neuron.
	 */
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this source neuron.
	 */
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}
	
	//method
	/**
	 * Lets this source neuron fire.
	 */
	protected void internal_fire() {}
}
