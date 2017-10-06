//package declaration
package ch.nolix.system.neuronoid;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A standard neuron is a neuron with an output or an output function that can be set dynamically.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 110
 * @param <IO> - The type of the input and output of a standard neuron.
 */
public final class StandardNeuron<IO>
extends Neuronoid<IO, IO, StandardNeuron<IO>> {
	
	//attribute
	private IElementTakerElementGetter<Iterable<InputConnection<IO>>, IO> outputFunction;
	
	//method
	/**
	 * Sets the output function of this standard neuron.
	 * This method overrides any previous output that was set to this standard neuron.
	 * 
	 * @param outputFunction
	 * @return this standard neuron.
	 * @throws NullArgumentException if the given output function is null.
	 */
	public StandardNeuron<IO> setOutputFunction(
		final IElementTakerElementGetter<Iterable<IO>, IO> outputFunction
	) {
		
		//Checks if the given output function is not null.
		Validator.suppose(outputFunction).thatIsNamed("output function").isNotNull();
		
		//Sets the output function of this standard neuron.
		this.outputFunction
		= in -> {
			
			//Creates input list.
			final List<IO> inputs = new List<IO>();
			for (InputConnection<IO> n: in) {
				inputs.addAtEnd(n.getRefInput());
			}
			
			return outputFunction.getOutput(inputs);
		};

		return this;
	}
	
	//method
	/**
	 * Sets the output of this standard neuron.
	 * This method overrided any previous output function that was set to this standard neuron.
	 * 
	 * @param output
	 * @return this standard neuron.
	 */
	public StandardNeuron<IO> setOutputFunction(final IO output) {
		
		setOutputFunction(c -> output);
		
		return this;
	}
	
	//method
	/**
	 * Sets the output function of this standard neuron.
	 * 
	 * @param weightOutputFunction
	 * @return this standard neuron.
	 * @throws NullArgumentException if the given weight output function is null.
	 */
	public StandardNeuron<IO> setWeightOutputFunction(
		final IElementTakerElementGetter<Iterable<InputConnection<IO>>, IO> weightOutputFunction
	) {
		
		//Checks if the given output function is not null.
		Validator.suppose(weightOutputFunction).thatIsNamed("weight output function").isNotNull();
		
		outputFunction = weightOutputFunction;
		
		return this;
	}

	//method
	/**
	 * @return the maximal number of input neurons of this standard neuron.
	 */
	protected int getMaxInputNeuronCount() {
		return Integer.MAX_VALUE;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this standard neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 0;
	}
	
	public void internal_fire() {
		setOutput(outputFunction.getOutput(getRefInputConnections()));
	}
}
