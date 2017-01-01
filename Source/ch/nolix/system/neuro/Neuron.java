//package declaration
package ch.nolix.system.neuro;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.List;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.zetaValidator.ZetaValidator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 190
 * @param <O> - The type of the inputs and the output of a neuron.
 * @param <N> - The concrete type of a neuron.
 */
public abstract class Neuron<O, N extends Neuron<O, N>> {
	
	//attribute
	private O output;
	
	//multiple attributes
	private final List<InputNeuronoid<O>> inputNeurons = new List<InputNeuronoid<O>>();
	private final List<TriggerableNeuronoid<O, N>> triggerableNeurons = new List<TriggerableNeuronoid<O, N>>();
	
	//method
	/**
	 * Adds the given input neuron to this neuron.
	 * An input neuron is a neuron this neuron can take its input from.
	 * 
	 * @param inputNeuron
	 * @return this neuron.
	 * @throws NullArgumentException if the given input neuron is null.
	 * @throws RuntimeException if this neuron contains already the given input neuron.
	 */
	@SuppressWarnings("unchecked")
	public final N addInputNeuron(final Neuron<O, ?> inputNeuron) {
		
		//Checks if the given input neuron is not null.
		ZetaValidator.supposeThat(inputNeuron).thatIsNamed("input neuron").isNotNull();
		
		//Checks if this neuron does not contain the given input neuron.
		if (inputNeurons.contains(in -> in.hasNeuron(inputNeuron))) {	
			throw new RuntimeException("Neuron contains already the given input neuron.");
		}
		
		inputNeurons.addAtEnd(new InputNeuronoid<O>(inputNeuron));
		
		return (N)this;
	}
	
	//method
	/**
	 * Adds the given triggering neuron to this neuron.
	 * A triggering neuron is a neuron that can trigger this neuron.
	 * 
	 * @param triggeringNeuron
	 * @return this neuron.
	 * @throws NullArgumentException if the given triggering neuron is null.
	 * @throws RuntimeException if this neuron contains already the given triggering neuron.
	 */
	@SuppressWarnings("unchecked")
	public final N addTriggeringNeuron(final Neuron<O, N> triggeringNeuron) {	
		
		triggeringNeuron.addTriggerableNeuron(this);
		
		return (N)this;
	}
	
	//method
	/**
	 * @return the output of this neuron.
	 */
	public final O getRefOutput() {
		return output;
	}
	
	//method
	/**
	 * Removes the given input neuron from this neuron.
	 * 
	 * @param inputNeuron
	 * @return this neuron.
	 */
	@SuppressWarnings("unchecked")
	public final N removeInputNeuron(final Neuron<O, N> inputNeuron) {
		
		inputNeurons.removeFirst(in -> in.hasNeuron(inputNeuron));
		
		return (N)this;
	}
	
	//method
	/**
	 * Removes the given triggering neuron from this neuron.
	 * 
	 * @param triggeringNeuron
	 * @return this neuron.
	 */
	@SuppressWarnings("unchecked")
	public final N removeTriggeringNeuron(final Neuron<O, N> triggeringNeuron) {
		
		triggeringNeuron.removeTriggerableNeuron(this);
		
		return (N)this;
	}

	//method
	/**
	 * Starts triggering this neuron.
	 */
	public final void startTriggering() {
		Sequencer.runInBackground(() -> trigger());
	}
	
	//method
	/**
	 * Triggers this neuron.
	 */
	public final void trigger() {
		new Processor(this);
	}
	
	//method
	/**
	 * @return the input neurons of this neuron.
	 */
	protected final IContainer<InputNeuronoid<O>> getRefInputNeurons() {
		return inputNeurons;
	}
	
	//method
	/**
	 * @return the inputs of this neuron, what are the outputs of the input neurons of this neuron.
	 */
	protected final IContainer<O> getRefInputs() {
		return inputNeurons.toContainer(in -> in.getRefInput());
	}
	
	//method
	/**
	 * @return the triggerable neurons of this neurons, what are the neurons this neuron can trigger.
	 */
	protected final IContainer<TriggerableNeuronoid<O, N>> getRefTriggerableNeurons() {
		return triggerableNeurons;
	}
	
	//method
	/**
	 * Sets the output of this neuron.
	 * 
	 * @param output
	 */
	protected final void setOutput(final O output) {
		this.output = output;
	}
	
	//abstract method
	/**
	 * Triggers this neuron using the given processor.
	 * 
	 * @param processor
	 */
	abstract void trigger(final Processor processor);
	
	//method
	/**
	 * Adds the given triggerable neuron to this neuron.
	 * 
	 * @param triggerableNeuron
	 * @throws NullArgumentException if the given triggerable neuron is null.
	 * @throws RuntimeException if this neuron contains already the given triggerable neuron.
	 */
	private void addTriggerableNeuron(Neuron<O, N> triggerableNeuron) {
		
		//Checks if the given triggerable neuron is not null.
		ZetaValidator.supposeThat(triggerableNeuron).thatIsNamed("triggerable neuron").isNotNull();
		
		//Checks if this neuron does not contain the given triggerable neuron.
		if (triggerableNeurons.contains(in -> in.hasNeuron(triggerableNeuron))) {	
			throw new RuntimeException("Neuron contains already the given triggerable neuron.");
		}
		
		triggerableNeurons.addAtEnd(new TriggerableNeuronoid<O, N>(triggerableNeuron));
	}
	
	//method
	/**
	 * Removes the given triggerable neuron from this neuron.
	 * 
	 * @param triggerableNeuron
	 */
	private void removeTriggerableNeuron(final Neuron<O, N> triggerableNeuron) {
		triggerableNeurons.removeFirst(tn -> tn.hasNeuron(triggerableNeuron));
	}
}
