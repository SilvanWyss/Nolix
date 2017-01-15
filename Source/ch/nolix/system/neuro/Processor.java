//package declaration
package ch.nolix.system.neuro;

//own import
import ch.nolix.common.container.List;

//class
/**
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 40
 */
public final class Processor {

	//multiple attribute
	private final List<Neuron<?, ?, ?>> queue = new List<Neuron<?, ?, ?>>();
	
	//package-visible constructor
	/**
	 * Creates new processor with the given neuron.
	 * 
	 * @param neuron
	 * @throws NullArgumentException if the given neuron is null.
	 */
	Processor(final Neuron<?, ?, ?> neuron) {
		
		queue.addAtBegin(neuron);
		
		while (queue.containsAny()) {
			queue.getRefLast().trigger(this);
			queue.removeLast();
		}
	}
	
	//method
	/**
	 * Adds the given to trigger.
	 * 
	 * @param neuron
	 * @throws NullArgumentException if the given neuron is null.
	 */
	public void addNeuronToTrigger(final TriggerableNeuronoid neuron) {
		queue.addAtBegin(neuron.getRefNeuron());
	}
}
