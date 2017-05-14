//package declaration
package ch.nolix.core.neuron;

//own import
import ch.nolix.core.validator2.ZetaValidator;

//class
/**
 * A triggerable neuronoid provides a neuron to trigger.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 50
 */
public final class TriggerableNeuronoid {
	
	//attribute
	private final Neuron<?, ?, ?> neuron;
	
	//constructor
	/**
	 * Creates new triggerable neuronoid with the given neuron.
	 * 
	 * @param neuron
	 * @throws NullArgumentException if the given neuron is null.
	 */
	public TriggerableNeuronoid(Neuron<?, ?, ?> neuron) {
		
		//Checks if the given neuron is not null.
		ZetaValidator.supposeThat(neuron).thatIsInstanceOf(Neuron.class).isNotNull();
		
		this.neuron = neuron;
	}
	
	//method
	/**
	 * @param neuron
	 * @return true if this triggerable neuronoid has the given neuron.
	 */
	public boolean hasNeuron(final Neuron<?, ?, ?> neuron) {
		return (this.neuron == neuron);
	}

	//package-visible method
	/**
	 * @return the neuron of this triggerable neronoid.
	 */
	Neuron<?, ?, ?> getRefNeuron() {
		return neuron;
	}
}
