//package declaration
package ch.nolix.system.neuronoid;

//own imports
import ch.nolix.core.validator2.Validator;

//class
/**
 * An input connection provides a neuron as input neuron and its weight.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 100
 * @param <O> The type of the output of the input neuron of an input connection.
 */
public final class InputConnection<O> {

	//default value
	public static final double DEFAULT_WEIGHT = 1.0;
	
	//attributes
	private double weight = DEFAULT_WEIGHT;
	private final Neuronoid<?, ?, O> inputNeuron;
	
	//constructor
	/**
	 * Creates a new input connection with a default weight and the given input neuron.
	 * 
	 * @param inputNeuron
	 * @throws NullArgumentException if the given input neuron is null.
	 */
	public InputConnection(final Neuronoid<?, ?, O> inputNeuron) {
		
		//Checks if the given input neuron is not null.
		Validator
		.suppose(inputNeuron)
		.thatIsNamed("input neuron")
		.isInstance();
		
		//Sets the input neuron of this input connection.
		this.inputNeuron = inputNeuron;
	}
	
	//constructor
	/**
	 * Creates a new input connection with the given weight and input neuron.
	 * 
	 * @param weight
	 * @param inputNeuron
	 * @throws NullArgumentException if the given input neuron is null.
	 */
	public InputConnection(
		final double weight,
		final Neuronoid<?, ?, O> inputNeuron
	) {

		//Calls other constructor.
		this(inputNeuron);
		
		setWeight(weight);
	}
	
	//method
	/**
	 * @return the input neuron of this input connection.
	 */
	public final Neuronoid<?, ?, O> getRefInputNeuron() {
		return inputNeuron;
	}

	//method
	/**
	 * @return the output of the input neuron of this input connection.
	 */
	public O getRefInputNeuronOutput() {
		return inputNeuron.getRefOutput();
	}
	
	//method
	/**
	 * @return the weight of this input connection.
	 */
	public double getWeight() {
		return weight;
	}
	
	//method
	/**
	 * @param inputNeuron
	 * @return true if this input connection has the given input neuron.
	 */
	public boolean hasInputNeuron(final Neuronoid<?, ?, ?> inputNeuron) {	
		return (this.inputNeuron == inputNeuron);
	}
	
	//method
	/**
	 * Sets the weight of this input connection.
	 * 
	 * @param weight
	 */
	public void setWeight(final double weight) {
		this.weight = weight;
	}
}
