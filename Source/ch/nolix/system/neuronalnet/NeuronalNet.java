//package declaration
package ch.nolix.system.neuronalnet;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.baseneuron.BaseNeuron;
import ch.nolix.system.baseneuron.BundleNeuron;
import ch.nolix.system.baseneuron.FanoutNeuron;

//class
/**
 * A {@link NeuronalNet} is a {@link BaseNeuron} that contains {@link BaseNeuron}s that are connected to a network.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 130
 * @param <IO> The type of the input and output of the {@link BaseNeuron}s of a {@link NeuronalNet}.
 */
public final class NeuronalNet<IO> extends BaseNeuron<NeuronalNet<IO>, Iterable<IO>, Iterable<IO>> {
	
	//constants
	private static final int MIN_INPUT_NEURON_COUNT = 1;
	private static final int MAX_INPUT_NEURON_COUNT = 1;

	//attributes
	private final FanoutNeuron<IO> inputFanoutNeuron = new FanoutNeuron<>();
	private final BundleNeuron<IO> outputBundleNeuron = new BundleNeuron<>();
	
	//constructor
	/**
	 * Creates a new {@link NeuronalNet} with the given input layer neurons and output layer neurons.
	 * 
	 * @param inputLayerNeurons
	 * @param outputLayerNeurons
	 * @param <N> is the type of the inputLayerNeurons and outputLayerNeurons.
	 * @throws ArgumentIsNullException if the given inputLayerNeurons is null.
	 * @throws ArgumentIsNullException if the given outputLayerNeurons is null.
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
		
		//Connects the input fanout neuron of the current NeuronalNet to the given input layer neurons.
		int i = 1;
		for (N iln: inputLayerNeurons) {

			//Connects the input fanout neuron of the current NeuronalNet to the current input layer neuron.
			iln.addInputNeuron(inputFanoutNeuron.getRefFanNeuron(i));
			
			//Increments the index.
			i++;
		}
		
		//Connects the output bundle neuron of the current NeuronalNet to the given output layer neurons.
		outputLayerNeurons.forEach(outputBundleNeuron::addInputNeuron);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}
	
	//method
	/**
	 * @return the neurons of the current {@link NeuronalNet}.
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
	 * @param <N> is the type of the returned {@link BaseNeuron}s.
	 * @return the neurons of the current {@link NeuronalNet}
	 * as neurons whose inputs and output are of the same type as the inputs and output of the current {@link NeuronalNet}.
	 */
	@SuppressWarnings("unchecked")
	public <N extends BaseNeuron<N, IO, IO>> LinkedList<N> getRefNeuronsAsTyped() {
		return getRefNeurons().to(e -> (N)e);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void internalUpdate() {
		inputFanoutNeuron.clearInputNeurons();
		inputFanoutNeuron.addInputNeuron(getRefOneInputNeuron());
		inputFanoutNeuron.fire();
		internalSetOutput(outputBundleNeuron.getRefOutput());
	}
}
