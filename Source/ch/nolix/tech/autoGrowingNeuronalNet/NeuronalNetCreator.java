//package declaration
package ch.nolix.tech.autoGrowingNeuronalNet;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.system.neuronalNetCreator.INeuronalNetCreator;
import ch.nolix.system.neuronoid.NeuronalNet;

//class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 20
 */
public final class NeuronalNetCreator implements INeuronalNetCreator<Double> {
	
	//method
	public NeuronalNet<Double> createNeuronalNet() {
		
		final Neuron neuron1 = new Neuron();
		final Neuron neuron2 = new Neuron().addInputNeuron(neuron1);
				
		return
		new NeuronalNet<Double>(
			new List<Neuron>(neuron1),
			new List<Neuron>(neuron2)
		);
	}
}
