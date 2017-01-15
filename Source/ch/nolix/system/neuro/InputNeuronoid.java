//package declaration
package ch.nolix.system.neuro;

//own import
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * An input neuronoid provides an input.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 50
 */
public final class InputNeuronoid<O> {

	//attribute
	private final Neuron<?, O, ?> neuron;
	
	//constructor
	/**
	 * Creates new input neuronoid with the given neuron.
	 * 
	 * @param neuron
	 * @throws NullArgumentException if the given neuron is null.
	 */
	public InputNeuronoid(final Neuron<?, O, ?> neuron) {
		
		//Checks if the given neuron is not null.
		ZetaValidator.supposeThat(neuron).thatIsInstanceOf(Neuron.class).isNotNull();
		
		this.neuron = neuron;
	}
	
	//method
	/**
	 * @return the output of the neuron of this input neuronoid.
	 */
	public O getRefInput() {
		return neuron.getRefOutput();
	}
	
	//method
	/**
	 * @param neuron
	 * @return true of this input neuronoid has the given neuron.
	 */
	public boolean hasNeuron(final Neuron<?, ?, ?> neuron) {	
		return (this.neuron == neuron);
	}
}
