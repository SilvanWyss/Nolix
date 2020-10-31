//package declaration
package ch.nolix.system.neuronalNet;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.baseNeuron.BaseNeuron;
import ch.nolix.system.baseNeuron.BundleNeuron;
import ch.nolix.system.baseNeuron.FanoutNeuron;

//class
/**
 * A neuronal net is a neuron that contains neurons that are connected to a network.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 120
 * @param <IO> The type of the input and output of the neurons of a neuronal net.
 */
public final class NeuronalNet<IO>
extends BaseNeuron<NeuronalNet<IO>, Iterable<IO>, Iterable<IO>> {
	
	//constants
	private static final int MIN_INPUT_NEURON_COUNT = 1;
	private static final int MAX_INPUT_NEURON_COUNT = 1;

	//attributes
	private final FanoutNeuron<IO> inputFanoutNeuron = new FanoutNeuron<>();
	private final BundleNeuron<IO> outputBundleNeuron = new BundleNeuron<>();
	
	//constructor
	/**
	 * Creates a new neuronal net with the given input layer neurons and output layer neurons.
	 * 
	 * @param inputLayerNeurons
	 * @param internalOutputNeurons
	 * @throws ArgumentIsNullException if the given input layer neurons is null.
	 * @throws ArgumentIsNullException if the given output layer neurons is null.
	 */
	public <N extends BaseNeuron<N, IO, IO>> NeuronalNet(
		final Iterable<N> inputLayerNeurons,
		final Iterable<N> outputLayerNeurons
	) {
		//Asserts that the given input layer neurons is not null.
		Validator
		.assertThat(inputLayerNeurons)
		.thatIsNamed("input layer neurons")
		.isNotNull();
		
		//Asserts that the given output layer neurons is not null.
		Validator
		.assertThat(outputLayerNeurons)
		.thatIsNamed("output layer neurons")
		.isNotNull();
		
		//Connects the input fanout neuron of this neuronal net to the given input layer neurons.
		int i = 1;
		for (N iln: inputLayerNeurons) {

			//Connects the input fanout neuron of this neuronal net to the current input layer neuron.
			iln.addInputNeuron(inputFanoutNeuron.getRefFanNeuron(i));
			
			//Increments the index.
			i++;
		}
		
		//Connects the output bundle neuron of this neuronal net to the given output layer neurons.
		outputLayerNeurons.forEach(oln -> outputBundleNeuron.addInputNeuron(oln));
	}
	
	//method
	/**
	 * @return the maximum number of input neurons of this neuronal net.
	 */
	@Override
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this neuronal net.
	 */
	@Override
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}
	
	//method
	/**
	 * @return the neurons of this neuronal net.
	 */
	public LinkedList<BaseNeuron<?, ?, ?>> getRefNeurons() {
	
		final var neurons = new LinkedList<BaseNeuron<?, ?, ?>>();
		neurons.addAtEnd(inputFanoutNeuron.getRefFanNeurons());
		
		for (final BaseNeuron<?, ?, ?> n : neurons) {
			for (final BaseNeuron<?, ?, ?> on : n.getRefOutputNeurons()) {
				
				//Handles the case that the neurons does not contains already the current output neuron.
				if (!neurons.contains(on)) {
					neurons.addAtEnd(on);
				}
			}
		}
		
		neurons.removeFirst(inputFanoutNeuron.getRefFanNeurons());
		neurons.removeFirst(outputBundleNeuron);
		
		return neurons;
	}

	//method
	/**
	 * @return the neurons of this neuronal net
	 * as neurons whose inputs and output are of the same type as the inputs and output of this neuronal net.
	 */
	@SuppressWarnings("unchecked")
	public <N extends BaseNeuron<N, IO, IO>> LinkedList<N> getRefNeuronsAsTyped() {
		return getRefNeurons().to(e -> (N)e);
	}
	
	//method
	/**
	 * Lets this neuronal net fire.
	 */
	@Override
	public void fire() {
		inputFanoutNeuron.clearInputNeurons();
		inputFanoutNeuron.addInputNeuron(getRefOneInputNeuron());
		inputFanoutNeuron.fireTransitively();
		internalSetOutput(outputBundleNeuron.getRefOutput());
	}
}
