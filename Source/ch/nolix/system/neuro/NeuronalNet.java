//package declaration
package ch.nolix.system.neuro;

//own import
import ch.nolix.common.zetaValidator.ZetaValidator;

//abstract class
/**
 * A neuronal net is a neuron that contains neurons that are connected together to a network.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 90
 * @param <IO> - The type of the input and output of the neurons of this neuronal net.
 */
public final class NeuronalNet<IO>
extends Neuron<Iterable<IO>, Iterable<IO>, NeuronalNet<IO>> {

	//attributes
	private final FanoutNeuron<IO> inputFanoutNeuron = new FanoutNeuron<IO>();
	private final BundleNeuron<IO> outputBundleNeuron = new BundleNeuron<IO>();
	private final Neuron<IO, IO, ?> triggerableStartNeuron;
	
	//constructor
	/**
	 * Creates new neuronal net with the given input layer neurons, output layer neurons and triggerable start neuron.
	 * 
	 * @param inputLayerNeurons
	 * @param outputNeurons
	 * @param triggerableStartNeuron
	 * @throws NullArgumentException if the given input layer neuron container is null.
	 * @throws NullArgumentException if the given output layer neuron container is null.
	 * @throws NullArgumentException if the given triggerable start neuron is null.
	 */
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
		inputFanoutNeuron.addTriggeringNeuron(this);

		
		//Connects the output bundle neuron of this neuronal net to the given output layer neurons.
		outputLayerNeurons.forEach(oln -> outputBundleNeuron.addInputNeuron(oln));
		
		//Sets the triggerable start neuron of this neuronal net.
		this.triggerableStartNeuron = triggerableStartNeuron;
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
	protected void trigger(final Processor processor) {
		
		inputFanoutNeuron.clearInputNeurons();
		inputFanoutNeuron.addInputNeuron(getRefOneInputNeuron());
		inputFanoutNeuron.trigger();
		triggerableStartNeuron.trigger();
		outputBundleNeuron.trigger();
		setOutput(outputBundleNeuron.getRefOutput());
		
		getRefTriggerableNeurons().forEach(tn -> processor.addNeuronToTrigger(tn));
	}
}
