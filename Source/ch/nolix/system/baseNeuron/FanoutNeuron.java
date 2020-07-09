//package declaration
package ch.nolix.system.baseNeuron;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;

//class
/**
 * A fanout neuron is a neuron that:
 * -Has a container of elements as input.
 * -Can create fan neurons that have a single element as output.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 90
 * @param <O> The type of the elements of the input and of the elements of the output of a fanout neuron.
 */
public final class FanoutNeuron<O>
extends BaseNeuron<FanoutNeuron<O>, Iterable<O>, Iterable<O>> {
	
	//constants
	public static final int MIN_INPUT_NEURON_COUNT = 0;
	public static final int MAX_INPUT_NEURON_COUNT = 1;

	//multi-attribute
	private final LinkedList<TransformNeuron<Iterable<O>, O>> fanNeurons = new LinkedList<>();
	
	//method
	/**
	 * @param index
	 * @return the fan neurons of this fanout neuron
	 * that returns the element at the given index from the input of this fanout neuron.
	 */
	public TransformNeuron<Iterable<O>, O> getRefFanNeuron(final int index) {
		
		//Handles the case that the fan neuron for the element at the given index exists already.
		if (fanNeurons.getElementCount() >= index) {
			return fanNeurons.getRefAt(index);
		}
		
		//Handles the case that the fan neuron for the element at the given index does not exist yet.		
			fanNeurons.addAtEnd(
				new TransformNeuron<Iterable<O>, O>(
					this,
					n -> new ReadContainer<O>(n).getRefAt(index)
				)
			);
			
			return fanNeurons.getRefLast();
	}
	
	//method
	/**
	 * @param fanNeuron
	 * @return true if this fanout neurons contains the given fan neuron.
	 */
	public boolean containsFanNeuron(final BaseNeuron<?, ?, ?> fanNeuron) {
		return fanNeurons.contains(fanNeuron);
	}
	
	//method
	/**
	 * @return the maximum number of input neurons of this fanout neuron.
	 */
	@Override
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this fanout neuron.
	 */
	@Override
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}
	
	//method
	/**
	 * @return the fan neurons of this fanout neuron.
	 */
	public ReadContainer<BaseNeuron<?, ?, O>> getRefFanNeurons() {
		return new ReadContainer<>(fanNeurons);
	}

	//method
	/**
	 * Lets this neuron fire.
	 */
	@Override
	public void fire() {
		internalSetOutput(getRefOneInput());
	}
}
