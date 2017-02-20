//package declaration
package ch.nolix.common.neuron;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.List;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.ErrorPredicate;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.zetaValidator.ZetaValidator;

//abstract class
/**
 * This class represents a neuron.
 * A neuron has several input neurons that all provides an input of a certain type.
 * A neuron provides an output of a certain type.
 * A neuron can be triggered to calculate and update its output and to trigger other neurons.
 * 
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
	private final List<InputConnection<I>> inputConnections = new List<InputConnection<I>>();
	private final List<TriggerableNeuronoid> triggerableNeurons = new List<TriggerableNeuronoid>();
	
	//method
	/**
	 * Adds the given input neuron to this neuron.
	 * An input neuron is a neuron this neuron can get an input from.
	 * 
	 * @param inputNeuron
	 * @return this neuron.
	 * @throws NullArgumentException if the given input neuron is null.
	 * @throws NonSmallerArgumentException if this neuron has reached its maximal number of input neurons.
	 * @throws RuntimeException if this neuron contains already the given input neuron.
	 */
	public final N addInputNeuron(final Neuron<?, I, ?> inputNeuron) {
		return addInputConnection(new InputConnection<I>(inputNeuron));
	}
	
	//method
	/**
	 * Adds the given input neuron with the given weight to this neuron.
	 * An input neuron is a neuron this neuron can get an input from.
	 * 
	 * @param weight
	 * @param inputNeuron
	 * @return this neuron.
	 * @throws NullArgumentException if the given input neuron is null.
	 * @throws NonSmallerArgumentException if this neuron has reached its maximal number of input neurons.
	 * @throws RuntimeException if this neuron contains already the given input neuron.
	 */
	public final N addInputNeuron(final double weight, final Neuron<?, I, ?> inputNeuron) {
		return addInputConnection(new InputConnection<I>(weight, inputNeuron));
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
		
		inputConnections.clear();
		
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
	 * @throws InvalidArgumentException if this neuron does not contain the given input neuron.
	 */
	@SuppressWarnings("unchecked")
	public final N removeInputNeuron(final Neuron<?, ?, ?> inputNeuron) {
		
		//Checks if this neuron has not reached its minimal number of input neurons.
		ZetaValidator.supposeThat(getInputNeuronCount()).isSmallerThan(getMinInputNeuronCount());
		
		inputConnections.removeFirst(in -> in.hasInputNeuron(inputNeuron));
		
		return (N)this;
	}
	
	//method
	/**
	 * Removes the given triggering neuron from this neuron.
	 * 
	 * @param triggeringNeuron
	 * @return this neuron.
	 * @throws InvalidArgumentException if this neuron does not contain the given triggering neuron.
	 */
	@SuppressWarnings("unchecked")
	public final N removeTriggeringNeuron(final Neuron<?, ?, ?> triggeringNeuron) {
		
		triggeringNeuron.removeTriggerableNeuron(this);
		
		return (N)this;
	}

	//method
	/**
	 * Starts triggering this neuron in background.
	 */
	public final void startTriggering() {
		Sequencer.runInBackground(() -> trigger());
	}
	
	//method
	/**
	 * Triggers this neuron.
	 */
	public final void trigger() {
		new TriggerQueue(this);
	}
	
	//method
	/**
	 * @return the number of input neurons of this neuron.
	 */
	protected final int getInputNeuronCount() {
		return inputConnections.getSize();
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
	 * @return the input connections of this neuron.
	 */
	protected final IContainer<InputConnection<I>> getRefInputConnections() {
		return inputConnections;
	}
	
	//method
	/**
	 * @return the inputs of this neuron.
	 */
	protected final IContainer<I> getRefInputs() {
		return inputConnections.toContainer(ic -> ic.getRefInput());
	}
	
	//method
	/**
	 * @return the one input of this neuron.
	 * @throws InvalidArgumentException if this neuron contains no or several inputs.
	 */
	protected final I getRefOneInput() {
		return getRefOneInputConnection().getRefInput();
	}
	
	//method
	/**
	 * @return the one input connection of this neuron.
	 * @throws InvalidArgumentException if this neuron contains no or several input connections.
	 */
	protected final InputConnection<I> getRefOneInputConnection() {
		return inputConnections.getRefOne();
	}
	
	//method
	/**
	 * The triggerable neurons of a neuron are the neurons the neuron can trigger.
	 * 
	 * @return the triggerable neurons of this neuron.
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
	protected abstract void trigger(final TriggerQueue processor);
	
	//package-visible method
	/**
	 * @return the input neurons of this neuron.
	 */
	final IContainer<Neuron<?, I, ?>> getRefInputNeurons() {
		return inputConnections.toContainer(ic -> ic.getRefInputNeuron());
	}
	
	//package-visible method
	/**
	 * @return the one input neuron of this neuron.
	 * @throws InvalidArgumentException if this neuron contains no or several input neurons.
	 */
	final Neuron<?, I, ?> getRefOneInputNeuron(){
		return inputConnections.getRefOne().getRefInputNeuron();
	}
	
	//method
	/**
	 * Adds the given input connection to this neuron.
	 * 
	 * @param inputConnection
	 * @return this neuron.
	 * @throws NullArgumentException if the given input connection is null.
	 * @throws NonSmallerArgumentException if this neuron has reached its maximal number of input neurons.
	 * @throws InvalidArgumentException if this neuron contains already the input neuron of the given input connection.
	 */
	@SuppressWarnings("unchecked")
	private final N addInputConnection(final InputConnection<I> inputConnection) {
		
		//Checks if the given input neuron is not null.
		ZetaValidator.supposeThat(inputConnection).thatIsNamed("input neuron").isNotNull();
		
		//Checks if this neuron has not reached its maximal number of input neurons.
		ZetaValidator.supposeThat(getInputNeuronCount()).isSmallerThan(getMaxInputNeuronCount());
		
		//Checks if this neuron does not contain the input neuron of the given input connection.
		if (inputConnections.contains(ic -> ic.hasInputNeuron(inputConnection.getRefInputNeuron()))) {
			throw new InvalidArgumentException(
				new Argument(inputConnection),
				new ErrorPredicate("is invalid because the neuron contains already its input neuron")
			);
		}
		
		inputConnections.addAtEnd(inputConnection);
		
		return (N)this;
	}
	
	//method
	/**
	 * Adds the given triggerable neuron to this neuron.
	 * A triggerable neuron of a origin neuron is a neuron that can trigger the origin neuron.
	 * 
	 * @param triggerableNeuron
	 * @throws NullArgumentException if the given triggerable neuron is null.
	 * @throws InvalidArgumentException if this neuron contains already the given triggerable neuron.
	 */
	private void addTriggerableNeuron(Neuron<?, ?, ?> triggerableNeuron) {
		
		//Checks if the given triggerable neuron is not null.
		ZetaValidator.supposeThat(triggerableNeuron).thatIsNamed("triggerable neuron").isNotNull();
		
		//Checks if this neuron does not contain the given triggerable neuron.
		if (triggerableNeurons.contains(in -> in.hasNeuron(triggerableNeuron))) {
			throw new InvalidArgumentException(
				new Argument(triggerableNeuron),
				new ErrorPredicate("is already contained in the neuron")
			);
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
