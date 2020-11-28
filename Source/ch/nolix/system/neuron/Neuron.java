//package declaration
package ch.nolix.system.neuron;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.baseneuron.BaseNeuron;
import ch.nolix.system.baseneuron.InputConnection;

//class
/**
 * A neuron has an output function that can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 210
 * @param <IO> The type of the input and output of a neuron.
 */
public final class Neuron<IO> extends BaseNeuron<Neuron<IO>, IO, IO> {
	
	//constants
	public static final int MIN_INPUT_NEURON_COUNT = 0;
	public static final int MAX_INPUT_NEURON_COUNT = Integer.MAX_VALUE;
	
	//attribute
	private IElementTakerElementGetter<Neuron<IO>, IO> outputFunction;
	
	//method
	/**
	 * @return the maximal number of input neurons of this neuron.
	 */
	@Override
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this neuron.
	 */
	@Override
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}
	
	//method
	/**
	 * Sets the output of this neuron.
	 * 
	 * Replaces the output function of this neuron
	 * if this neuron has already an output function.
	 * 
	 * @param output
	 * @return this neuron.
	 */
	public Neuron<IO> setOutput(final IO output) {
		
		//Sets the output function of this neuron.
		outputFunction = n -> output;
		
		return this;
	}
	
	//method
	/**
	 * Sets the output function of this neuron.
	 * 
	 * Replaces the output function of this neuron
	 * if this neuron has already an output function.
	 * 
	 * @param outputFunction
	 * @return this neuron.
	 * @throws ArgumentIsNullException if the given output function is null.
	 */
	public Neuron<IO> setOutputFunction(
		final IElementTakerElementGetter<Iterable<IO>, IO> outputFunction
	) {
		
		//Asserts that the given output function is not null.
		Validator
		.assertThat(outputFunction)
		.thatIsNamed(VariableNameCatalogue.OUTPUT_FUNCTION)
		.isNotNull();
		
		//Sets the output function of this neuron.
		this.outputFunction = n -> outputFunction.getOutput(n.getRefInputs());

		return this;
	}
	
	//method
	/**
	 * Sets the output function of this neuron.
	 * 
	 * Replaces the output function of this neuron
	 * if this neuron has already an output function.
	 * 
	 * @param outputFunction
	 * @return this neuron.
	 * @throws ArgumentIsNullException if the given output function is null.
	 */
	public Neuron<IO> setOutputFunction2(
		final IElementTakerElementGetter<IContainer<IO>, IO> outputFunction
	) {
		
		//Asserts that the given output function is not null.
		Validator
		.assertThat(outputFunction)
		.thatIsNamed(VariableNameCatalogue.OUTPUT_FUNCTION)
		.isNotNull();
		
		//Sets the output function of this neuron.
		this.outputFunction = n -> outputFunction.getOutput(n.getRefInputs());

		return this;
	}
	
	//method
	/**
	 * Sets the output function of this neuron.
	 * 
	 * Replaces the output function of this neuron
	 * if this neuron has already an output function.
	 * 
	 * @param outputFunction
	 * @return this neuron.
	 * @throws ArgumentIsNullException if the given weight output function is null.
	 */
	public Neuron<IO> setOutputFunction3(
		final IElementTakerElementGetter<Iterable<InputConnection<IO>>, IO> outputFunction
	) {
		
		//Asserts that the given output function is not null.
		Validator
		.assertThat(outputFunction)
		.thatIsNamed(VariableNameCatalogue.OUTPUT_FUNCTION)
		.isNotNull();
		
		//Sets the output function of this neuron.
		this.outputFunction = n -> outputFunction.getOutput(n.getRefInputConnections());
				
		return this;
	}
	
	//method
	/**
	 * Sets the output function of this neuron.
	 * 
	 * Replaces the output function of this neuron
	 * if this neuron has already an output function.
	 * 
	 * @param outputFunction
	 * @return this neuron.
	 * @throws ArgumentIsNullException if the given weight output function is null.
	 */
	public Neuron<IO> setOutputFunction4(
		final IElementTakerElementGetter<IContainer<InputConnection<IO>>, IO> outputFunction
	) {
		
		//Asserts that the given output function is not null.
		Validator
		.assertThat(outputFunction)
		.thatIsNamed(VariableNameCatalogue.OUTPUT_FUNCTION)
		.isNotNull();
		
		//Sets the output function of this neuron.
		this.outputFunction = n -> outputFunction.getOutput(n.getRefInputConnections());
				
		return this;
	}
	
	//method
	/**
	 * Lets this neuron fire.
	 * 
	 * @throws ArgumentDoesNotHaveAttributeException if this neuron does not have an output function.
	 */
	@Override
	public void internalUpdate() {
		internalSetOutput(getOutputFunction().getOutput(this));
	}
	
	//method
	/**
	 * @return the output function of this neuron. 
	 * @throws ArgumentDoesNotHaveAttributeException if this neuron does not have an output function.
	 */
	private IElementTakerElementGetter<Neuron<IO>, IO> getOutputFunction() {
		
		//Asserts that this neuron has an
		supposeHasOutputFunction();
		
		return outputFunction;
	}
	
	//method
	/**
	 * @return true if this neuron has an output function.
	 */
	private boolean hasOutputFunction() {
		return (outputFunction != null);
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if this neuron does not have an output function.
	 */
	private void supposeHasOutputFunction() {
		if (!hasOutputFunction()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.OUTPUT_FUNCTION);
		}
	}
}
