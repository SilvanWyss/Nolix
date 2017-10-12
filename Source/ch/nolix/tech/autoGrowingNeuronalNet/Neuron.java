//package declaration
package ch.nolix.tech.autoGrowingNeuronalNet;

//own import
import ch.nolix.system.neuronoid.Neuronoid;

//class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 40
 * @param <IO> The type of the inputs and output of a neuron.
 */
public final class Neuron extends Neuronoid<Neuron, Double, Double> {

	//limits
	public static final int MIN_INPUT_NEURON_COUNT = 0;
	public static final int MAX_INPUT_NEURON_COUNT = Integer.MAX_VALUE;
	
	//method
	/**
	 * @return the maximal number of input neurons of a neuron.
	 */
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimal number of input neurons of a neuron.
	 */
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * Lets this neuron fire.
	 */
	protected void internal_fire() {
		setOutput(2.0 * getRefInputs().getRefAny());
	}
}
