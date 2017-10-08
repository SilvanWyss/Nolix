//package declaration
package ch.nolix.system.neuron;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.validator2.Validator;
import ch.nolix.system.neuronoid.InputConnection;
import ch.nolix.system.neuronoid.Neuronoid;

//class
/**
 * A neuron is a neuron with an output or an output function that can be set dynamically.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 110
 * @param <IO> - The type of the input and output of a neuron.
 */
public final class Neuron<IO>
extends Neuronoid<Neuron<IO>, IO, IO> {
	
	//attribute
	private IElementTakerElementGetter<Neuronoid<?, IO, IO>, IO> outputFunction;
	
	//method
	/**
	 * Sets the output function of this neuron.
	 * This method overrides any previous output that was set to this neuron.
	 * 
	 * @param outputFunction
	 * @return this neuron.
	 * @throws NullArgumentException if the given output function is null.
	 */
	public Neuron<IO> setOutputFunction(
		final IElementTakerElementGetter<Iterable<IO>, IO> outputFunction
	) {
		
		//Checks if the given output function is not null.
		Validator.suppose(outputFunction).thatIsNamed("output function").isNotNull();
		
		this.outputFunction = n -> outputFunction.getOutput(n.getRefInputs());

		return this;
	}
	
	//method
	/**
	 * Sets the output of this neuron.
	 * This method overrided any previous output function that was set to this neuron.
	 * 
	 * @param output
	 * @return this neuron.
	 */
	public Neuron<IO> setOutputFunction(final IO output) {
		
		setOutputFunction(c -> output);
		
		return this;
	}
	
	//method
	/**
	 * Sets the output function of this neuron.
	 * 
	 * @param weightOutputFunction
	 * @return this neuron.
	 * @throws NullArgumentException if the given weight output function is null.
	 */
	public Neuron<IO> setWeightOutputFunction(
		final IElementTakerElementGetter<Iterable<InputConnection<IO>>, IO> weightOutputFunction
	) {
		
		//Checks if the given output function is not null.
		Validator.suppose(weightOutputFunction).thatIsNamed("weight output function").isNotNull();
		
		//outputFunction = weightOutputFunction;
		outputFunction =
		n -> {
			return weightOutputFunction.getOutput(n.getRefInputConnections());
		};
		
		return this;
	}

	//method
	/**
	 * @return the maximal number of input neurons of this neuron.
	 */
	public int getMaxInputNeuronCount() {
		return Integer.MAX_VALUE;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this neuron.
	 */
	public int getMinInputNeuronCount() {
		return 0;
	}
	
	public void internal_fire() {
		setOutput(outputFunction.getOutput(this));
	}
}
