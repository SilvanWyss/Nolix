//package declaration
package ch.nolix.system.neuronoid;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * An output connection provides a neuron as output neuron.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 40
 * @param <I> The type of the input
 * of the output neuron of an output connection.
 */
public final class OutputConnection<I> {

	//attribute
	private final Neuronoid<?, I, ?> outputNeuron;
	
	//constructor
	/**
	 * Creates a new output connection with the given output neuron.
	 * 
	 * @param outputNeuron
	 * @throws NullArgumentException if the given output neuron is null.
	 */
	public OutputConnection(final Neuronoid<?, I, ?> outputNeuron) {
		
		//Checks if the given output neuron is not null.
		Validator
		.suppose(outputNeuron)
		.thatIsNamed("output neuron")
		.isNotNull();
		
		this.outputNeuron = outputNeuron;
	}
	
	//method
	/**
	 * @param outputNeuron
	 * @return true if this output connection has the given output neuron.
	 */
	public boolean hasOutputNeuron(final Neuronoid<?, ?, ?> outputNeuron) {	
		return (this.outputNeuron == outputNeuron);
	}
	
	//package visible method
	/**
	 * @return the output neuron of this input connection.
	 */
	final Neuronoid<?, I, ?> getRefOutputNeuron() {
		return outputNeuron;
	}
}
