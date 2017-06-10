//package declaration
package ch.nolix.system.neuron;

//Java import
import java.util.Iterator;



//own imports

import ch.nolix.core.container.List;
import ch.nolix.core.sequencer.Sequencer;

//class
/**
 * A fanout neuron is a neuron that:
 * -Has a container of elements as input.
 * -Can create output neurons that have a single element of this container as output.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 80
 * @param <O> - The type of the elements of the input container and output container of a fanout neuron.
 */
public final class  FanoutNeuron<O>
extends Neuron<Iterable<O>, Iterable<O>, FanoutNeuron<O>> {

	//attribute
	private final List<TransformNeuron<Iterable<O>, O>> outputNeurons = new List<TransformNeuron<Iterable<O>, O>>();
	
	//method
	/**
	 * @param index
	 * @return the output neuron of this fanout neuron that returns the element at the given index from the input container of this fanout neuron.
	 */
	public TransformNeuron<Iterable<O>, O> getRefOutputNeuron(final int index) {
		
		//Handles the case if the output neuron for the element at the given index exists already.
		if (outputNeurons.getElementCount() >= index) {
			return outputNeurons.getRefAt(index);
		}
		
		//Handles the case if the output neuron for the element at the given index does not exist yet.
		outputNeurons.addAtEnd(
			new TransformNeuron<Iterable<O>, O>(
				this,
				n -> {
					final Iterator<O> iterator = n.iterator();
					Sequencer.forCount(index - 1).run(() -> iterator.next());	
					return iterator.next();
				}
			)
		);
		
		return outputNeurons.getRefLast();
	}
	
	//method
	/**
	 * @param neuron
	 * @return true if this fanout neurons contains the given output neuron.
	 */
	public boolean containsOutputNeuron(final Neuron<?, ?, ?> neuron) {
		return outputNeurons.contains(neuron);
	}
	
	//method
	/**
	 * @return the maximum number of input neurons of this fanout neuron.
	 */
	protected int getMaxInputNeuronCount() {
		return 1;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this fanout neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 0;
	}

	//method
	/**
	 * Triggers this fanout neuron using the given processor.
	 * 
	 * @param processor
	 */
	protected void trigger(final TriggerQueue processor) {
		setOutput(getRefOneInput());
		outputNeurons.forEach(on -> on.trigger());
	}	
}
