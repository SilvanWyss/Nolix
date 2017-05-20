//package declaration
package ch.nolix.system.neuron;

//own import
import ch.nolix.core.invalidStateException.UnsupportedMethodException;

//class
/**
 * A source neuron is a neuron that just serves as source.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 50
 */
public final class SourceNeuron<O>
extends Neuron<Object, O, SourceNeuron<O>> {

	//constructor
	/**
	 * Creates new source neuron with default values.
	 */
	public SourceNeuron() {}
	
	//constructor
	/**
	 * Creates new source neuron with the given output.
	 * 
	 * @param output
	 */
	public SourceNeuron(final O output) {
		setOutput(output);
	}
	
	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public void trigger(final TriggerQueue processor) {
		throw new UnsupportedMethodException(this, "trigger");
	}
	
	//method
	/**
	 * @return the maximal number of input neurons of this source neuron.
	 */
	protected int getMaxInputNeuronCount() {
		return 0;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this source neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 0;
	}
}
