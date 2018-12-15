//package declaration
package ch.nolix.system.neuronoid;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.container.List;
import ch.nolix.core.sequencer.Future;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * This class represents a neuron.
 * A neuron has several input neurons, that all provides an input of a certain type.
 * A neuron provides an output of a certain type.
 * 
 * When a neuron fires:
 * -The neuron can update its output.
 * -The neuron can update other stuff.
 * -The neuron can let fire other neurons.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 290
 * @param <I> The type of the inputs of a neuron.
 * @param <O> The type of the output of a neuron.
 * @param <N> The type of a neuron.
 */
public abstract class Neuronoid<N extends Neuronoid<N, I, O>, I, O>
implements ISmartObject<N> {
	
	//attribute
	private O output;
	
	//multiple attributes
	private final List<InputConnection<I>> inputConnections = new List<>();
	private final List<Neuronoid<?, O, ?>> outputNeurons = new List<>();
	
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
	public final N addInputNeuron(final double weight, final Neuronoid<?, ?, I> inputNeuron) {
		
		addInputConnection(new InputConnection<I>(weight, inputNeuron));
		
		return asConcreteType();
	}
	
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
	public final N addInputNeuron(final Neuronoid<?, ?, I> inputNeuron) {
		
		addInputConnection(new InputConnection<I>(inputNeuron));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes all input neurons of this neuron from this neuron.
	 * 
	 * @return this neuron.
	 * @throws UnequalArgumentException if the minimal number of input neurons of this neuron is not 0.
	 */
	public final N clearInputNeurons() {
		
		//Checks if the minimal number of input neurons of this neuron is 0.
		Validator.suppose(getMinInputNeuronCount()).thatIsNamed("minimal number of input neurons").isZero();
		
		while (getInputNeuronCount() > 0) {
			removeInputNeuron(getRefInputNeurons().getRefFirst());
		}
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Lets this neuron fire.
	 */
	public void fire() {
		
		final List<Neuronoid<?, ?, ?>> nextNeurons = new List<Neuronoid<?, ?, ?>>(this);
		final List<Neuronoid<?, ?, ?>> visitedNeurons = new List<Neuronoid<?, ?, ?>>();
				
		while (nextNeurons.containsAny()) {
			
			final Neuronoid<?, ?, ?> neuron = nextNeurons.removeAndGetRefFirst();
			visitedNeurons.addAtEnd(neuron);
			neuron.internal_fire();
			
			//Iterates the output neurons of the current neuron.
			for (final Neuronoid<?, ?, ?> on : neuron.getRefOutputNeurons()) {
				
				//Handles the case that the current output neuron has not been visited yet.
				if (!visitedNeurons.contains(on)) {
					nextNeurons.addAtEnd(on);
				}
			}
		}
	}
	
	//method
	/**
	 * Lets this neuron fire in background.
	 * 
	 * @return a new future.
	 */
	public final Future fireInBackground() {
		return Sequencer.runInBackground(() -> fire());
	}
		
	//method
	/**
	 * @return the number of input neurons of this neuron.
	 */
	public final int getInputNeuronCount() {
		return inputConnections.getSize();
	}
	
	//abstract method
	/**
	 * @return the maximal number of input neurons of this neuron.
	 */
	public abstract int getMaxInputNeuronCount();
	
	//abstract method
	/**
	 * @return the minimal number of input neurons of this neuron.
	 */
	public abstract int getMinInputNeuronCount();
	
	//method
	/**
	 * @return the input connections of this neuron.
	 */
	public final ReadContainer<InputConnection<I>> getRefInputConnections() {
		return new ReadContainer<InputConnection<I>>(inputConnections);
	}
	
	//package-visible method
	/**
	 * @return the input neurons of this neuron.
	 */
	public final ReadContainer<Neuronoid<?, ?, I>> getRefInputNeurons() {
		return new ReadContainer<>(inputConnections.to(ic -> ic.getRefInputNeuron()));
	}
	
	//method
	/**
	 * @return the inputs of this neuron.
	 */
	public final ReadContainer<I> getRefInputs() {
		return new ReadContainer<I>(inputConnections.to(ic -> ic.getRefInputNeuronOutput()));
	}
	
	//method
	/**
	 * @return the one input of this neuron.
	 * @throws EmptyStateException if this neuron does not have an input.
	 * @throws InvalidStateException if this neuron has several inputs.
	 */
	public final I getRefOneInput() {
		return getRefOneInputConnection().getRefInputNeuronOutput();
	}
	
	//method
	/**
	 * @return the one input connection of this neuron.
	 * @throws EmptyStateException if this neuron does not contain an input connection.
	 * @throws InvalidStateException if this neuron contains several input connections.
	 */
	public final InputConnection<I> getRefOneInputConnection() {
		return inputConnections.getRefOne();
	}
	
	//package-visible method
	/**
	 * @return the one input neuron of this neuron.
	 * @throws InvalidArgumentException
	 * if this neuron does not contain an input neuron or contains several input neurons.
	 */
	public final Neuronoid<?, ?, I> getRefOneInputNeuron(){
		return inputConnections.getRefOne().getRefInputNeuron();
	}
	
	//method
	/**
	 * @return the output of this neuron.
	 */
	public final O getRefOutput() {
		return output;
	}
	
	//package-visible method
	/**
	 * @return the output neurons of this neuron.
	 */
	public final ReadContainer<Neuronoid<?, O, ?>> getRefOutputNeurons() {
		return new ReadContainer<>(outputNeurons);
	}
	
	//method
	/**
	 * Removes the given input neuron from this neuron.
	 * 
	 * @param inputNeuron
	 * @return this neuron.
	 * @throws NonBiggerArgumentException if this neuron has not more input neurons than its minimal input neuron count says.
	 * @throws InvalidArgumentException if this neuron does not contain the given input neuron.
	 */
	public final N removeInputNeuron(final Neuronoid<?, ?, ?> inputNeuron) {
		
		//Checks if this neuron has not more input neurons than its minimal input neuron count says.
		Validator.suppose(getInputNeuronCount()).isBiggerThan(getMinInputNeuronCount());
		
		inputConnections.removeFirst(ic -> ic.hasInputNeuron(inputNeuron));
		inputNeuron.outputNeurons.removeFirst(this);
		
		return asConcreteType();
	}
	
	//abstract method
	/**
	 * Lets this neuron fire.
	 */
	protected abstract void internal_fire();
	
	//method
	/**
	 * Sets the output of this neuron.
	 * 
	 * @param output
	 */
	protected final void internal_setOutput(final O output) {
		this.output = output;
	}
	
	//method
	/**
	 * Adds the given input connection to this neuron.
	 * 
	 * @param inputConnection
	 * @return this neuron.
	 * @throws NullArgumentException if the given input connection is null.
	 * @throws NonSmallerArgumentException if this neuron has not less input neurons than its maximal input neurons count says.
	 * @throws InvalidArgumentException if this neuron contains already the input neuron of the given input connection.
	 */
	private final void addInputConnection(final InputConnection<I> inputConnection) {
		
		//Checks if the given input neuron is not null.
		Validator.suppose(inputConnection).thatIsNamed("input neuron").isInstance();
		
		//Checks if this neuron has less input neurons than its maximal input neurons count says.
		Validator.suppose(getInputNeuronCount()).isSmallerThan(getMaxInputNeuronCount());
		
		//Checks if this neuron does not contain the input neuron of the given input connection.
		if (inputConnections.contains(ic -> ic.hasInputNeuron(inputConnection.getRefInputNeuron()))) {
			throw new InvalidArgumentException(
				inputConnection,
				"is invalid because the current neuron contains already its input neuron"
			);
		}
		
		inputConnections.addAtEnd(inputConnection);
		
		inputConnection.getRefInputNeuron().outputNeurons.addAtEnd(this);
	}
}
