/*
 * file:	Processor.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	30
 */

//package declaration
package ch.nolix.system.neuro;

//own import
import ch.nolix.common.container.List;

//package-visible class
final class Processor {

	//multiple attribute
	private final List<Neuron<?, ?>> queue = new List<Neuron<?, ?>>();
	
	//constructor
	public Processor(final Neuron<?, ?> neuron) {
		queue.addAtBegin(neuron);
		while (queue.containsAny()) {
			queue.getRefLast().trigger(this);
			queue.removeLast();
		}
	}
	
	public void addNeuronToTrigger(final TriggerableNeuronoid<?, ?> neuron) {
		queue.addAtBegin(neuron.neuron);
	}
}
