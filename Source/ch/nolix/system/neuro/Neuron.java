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
 * @lines 290
 * @param <I> - The type of the inputs of a neuron.
 * @param <O> - The type of the output of a neuron.
 * @param <N> - The type of a neuron.
 */
public abstract class Neuron<I, O, N extends Neuron<I, O, N>> {
	
	//attribute
	private O output;
	
	//multiple attributes
	private final List<InputNeuronoid<I>> inputNeurons = new List<InputNeuronoid<I>>();
	private final List<TriggerableNeuronoid> triggerableNeurons = new List<TriggerableNeuronoid>();
	
	//method
	/**
	 * Adds the given input neuron to this neuron.
	 * An input neuron is a neuron this neuron can take its input from.
	 * 
	 * @param inputNeuron
	 * @return this neuron.
	 * @throws NullArgumentException if the given input neuron is null.
	 * @throws NonSmallerArgumentException if this neuron has reached its maximal number of input neurons.
	 * @throws RuntimeException if this neuron contains already the given input neuron.
	 */
	public final N addInputNeuron(final Neuron<?, I, ?> inputNeuron) {
		return addInputNeuron(new InputNeuronoid<I>(inputNeuron));
	}
	
	//method
	/**
	 * Adds the given input neuron to this neuron.
	 * An input neuron is a neuron this neuron can take its input from.
	 * 
	 * @param inputNeuron
	 * @return this neuron.
	 * @throws NullArgumentException if the given input neuron is null.
	 * @throws NonSmallerArgumentException if this neuron has reached its maximal number of input neurons.
	 * @throws RuntimeException if this neuron contains already the given input neuron.
	 */
	@SuppressWarnings("unchecked")
	public final N addInputNeuron(final InputNeuronoid<I> inputNeuron) {
		
		//Checks if the given input neuron is not null.
		ZetaValidator.supposeThat(inputNeuron).thatIsNamed("input neuron").isNotNull();
		
		//Checks if this neuron has not reached its maximal number of input neurons.
		ZetaValidator.supposeThat(getInputNeuronCount()).isSmallerThan(getMaxInputNeuronCount());
				
		inputNeurons.addAtEndRegardingSingularity(inputNeuron);
		
		return (N)this;
	}
	
	//method
	/**
	 * Adds the given input neuron with the given weight to this neuron.
	 * An input neuron is a neuron this neuron can take its input from.
	 * 
	 * @param weight
	 * @param inputNeuron
	 * @return this neuron.
	 * @throws NullArgumentException if the given input neuron is null.
	 * @throws NonSmallerArgumentException if this neuron has reached its maximal number of input neurons.
	 * @throws RuntimeException if this neuron contains already the given input neuron.
	 */
	@SuppressWarnings("unchecked")
	public final N addInputNeuron(final double weight, final Neuron<?, I, ?> inputNeuron) {
		
		addInputNeuron(new InputNeuronoid<I>(weight, inputNeuron));
		
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
	public final N addTriggeringNeuron(final Neuron<?, ?, ?> triggeringNeuron) {	
		
		triggeringNeuron.addTriggerableNeuron(this);
		
		return (N)this;
	}
	
	//method
	/**
	 * Removes all input neurons of this neuron.
	 * 
	 * @return this neuron.
	 * @throws UnequalArgumentException if the minimal number of input neurons of this neuron is not 0.
	 */
	@SuppressWarnings("unchecked")
	public final N clearInputNeurons() {
		
		//Checks if the minimal number of input neurons of this neuron is 0.
		ZetaValidator.supposeThat(getMinInputNeuronCount()).thatIsNamed("minimal number of input neurons").isZero();
		
		inputNeurons.clear();
		
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
	 * @throws NonSmallerArgumentException if this neuron has reached its minimal number of input neurons.
	 */
	@SuppressWarnings("unchecked")
	public final N removeInputNeuron(final Neuron<?, ?, ?> inputNeuron) {
		
		//Checks if this neuron has not reached its minimal number of input neurons.
		ZetaValidator.supposeThat(getInputNeuronCount()).isSmallerThan(getMinInputNeuronCount());
		
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
	public final N removeTriggeringNeuron(final Neuron<?, ?, ?> triggeringNeuron) {
		
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
	 * @return the number of input neurons of this neuron.
	 */
	protected final int getInputNeuronCount() {
		return inputNeurons.getSize();
	}
	
	//abstract method
	/**
	 * @return the maximal number of input neurons of this neuron.
	 */
	protected abstract int getMaxInputNeuronCount();
	
	//abstract method
	/**
	 * @return the minimal number of input neurons of this neuron.
	 */
	protected abstract int getMinInputNeuronCount();
	
	//method
	/**
	 * @return the input neurons of this neuron.
	 */
	protected final IContainer<InputNeuronoid<I>> getRefInputNeurons() {
		return inputNeurons;
	}
	
	//method
	/**
	 * @return the inputs of this neuron, what are the outputs of the input neurons of this neuron.
	 */
	protected final List<I> getRefInputs() {
		return inputNeurons.toContainer(in -> in.getRefInput());
	}
	
	//method
	/**
	 * @return the one input of this neuron.
	 * @throws Exception if this neuron has no or several inputs.
	 */
	protected final I getRefOneInput() {
		return inputNeurons.getRefOne().getRefInput();
	}
	
	//method
	/**
	 * @return the one input neuron of this neuron
	 * @throws RuntimeException if this neuron contains no or several input neurons.
	 */
	protected final InputNeuronoid<I> getRefOneInputNeuron() {
		return inputNeurons.getRefOne();
	}
	
	//method
	/**
	 * @return the triggerable neurons of this neurons, what are the neurons this neuron can trigger.
	 */
	protected final IContainer<TriggerableNeuronoid> getRefTriggerableNeurons() {
		return triggerableNeurons;
	}
	
	//method
	/**
	 * Sets the output of this neuron.
	 * 
	 * @param output
	 */
	protected void setOutput(final O output) {
		this.output = output;
	}
	
	//abstract method
	/**
	 * Triggers this neuron using the given processor.
	 * 
	 * @param processor
	 */
	protected abstract void trigger(final Processor processor);
	
	//method
	/**
	 * Adds the given triggerable neuron to this neuron.
	 * 
	 * @param triggerableNeuron
	 * @throws NullArgumentException if the given triggerable neuron is null.
	 * @throws RuntimeException if this neuron contains already the given triggerable neuron.
	 */
	private void addTriggerableNeuron(Neuron<?, ?, ?> triggerableNeuron) {
		
		//Checks if the given triggerable neuron is not null.
		ZetaValidator.supposeThat(triggerableNeuron).thatIsNamed("triggerable neuron").isNotNull();
		
		//Checks if this neuron does not contain the given triggerable neuron.
		if (triggerableNeurons.contains(in -> in.hasNeuron(triggerableNeuron))) {	
			throw new RuntimeException("Neuron contains already the given triggerable neuron.");
		}
		
		triggerableNeurons.addAtEnd(new TriggerableNeuronoid(triggerableNeuron));
	}
	
	//method
	/**
	 * Removes the given triggerable neuron from this neuron.
	 * 
	 * @param triggerableNeuron
	 */
	private void removeTriggerableNeuron(final Neuron<?, ?, ?> triggerableNeuron) {
		triggerableNeurons.removeFirst(tn -> tn.hasNeuron(triggerableNeuron));
	}
}
