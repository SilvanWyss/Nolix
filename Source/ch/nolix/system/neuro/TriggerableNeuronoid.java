//package declaration
package ch.nolix.system.neuro;

//class
/**
 * A triggerable neuronoid provides a neutro to trigger.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 10
 * @param <O>
 * @param <N>
 */
public class TriggerableNeuronoid<O, N extends Neuron<O, N>> {
	
	final Neuron<O, N> neuron;
		
	public TriggerableNeuronoid(Neuron<O, N> neuron) {
		this.neuron = neuron;
	}
	
	public boolean hasNeuron(Neuron<O, N> n) {
		return (n == neuron);
	}
}
