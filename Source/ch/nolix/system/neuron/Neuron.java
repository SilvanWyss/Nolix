//package declaration
package ch.nolix.system.neuron;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;
import ch.nolix.system.neuronoid.InputConnection;
import ch.nolix.system.neuronoid.Neuronoid;

//class
/**
 * A neuron has an output function that can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 210
 * @param <IO> The type of the input and output of a neuron.
 */
public final class Neuron<IO> extends Neuronoid<Neuron<IO>, IO, IO> {
	
	//limits
	public static final int MIN_INPUT_NEURON_COUNT = 0;
	public static final int MAX_INPUT_NEURON_COUNT = Integer.MAX_VALUE;
	
	//attribute
	private IElementTakerElementGetter<Neuron<IO>, IO> outputFunction;
	
	//method
	/**
	 * @return the maximal number of input neurons of this neuron.
	 */
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this neuron.
	 */
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
	 * @throws NullArgumentException if the given output function is not an instance.
	 */
	public Neuron<IO> setOutputFunction(
		final IElementTakerElementGetter<Iterable<IO>, IO> outputFunction
	) {
		
		//Checks if the given output function is an instance.
		Validator
		.suppose(outputFunction)
		.thatIsNamed(VariableNameCatalogue.OUTPUT_FUNCTION)
		.isInstance();
		
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
	 * @throws NullArgumentException if the given output function is not an instance.
	 */
	public Neuron<IO> setOutputFunction2(
		final IElementTakerElementGetter<IContainer<IO>, IO> outputFunction
	) {
		
		//Checks if the given output function is an instance.
		Validator
		.suppose(outputFunction)
		.thatIsNamed(VariableNameCatalogue.OUTPUT_FUNCTION)
		.isInstance();
		
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
	 * @throws NullArgumentException if the given weight output function is not an instance.
	 */
	public Neuron<IO> setOutputFunction3(
		final IElementTakerElementGetter<Iterable<InputConnection<IO>>, IO> outputFunction
	) {
		
		//Checks if the given output function is an instance.
		Validator
		.suppose(outputFunction)
		.thatIsNamed(VariableNameCatalogue.OUTPUT_FUNCTION)
		.isInstance();
		
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
	 * @throws NullArgumentException if the given weight output function is not an instance.
	 */
	public Neuron<IO> setOutputFunction4(
		final IElementTakerElementGetter<IContainer<InputConnection<IO>>, IO> outputFunction
	) {
		
		//Checks if the given output function is an instance.
		Validator
		.suppose(outputFunction)
		.thatIsNamed(VariableNameCatalogue.OUTPUT_FUNCTION)
		.isInstance();
		
		//Sets the output function of this neuron.
		this.outputFunction = n -> outputFunction.getOutput(n.getRefInputConnections());
				
		return this;
	}
	
	//method
	/**
	 * Lets this neuron fire.
	 * 
	 * @throws UnexistingAttributeException if this neuron has no output function.
	 */
	protected void internal_fire() {
		internal_setOutput(getOutputFunction().getOutput(this));
	}
	
	//method
	/**
	 * @return the output function of this neuron. 
	 * @throws UnexistingAttributeException if this neuron has no output function.
	 */
	private IElementTakerElementGetter<Neuron<IO>, IO> getOutputFunction() {
		
		//Checks if this neuron has an
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
	 * @throws UnexistingAttributeException if this neuron has no output function.
	 */
	private void supposeHasOutputFunction() {
		if (!hasOutputFunction()) {
			throw new UnexistingAttributeException(this, VariableNameCatalogue.OUTPUT_FUNCTION);
		}
	}
}
