//package declaration
package ch.nolix.tech.autoGrowingNeuronalNet;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.system.neuronoid.NeuronalNet;
import ch.nolix.system.neuronoid.SourceNeuron;

//class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 20
 */
public final class Launcher {

	//main method
	public static void main(String[] args) {
		
		final SourceNeuron<Iterable<Double>> sourceNeuron = new SourceNeuron<Iterable<Double>>(new List<Double>(1.0));
		
		final NeuronalNet<Double> neuronalNet = new NeuronalNetCreator().createNeuronalNet();
		
		neuronalNet.addInputNeuron(sourceNeuron);
		neuronalNet.fire();
		
		System.out.println(neuronalNet.getRefOutput());
	}
}
