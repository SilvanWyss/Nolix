//package declaration
package ch.nolix.system.baseNeuron;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.generalSkillAPI.ISmartObject;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.sequencer.Future;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//class
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
 * @lines 320
 * @param <I> The type of the inputs of a neuron.
 * @param <O> The type of the output of a neuron.
 * @param <N> The type of a neuron.
 */
public abstract class BaseNeuron<N extends BaseNeuron<N, I, O>, I, O>
implements ISmartObject<N> {
	
	//attribute
	private O output;
	
	//multi-attributes
	private final LinkedList<InputConnection<I>> inputConnections = new LinkedList<>();
	private final LinkedList<BaseNeuron<?, O, ?>> outputNeurons = new LinkedList<>();
	
	//method
	/**
	 * Adds the given input neuron with the given weight to this neuron.
	 * An input neuron is a neuron this neuron can get an input from.
	 * 
	 * @param weight
	 * @param inputNeuron
	 * @return this neuron.
	 * @throws ArgumentIsNullException if the given input neuron is null.
	 * @throws NonSmallerArgumentException if this neuron has reached its maximal number of input neurons.
	 * @throws RuntimeException if this neuron contains already the given input neuron.
	 */
	public final N addInputNeuron(final double weight, final BaseNeuron<?, ?, I> inputNeuron) {
		
		addInputConnection(new InputConnection<I>(weight, inputNeuron));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given input neuron to this neuron.
	 * An input neuron is a neuron this neuron can get an input from.
	 * 
	 * @param inputNeuron
	 * @return this neuron.
	 * @throws ArgumentIsNullException if the given input neuron is null.
	 * @throws NonSmallerArgumentException if this neuron has reached its maximal number of input neurons.
	 * @throws RuntimeException if this neuron contains already the given input neuron.
	 */
	public final N addInputNeuron(final BaseNeuron<?, ?, I> inputNeuron) {
		
		addInputConnection(new InputConnection<I>(inputNeuron));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes all input neurons of this neuron from this neuron.
	 * 
	 * @return this neuron.
	 * @throws UnequalArgumentException if the minimal number of input neurons of this neuron is not 0.
	 */
	public final N clearInputNeurons() {
		
		//Asserts that the minimal number of input neurons of this neuron is 0.
		Validator.assertThat(getMinInputNeuronCount()).thatIsNamed("minimal number of input neurons").isZero();
		
		while (getInputNeuronCount() > 0) {
			removeInputNeuron(getRefInputNeurons().getRefFirst());
		}
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets this neuron fire transitively.
	 */
	public void fireTransitively() {
		
		final LinkedList<BaseNeuron<?, ?, ?>> nextNeurons = new LinkedList<>(this);
		final LinkedList<BaseNeuron<?, ?, ?>> visitedNeurons = new LinkedList<>();
				
		while (nextNeurons.containsAny()) {
			
			final BaseNeuron<?, ?, ?> neuron = nextNeurons.removeAndGetRefFirst();
			visitedNeurons.addAtEnd(neuron);
			neuron.fillUpInputNeuronsWithoutOutputPartialRecursively(visitedNeurons);
			neuron.fire();
			
			//Iterates the output neurons of the current neuron.
			for (final BaseNeuron<?, ?, ?> on : neuron.getRefOutputNeurons()) {
				
				//Handles the case that the current output neuron has not been visited yet.
				if (!visitedNeurons.contains(on)) {
					nextNeurons.addAtEnd(on);
				}
			}
		}
	}
	
	//method
	/**
	 * Lets this neuron fire transitively in background.
	 * 
	 * @return a new future.
	 */
	public final Future fireTransitivelyInBackground() {
		return Sequencer.runInBackground(() -> fireTransitively());
	}
		
	//method
	/**
	 * @return the number of input neurons of this neuron.
	 */
	public final int getInputNeuronCount() {
		return inputConnections.getElementCount();
	}
	
	//method declaration
	/**
	 * @return the maximal number of input neurons of this neuron.
	 */
	public abstract int getMaxInputNeuronCount();
	
	//method declaration
	/**
	 * @return the minimal number of input neurons of this neuron.
	 */
	public abstract int getMinInputNeuronCount();
	
	//method
	/**
	 * @return the input connections of this neuron.
	 */
	public final ReadContainer<InputConnection<I>> getRefInputConnections() {
		return new ReadContainer<>(inputConnections);
	}
	
	//method
	/**
	 * @return the input neurons of this neuron.
	 */
	public final ReadContainer<BaseNeuron<?, ?, I>> getRefInputNeurons() {
		return new ReadContainer<>(inputConnections.to(ic -> ic.getRefInputNeuron()));
	}
	
	//method
	/**
	 * @return the inputs of this neuron.
	 */
	public final ReadContainer<I> getRefInputs() {
		return new ReadContainer<>(inputConnections.to(ic -> ic.getRefInputNeuronOutput()));
	}
	
	//method
	/**
	 * @return the one input of this neuron.
	 * @throws EmptyArgumentException if this neuron does not have an input.
	 * @throws InvalidArgumentException if this neuron has several inputs.
	 */
	public final I getRefOneInput() {
		return getRefOneInputConnection().getRefInputNeuronOutput();
	}
	
	//method
	/**
	 * @return the one input connection of this neuron.
	 * @throws EmptyArgumentException if this neuron does not contain an input connection.
	 * @throws InvalidArgumentException if this neuron contains several input connections.
	 */
	public final InputConnection<I> getRefOneInputConnection() {
		return inputConnections.getRefOne();
	}
	
	//method
	/**
	 * @return the one input neuron of this neuron.
	 * @throws InvalidArgumentException
	 * if this neuron does not contain an input neuron or contains several input neurons.
	 */
	public final BaseNeuron<?, ?, I> getRefOneInputNeuron(){
		return inputConnections.getRefOne().getRefInputNeuron();
	}
	
	//method
	/**
	 * @return the output of this neuron.
	 * @throws ArgumentDoesNotHaveAttributeException if this neuron does not have an output after firing.
	 */
	public final O getRefOutput() {
		
		if (output == null) {
			fire();
		}
		
		if (output == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.OUTPUT);
		}
		
		return output;
	}
	
	//method
	/**
	 * @return the output neurons of this neuron.
	 */
	public final ReadContainer<BaseNeuron<?, O, ?>> getRefOutputNeurons() {
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
	public final N removeInputNeuron(final BaseNeuron<?, ?, ?> inputNeuron) {
		
		//Asserts that this neuron has not more input neurons than its minimal input neuron count says.
		Validator.assertThat(getInputNeuronCount()).isBiggerThan(getMinInputNeuronCount());
		
		inputConnections.removeFirst(ic -> ic.hasInputNeuron(inputNeuron));
		inputNeuron.outputNeurons.removeFirst(this);
		
		return asConcrete();
	}
	
	//method declaration
	/**
	 * Lets this neuron fire.
	 */
	public abstract void fire();
	
	//method
	/**
	 * Sets the output of this neuron.
	 * 
	 * @param output
	 */
	protected final void internal_setOutput(final O output) {
		
		Validator.assertThat(output).thatIsNamed(VariableNameCatalogue.OUTPUT).isNotNull();
		
		this.output = output;
	}
	
	//method
	/**
	 * Adds the given input connection to this neuron.
	 * 
	 * @param inputConnection
	 * @return this neuron.
	 * @throws ArgumentIsNullException if the given input connection is null.
	 * @throws NonSmallerArgumentException if this neuron has not less input neurons than its maximal input neurons count says.
	 * @throws InvalidArgumentException if this neuron contains already the input neuron of the given input connection.
	 */
	private final void addInputConnection(final InputConnection<I> inputConnection) {
		
		//Asserts that the given input neuron is not null.
		Validator.assertThat(inputConnection).thatIsNamed("input neuron").isNotNull();
		
		//Asserts that this neuron has less input neurons than its maximal input neurons count says.
		Validator.assertThat(getInputNeuronCount()).isSmallerThan(getMaxInputNeuronCount());
		
		//Asserts that this neuron does not contain the input neuron of the given input connection.
		if (inputConnections.contains(ic -> ic.hasInputNeuron(inputConnection.getRefInputNeuron()))) {
			throw new InvalidArgumentException(
				inputConnection,
				"is invalid because the current neuron contains already its input neuron"
			);
		}
		
		inputConnections.addAtEnd(inputConnection);
		
		inputConnection.getRefInputNeuron().outputNeurons.addAtEnd(this);
	}
	
	//method
	/**
	 * Fills up the input neurons of this neuron, that do not have an output, into the given list partial recursively.
	 * 
	 * @param list
	 */
	private void fillUpInputNeuronsWithoutOutputPartialRecursively(final LinkedList<BaseNeuron<?, ?, ?>> list) {
		
		//Iterates the input connections of this neuron.
		for (final var ic : inputConnections) {
			
			//Extracts the input neuron of the current input connection.
			final var inputNeuron = ic.getRefInputNeuron();
			
			//Handles the case that the input neuron does not have an output.
			if (inputNeuron.output == null) {
				list.addAtEnd(inputNeuron);
				inputNeuron.fillUpInputNeuronsWithoutOutputPartialRecursively(list);
			}
		}
	}
}
