//package declaration
package ch.nolix.system.neuronoid;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator2.Validator;

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
public abstract class Neuronoid<I, O, N extends Neuronoid<I, O, N>> {
	
	//attribute
	private O output;
	
	//multiple attributes
	private final List<InputConnection<I>> inputConnections = new List<InputConnection<I>>();
	private final List<Neuronoid<O, ?, ?>> outputNeurons = new List<Neuronoid<O, ?, ?>>();
	
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
	public final N addInputNeuron(final Neuronoid<?, I, ?> inputNeuron) {
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
	public final N addInputNeuron(final double weight, final Neuronoid<?, I, ?> inputNeuron) {
		return addInputConnection(new InputConnection<I>(weight, inputNeuron));
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
		Validator.suppose(getMinInputNeuronCount()).thatIsNamed("minimal number of input neurons").isZero();
		
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
	public final N removeInputNeuron(final Neuronoid<?, ?, ?> inputNeuron) {
		
		//Checks if this neuron has not reached its minimal number of input neurons.
		Validator.suppose(getInputNeuronCount()).isSmallerThan(getMinInputNeuronCount());
		
		inputConnections.removeFirst(in -> in.hasInputNeuron(inputNeuron));
		
		return (N)this;
	}

	//method
	/**
	 * Starts triggering this neuron in background.
	 */
	public final void startFiring() {
		Sequencer.runInBackground(() -> fire());
	}
	
	//method
	/**
	 * Lets this neuron fire.
	 */
	public void fire() {
		final List<Neuronoid<?, ?, ?>> nextNeurons = new List<Neuronoid<?, ?, ?>>(this);
		final List<Neuronoid<?, ?, ?>> visitedNeurons = new List<Neuronoid<?, ?, ?>>();
		while (nextNeurons.containsAny()) {
			nextNeurons.removeAndGetRefFirst().fire(nextNeurons, visitedNeurons);
		}
	}
	
	private void fire(List<Neuronoid<?, ?, ?>> nextNeurons, List<Neuronoid<?, ?, ?>> visitedNeurons) {
		if (!visitedNeurons.contains(this)) {
			internal_fire();
			visitedNeurons.addAtEnd(this);
			final List<Neuronoid<O, ?, ?>> x = outputNeurons.getRefSelected(n -> !visitedNeurons.contains(n));
			x.forEach(n -> nextNeurons.addAtEnd(n));
		}
	}
	
	protected abstract void internal_fire();
	
	//method
	/**
	 * @return the number of input neurons of this neuron.
	 */
	protected final int getInputNeuronCount() {
		return inputConnections.getElementCount();
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
		return inputConnections.to(ic -> ic.getRefInput());
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
	//protected abstract void trigger(final TriggerQueue processor);
	
	//package-visible method
	/**
	 * @return the input neurons of this neuron.
	 */
	final IContainer<Neuronoid<?, I, ?>> getRefInputNeurons() {
		return inputConnections.to(ic -> ic.getRefInputNeuron());
	}
	
	//package-visible method
	/**
	 * @return the one input neuron of this neuron.
	 * @throws InvalidArgumentException if this neuron contains no or several input neurons.
	 */
	final Neuronoid<?, I, ?> getRefOneInputNeuron(){
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
		Validator.suppose(inputConnection).thatIsNamed("input neuron").isNotNull();
		
		//Checks if this neuron has not reached its maximal number of input neurons.
		Validator.suppose(getInputNeuronCount()).isSmallerThan(getMaxInputNeuronCount());
		
		//Checks if this neuron does not contain the input neuron of the given input connection.
		if (inputConnections.contains(ic -> ic.hasInputNeuron(inputConnection.getRefInputNeuron()))) {
			throw new InvalidArgumentException(
				new Argument(inputConnection),
				new ErrorPredicate("is invalid because the neuron contains already its input neuron")
			);
		}
		
		inputConnections.addAtEnd(inputConnection);
		
		inputConnection.getRefInputNeuron().outputNeurons.addAtEnd(this);
		
		return (N)this;
	}
}
