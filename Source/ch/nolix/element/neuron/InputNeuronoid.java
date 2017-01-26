//package declaration
package ch.nolix.element.neuron;

//own import
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * An input neuronoid provides an input and its weight.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 */
public final class InputNeuronoid<O> {

	//default value
	public final static double DEFAULT_WEIGHT = 1.0;
	
	//attributes
	private final Neuron<?, O, ?> neuron;
	private double weight = DEFAULT_WEIGHT;
	
	//constructor
	/**
	 * Creates new input neuronoid with the given neuron that will have a default weight.
	 * 
	 * @param neuron
	 * @throws NullArgumentException if the given neuron is null.
	 */
	public InputNeuronoid(final Neuron<?, O, ?> neuron) {
		
		//Checks if the given neuron is not null.
		ZetaValidator.supposeThat(neuron).thatIsInstanceOf(Neuron.class).isNotNull();
		
		this.neuron = neuron;
	}
	
	//constructor
	/**
	 * Creates new input neronoid with the given weight that will have the given weight.
	 * 
	 * @param weight
	 * @param neuron
	 * @throws NullArgumentException if the given neuron is null.
	 */
	public InputNeuronoid(final double weight, final Neuron<?, O, ?> neuron) {

		//Calls other constructor.
		this(neuron);
		
		setWeight(weight);
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
	 * @return the weight of this input neuronoid.
	 */
	public double getWeight() {
		return weight;
	}
	
	//method
	/**
	 * @param neuron
	 * @return true of this input neuronoid has the given neuron.
	 */
	public boolean hasNeuron(final Neuron<?, ?, ?> neuron) {	
		return (this.neuron == neuron);
	}
	
	//method
	/**
	 * Sets the weight of this input neoronoid.
	 * 
	 * @param weight
	 */
	public void setWeight(final double weight) {
		this.weight = weight;
	}
}
