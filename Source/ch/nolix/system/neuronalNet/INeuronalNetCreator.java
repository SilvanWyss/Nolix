//package declaration
package ch.nolix.system.neuronalNet;

//interface
/**
 * A neuronal net creator can create neuronal nets.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 10
 * @param <IO> 
 * The type of the elements of the inputs and outputs of the {@link NeuronalNet}s
 * a {@link INeuronalNetCreator} creates.
 */
public interface INeuronalNetCreator<IO> {

	//method declaration
	/**
	 * @return a new neuronal net.
	 */
	NeuronalNet<IO> createNeuronalNet();
}
