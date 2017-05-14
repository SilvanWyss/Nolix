//package declaration
package ch.nolix.core.neuron;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.validator2.ZetaValidator;

//abstract class
/**
 * A neuronal net is a neuron that contains neurons that are connected together to a network.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 180
 * @param <IO> - The type of the input and output of the neurons of this neuronal net.
 */
public final class NeuronalNet<IO>
extends Neuron<Iterable<IO>, Iterable<IO>, NeuronalNet<IO>> {

	//attributes
	private final FanoutNeuron<IO> inputFanoutNeuron = new FanoutNeuron<IO>();
	private final BundleNeuron<IO> outputBundleNeuron = new BundleNeuron<IO>();
	private final Neuron<IO, IO, ?> triggerableStartNeuron;
	
	private final Iterable<Neuron<IO, IO, ?>> internalOutputNeurons;
	
	//constructor
	/**
	 * Creates new neuronal net with the given input layer neurons, output layer neurons and triggerable start neuron.
	 * 
	 * @param inputLayerNeurons
	 * @param internalOutputNeurons
	 * @param triggerableStartNeuron
	 * @throws NullArgumentException if the given input layer neuron container is null.
	 * @throws NullArgumentException if the given output layer neuron container is null.
	 * @throws NullArgumentException if the given triggerable start neuron is null.
	 */
	@SuppressWarnings("unchecked")
	public <M extends Neuron<IO, IO, M>> NeuronalNet(
		final Iterable<M> inputLayerNeurons,
		final Iterable<M> outputLayerNeurons,
		final M triggerableStartNeuron
	) {
		//Checks if the given input layer neuron container and output layer neuron container is not null.
		ZetaValidator.supposeThat(inputLayerNeurons).thatIsNamed("input layer neuron container").isNotNull();
		ZetaValidator.supposeThat(outputLayerNeurons).thatIsNamed("output layer neuron container").isNotNull();
		
		//Checks if the given triggerable start neuron is not null.
		ZetaValidator.supposeThat(triggerableStartNeuron).thatIsNamed("triggerable start neuron").isNotNull();
		
		//Connects the input fanout neuron of this neuronal net to the given input layer neurons.
		int i = 1;
		for (M iln: inputLayerNeurons) {

			//Connects the input fanout neuron of this neuronal net to the current input layer neuron.
			iln.addInputNeuron(inputFanoutNeuron.getRefOutputNeuron(i));
			
			i++;
		}

		
		//Connects the output bundle neuron of this neuronal net to the given output layer neurons.
		outputLayerNeurons.forEach(oln -> outputBundleNeuron.addInputNeuron(oln));
		
		//Sets the triggerable start neuron of this neuronal net.
		this.triggerableStartNeuron = triggerableStartNeuron;
		
		this.internalOutputNeurons = (Iterable<Neuron<IO, IO, ?>>)outputLayerNeurons;
	}
	
	//method
	/**
	 * @return the neurons of this neuronal net.
	 */
	public List<Neuron<?, ?, ?>> getRefNeurons() {
		final List<Neuron<?, ?, ?>> neurons = new List<Neuron<?, ?, ?>>();
		internalOutputNeurons.forEach(on -> fillUpInputNeuronsRecursively(on, neurons));
		return neurons;
	}

	//method
	/**
	 * @return the neurons of this neuronal net.
	 */
	@SuppressWarnings("unchecked")
	public <N extends Neuron<IO, IO, N>> List<N> getRefNeuronsOfInputOutputType() {
		
		final List<N> neurons = new List<N>();
		
		//Adds the suitable internal output neurons of this neuronal net.
		for (Neuron<IO, IO, ?> on : internalOutputNeurons) {
			try {
				final N outputNeuron = (N)on;
				fillUpInputNeuronsOfInputOutputTypeRecursively(outputNeuron, neurons);
			}
			catch (Exception e) {} //Swallows any exception.
		}
		
		return neurons;
	}
	
	//method
	/**
	 * @return the maximum number of input neurons of this neuronal net.
	 */
	protected int getMaxInputNeuronCount() {
		return 1;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this neuronal net.
	 */
	protected int getMinInputNeuronCount() {
		return 1;
	}
	
	//method
	/**
	 * Triggers this neuronal net using the given processor.
	 * 
	 * @param processor
	 */
	protected void trigger(final TriggerQueue processor) {
		
		inputFanoutNeuron.clearInputNeurons();
		inputFanoutNeuron.addInputNeuron(getRefOneInputNeuron());
		inputFanoutNeuron.trigger();
		triggerableStartNeuron.trigger();
		outputBundleNeuron.trigger();
		setOutput(outputBundleNeuron.getRefOutput());
		
		getRefTriggerableNeurons().forEach(tn -> processor.addNeuron(tn));
	}
	
	//method
	/**
	 * Fills up the given neuron container with the input neurons of the given neuron.
	 * 
	 * @param neuron
	 * @param neurons
	 */
	private void fillUpInputNeuronsRecursively(
		final Neuron<?, ?, ?> neuron,
		final List<Neuron<?, ?, ?>> neurons
	) {
		if (!neurons.contains(neuron) && !isInternalInputNeuron(neuron)) {
			neurons.addAtEnd(neuron);
			neuron.getRefInputNeurons().forEach(in -> fillUpInputNeuronsRecursively(in, neurons));
		}
	}
	
	//method
	/**
	 * Fills up the given neuron container with the suitable input neurons of the given neuron.
	 * 
	 * @param neuron
	 * @param neurons
	 */
	@SuppressWarnings("unchecked")
	private <N extends Neuron<IO, IO, N>> void fillUpInputNeuronsOfInputOutputTypeRecursively(
		final N neuron,
		final List<N> neurons
	) {
		if (!neurons.contains(neuron) && !isInternalInputNeuron(neuron)) {
			neurons.addAtEnd(neuron);
			
			//Iterates the input neurons of the given neuron.
			for (Neuron<?, IO, ?> in: neuron.getRefInputNeurons()) {
				
				//Adds the suitable input neurons of the given neuron to the given neuron container.
				try {
					final N n = (N)in;
					this.fillUpInputNeuronsOfInputOutputTypeRecursively(n, neurons);
				}
				catch(Exception e) {} //Swallows any exception and continue.
			}
		}
	}
	
	//method
	/**
	 * @param neuron
	 * @return true if the given neuron is an internal input neuron.
	 */
	private boolean isInternalInputNeuron(final Neuron<?, ?, ?> neuron) {	
		return inputFanoutNeuron.containsOutputNeuron(neuron);
	}
}
