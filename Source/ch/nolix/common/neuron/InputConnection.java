//package declaration
package ch.nolix.common.neuron;

//own import
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * An input connection provides an input neuron and its weight.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 90
 * 
 * @param <O> - The type of the output of the input neuron of an input connection.
 */
public final class InputConnection<O> {

	//default value
	public static final double DEFAULT_WEIGHT = 1.0;
	
	//attributes
	private final Neuron<?, O, ?> inputNeuron;
	private double weight = DEFAULT_WEIGHT;
	
	//constructor
	/**
	 * Creates new input connection with a default weight and the given input neuron.
	 * 
	 * @param inputNeuron
	 * @throws NullArgumentException if the given input neuron is null.
	 */
	public InputConnection(final Neuron<?, O, ?> inputNeuron) {
		
		//Checks if the given input neuron is not null.
		ZetaValidator.supposeThat(inputNeuron).thatIsNamed("input neuron").isNotNull();
		
		this.inputNeuron = inputNeuron;
	}
	
	//constructor
	/**
	 * Creates new input connection with the given weight and input neuron.
	 * 
	 * @param weight
	 * @param inputNeuron
	 * @throws NullArgumentException if the given input neuron is null.
	 */
	public InputConnection(final double weight, final Neuron<?, O, ?> inputNeuron) {

		//Calls other constructor.
		this(inputNeuron);
		
		setWeight(weight);
	}

	//method
	/**
	 * @return the output of the input neuron of this input connection.
	 */
	public O getRefInput() {
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
	public boolean hasInputNeuron(final Neuron<?, ?, ?> inputNeuron) {	
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
	
	//package visible method
	/**
	 * @return the input neuron of this input connection.
	 */
	final Neuron<?, O, ?> getRefInputNeuron() {
		return inputNeuron;
	}
}
