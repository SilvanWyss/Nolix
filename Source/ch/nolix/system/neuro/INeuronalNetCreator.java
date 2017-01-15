//package declaration
package ch.nolix.system.neuro;

//interface
/**
 * A neuronal net creator can create neuronal nets.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 10
 * @param <IO> - The type of the elements of the input container and output container of the neuronal nets a neuronal net creator creates.
 */
public interface INeuronalNetCreator<IO> {

	//abstract method
	/**
	 * @return a new neuronal net.
	 */
	public abstract NeuronalNet<IO> createNeuronalNet();
}
