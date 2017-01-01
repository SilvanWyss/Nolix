/*
 * file:	InputNeuronoid.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	10
 */

//package declaration
package ch.nolix.system.neuro;

//onw import
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * An input neuronoid provides an input.
 * 
 * @author Silvan Wyss
 */
public final class InputNeuronoid<I> {

	//attribute
	private final Neuron<I, ?> neuron;
	
	//constructor
	/**
	 * Creates new input neuronoid with the given neuron.
	 * 
	 * @param neuron		The neuron of this neuronoid.
	 * @throws NullArgumentException if the given neuron is null
	 */
	public InputNeuronoid(final Neuron<I, ?> neuron) {
		
		//Checks the given neuron.
		ZetaValidator.supposeThat(neuron).thatIsNamed("neuron").isNotNull();
		
		this.neuron = neuron;
	}
	
	//method
	/**
	 * @return the output of the neuron of this input neuronoid.
	 */
	public I getRefInput() {
		return neuron.getRefOutput();
	}
	
	//method
	/**
	 * @param neuron
	 * @return true of this input neuronoid has the given neuron.
	 */
	public boolean hasNeuron(final Neuron<I, ?> neuron) {	
		return (this.neuron == neuron);
	}
}
