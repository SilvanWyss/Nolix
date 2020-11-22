//package declaration
package ch.nolix.system.baseNeuron;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
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
 * A {@link BaseNeuron} has several input neurons, that all provides an input of a certain type.
 * A {@link BaseNeuron} provides an output of a certain type.
 * When a {@link BaseNeuron} fires it can update its output.
 * 
 * @author Silvan Wyss
 * @date 2016-12-01
 * @lines 320
 * @param <N> The type of a {@link BaseNeuron}.
 * @param <I> The type of the inputs of a {@link BaseNeuron}.
 * @param <O> The type of the output of a {@link BaseNeuron}.
 */
public abstract class BaseNeuron<N extends BaseNeuron<N, I, O>, I, O> implements ISmartObject<N> {
	
	//attribute
	private O output;
	
	//multi-attributes
	private final LinkedList<InputConnection<I>> inputConnections = new LinkedList<>();
	private final LinkedList<BaseNeuron<?, O, ?>> outputNeurons = new LinkedList<>();
	
	//method
	/**
	 * Adds the given inputNeuron with the given weight to the current {@link BaseNeuron}.
	 * An input neuron is a neuron the current {@link BaseNeuron} can get an input from.
	 * 
	 * @param weight
	 * @param inputNeuron
	 * @return the current {@link BaseNeuron}.
	 * @throws ArgumentIsNullException if the given input neuron is null.
	 * @throws NonSmallerArgumentException if the current {@link BaseNeuron} has reached its maximal number of input neurons.
	 * @throws RuntimeException if the current {@link BaseNeuron} contains already the given input neuron.
	 */
	public final N addInputNeuron(final double weight, final BaseNeuron<?, ?, I> inputNeuron) {
		
		addInputConnection(new InputConnection<I>(weight, inputNeuron));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given input neuron to the current {@link BaseNeuron}.
	 * An input neuron is a neuron the current {@link BaseNeuron} can get an input from.
	 * 
	 * @param inputNeuron
	 * @return the current {@link BaseNeuron}.
	 * @throws ArgumentIsNullException if the given input neuron is null.
	 * @throws NonSmallerArgumentException if the current {@link BaseNeuron} has reached its maximal number of input neurons.
	 * @throws RuntimeException if the current {@link BaseNeuron} contains already the given input neuron.
	 */
	public final N addInputNeuron(final BaseNeuron<?, ?, I> inputNeuron) {
		
		addInputConnection(new InputConnection<I>(inputNeuron));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes all input neurons of the current {@link BaseNeuron} from the current {@link BaseNeuron}.
	 * 
	 * @return the current {@link BaseNeuron}.
	 * @throws UnequalArgumentException if the minimal number of input neurons of the current {@link BaseNeuron} is not 0.
	 */
	public final N clearInputNeurons() {
		
		//Asserts that the minimal number of input neurons of the current BaseNeuron is 0.
		Validator.assertThat(getMinInputNeuronCount()).thatIsNamed("minimal number of input neurons").isEqualTo(0);
		
		while (getInputNeuronCount() > 0) {
			removeInputNeuron(getRefInputNeurons().getRefFirst());
		}
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets the current {@link BaseNeuron} fire.
	 */
	public void fire() {
		
		final LinkedList<BaseNeuron<?, ?, ?>> nextNeurons = LinkedList.withElements(this);
		final LinkedList<BaseNeuron<?, ?, ?>> visitedNeurons = new LinkedList<>();
				
		while (nextNeurons.containsAny()) {
			
			final BaseNeuron<?, ?, ?> neuron = nextNeurons.removeAndGetRefFirst();
			visitedNeurons.addAtEnd(neuron);
			neuron.fillUpInputNeuronsWithoutOutputPartialRecursively(visitedNeurons);
			neuron.internalUpdate();
			
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
	 * Lets the current {@link BaseNeuron} fire in background.
	 * 
	 * @return a new future.
	 */
	public final Future fireInBackground() {
		return Sequencer.runInBackground(this::fire);
	}
		
	//method
	/**
	 * @return the number of input neurons of the current {@link BaseNeuron}.
	 */
	public final int getInputNeuronCount() {
		return inputConnections.getElementCount();
	}
	
	//method declaration
	/**
	 * @return the maximal number of input neurons of the current {@link BaseNeuron}.
	 */
	public abstract int getMaxInputNeuronCount();
	
	//method declaration
	/**
	 * @return the minimal number of input neurons of the current {@link BaseNeuron}.
	 */
	public abstract int getMinInputNeuronCount();
	
	//method
	/**
	 * @return the input connections of the current {@link BaseNeuron}.
	 */
	public final IContainer<InputConnection<I>> getRefInputConnections() {
		return ReadContainer.forIterable(inputConnections);
	}
	
	//method
	/**
	 * @return the input neurons of the current {@link BaseNeuron}.
	 */
	public final LinkedList<BaseNeuron<?, ?, I>> getRefInputNeurons() {
		return inputConnections.to(InputConnection::getRefInputNeuron);
	}
	
	//method
	/**
	 * @return the inputs of the current {@link BaseNeuron}.
	 */
	public final LinkedList<I> getRefInputs() {
		return inputConnections.to(InputConnection::getRefInputNeuronOutput);
	}
	
	//method
	/**
	 * @return the one input of the current {@link BaseNeuron}.
	 * @throws EmptyArgumentException if the current {@link BaseNeuron} does not have an input.
	 * @throws InvalidArgumentException if the current {@link BaseNeuron} has several inputs.
	 */
	public final I getRefOneInput() {
		return getRefOneInputConnection().getRefInputNeuronOutput();
	}
	
	//method
	/**
	 * @return the one input connection of the current {@link BaseNeuron}.
	 * @throws EmptyArgumentException if the current {@link BaseNeuron} does not contain an input connection.
	 * @throws InvalidArgumentException if the current {@link BaseNeuron} contains several input connections.
	 */
	public final InputConnection<I> getRefOneInputConnection() {
		return inputConnections.getRefOne();
	}
	
	//method
	/**
	 * @return the one input neuron of the current {@link BaseNeuron}.
	 * @throws InvalidArgumentException
	 * if the current {@link BaseNeuron} does not contain an input neuron or contains several input neurons.
	 */
	public final BaseNeuron<?, ?, I> getRefOneInputNeuron(){
		return inputConnections.getRefOne().getRefInputNeuron();
	}
	
	//method
	/**
	 * @return the output of the current {@link BaseNeuron}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseNeuron} does not have an output after firing.
	 */
	public final O getRefOutput() {
		
		if (output == null) {
			internalUpdate();
		}
		
		if (output == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.OUTPUT);
		}
		
		return output;
	}
	
	//method
	/**
	 * @return the output neurons of the current {@link BaseNeuron}.
	 */
	public final ReadContainer<BaseNeuron<?, O, ?>> getRefOutputNeurons() {
		return ReadContainer.forIterable(outputNeurons);
	}
	
	//method
	/**
	 * Removes the given input neuron from the current {@link BaseNeuron}.
	 * 
	 * @param inputNeuron
	 * @return the current {@link BaseNeuron}.
	 * @throws NonBiggerArgumentException if the current {@link BaseNeuron} has not more input neurons than its minimal input neuron count says.
	 * @throws InvalidArgumentException if the current {@link BaseNeuron} does not contain the given input neuron.
	 */
	public final N removeInputNeuron(final BaseNeuron<?, ?, ?> inputNeuron) {
		
		//Asserts that the current BaseNeuron has not more input neurons than its minimal input neuron count says.
		Validator.assertThat(getInputNeuronCount()).isBiggerThan(getMinInputNeuronCount());
		
		inputConnections.removeFirst(ic -> ic.hasInputNeuron(inputNeuron));
		inputNeuron.outputNeurons.removeFirst(this);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the output of the current {@link BaseNeuron}.
	 * 
	 * @param output
	 */
	protected final void internalSetOutput(final O output) {
		
		Validator.assertThat(output).thatIsNamed(VariableNameCatalogue.OUTPUT).isNotNull();
		
		this.output = output;
	}
	
	//method declaration
	/**
	 * Updates the current {@link BaseNeuron}.
	 */
	protected abstract void internalUpdate();
	
	//method
	/**
	 * Adds the given input connection to the current {@link BaseNeuron}.
	 * 
	 * @param inputConnection
	 * @return the current {@link BaseNeuron}.
	 * @throws ArgumentIsNullException if the given input connection is null.
	 * @throws NonSmallerArgumentException if the current {@link BaseNeuron} has not less input neurons than its maximal input neurons count says.
	 * @throws InvalidArgumentException if the current {@link BaseNeuron} contains already the input neuron of the given input connection.
	 */
	private final void addInputConnection(final InputConnection<I> inputConnection) {
		
		//Asserts that the given input neuron is not null.
		Validator.assertThat(inputConnection).thatIsNamed("input neuron").isNotNull();
		
		//Asserts that the current BaseNeuron} has less input neurons than its maximal input neurons count says.
		Validator.assertThat(getInputNeuronCount()).isSmallerThan(getMaxInputNeuronCount());
		
		//Asserts that the current BaseNeuron does not contain the input neuron of the given input connection.
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
	 * Fills up the input neurons of the current {@link BaseNeuron}, that do not have an output, into the given list partial recursively.
	 * 
	 * @param list
	 */
	private void fillUpInputNeuronsWithoutOutputPartialRecursively(final LinkedList<BaseNeuron<?, ?, ?>> list) {
		
		//Iterates the input connections of the current BaseNeuron.
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
