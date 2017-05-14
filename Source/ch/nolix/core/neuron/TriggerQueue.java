//package declaration
package ch.nolix.core.neuron;

//own import
import ch.nolix.core.container.List;

//class
/**
 * A trigger queue triggers neurons in the order they were added to the trigger queue.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 40
 */
public final class TriggerQueue {

	//multiple attribute
	private final List<Neuron<?, ?, ?>> queue = new List<Neuron<?, ?, ?>>();
	
	//package-visible constructor
	/**
	 * Creates new trigger queue that begins with the given neuron.
	 * 
	 * @param neuron
	 * @throws NullArgumentException if the given neuron is null.
	 */
	TriggerQueue(final Neuron<?, ?, ?> neuron) {
		
		queue.addAtBegin(neuron);
		
		while (queue.containsAny()) {
			queue.removeAndGetRefLast().trigger(this);
		}
	}
	
	//method
	/**
	 * Adds the given neuron to this trigger queue.
	 * 
	 * @param neuron
	 * @throws NullArgumentException if the given neuron is null.
	 */
	public void addNeuron(final TriggerableNeuronoid neuron) {
		queue.addAtBegin(neuron.getRefNeuron());
	}
}
