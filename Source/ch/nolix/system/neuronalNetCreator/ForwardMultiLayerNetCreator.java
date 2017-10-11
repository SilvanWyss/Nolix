//package declaration
package ch.nolix.system.neuronalNetCreator;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.validator2.Validator;
import ch.nolix.system.neuron.Neuron;
import ch.nolix.system.neuronoid.InputConnection;
import ch.nolix.system.neuronoid.NeuronalNet;

//class
/**
 * A forward multi layer net creator can create forward multi layer neuronal nets, that:
 * -Have a specific number of layers.
 * -Have a specific numbers of neurons per layer.
 * -Have the property that each neuron of a layer contains all neurons of the previous layer as input neurons.
 * -Are triggered linearly through its layers.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 160
 * @param <IO> - The type of the inputs and output of the neuronal nets a forward multi layer net creator creates.
 */
public final class ForwardMultiLayerNetCreator<IO> implements INeuronalNetCreator<IO> {

	//attributes
	private int layerCount = 1;
	private int neuronsPerLayer = 1;
	private IElementTakerElementGetter<Iterable<InputConnection<IO>>, IO> outputFunction = c -> null;
	
	//method
	/**
	 * @return a new forward multi layer neoronal net.
	 */
	public NeuronalNet<IO> createNeuronalNet() {
			
		final List<Neuron<IO>> inputNeurons = new List<Neuron<IO>>();
		
		List<Neuron<IO>> previousLayer = new List<Neuron<IO>>();
		for (int i = 1; i <= getLayerCount(); i++) {
			
			List<Neuron<IO>> currentLayer = new List<Neuron<IO>>();
			
			for (int j = 1; j <= getNeuronsPerLayer(); j++) {
				
				//Creates the j-th neuron of the i-th layer.
				final Neuron<IO> neuron = new Neuron<IO>();
				if (i == 1) {
					inputNeurons.addAtEnd(neuron);
				}	
				else {
					previousLayer.forEach(n -> neuron.addInputNeuron(n));
				}		
				neuron.setOutputFunction2(outputFunction);
				
				currentLayer.addAtEnd(neuron);
			}
			
			previousLayer = currentLayer;
		}
		
		return new NeuronalNet<IO>(inputNeurons, previousLayer);
	}
	
	//method
	/**
	 * @return the number of layers of the neuronal nets this forward multi layer net creator creates.
	 */
	public int getLayerCount() {
		return layerCount;
	}
	
	//method
	/**
	 * @return the number of neurons per layer of the neuronal nets this forward multi layer net creator creates.
	 */
	public int getNeuronsPerLayer() {
		return neuronsPerLayer;
	}
	
	//method
	/**
	 * Sets the number of layers of the neuronal nets this forward multi layer net creator creates.
	 * 
	 * @param layerCount
	 * @return this forward multi layer net creator.
	 * @throws NonPositiveArgumentException if the given layer count is not positive.
	 */
	public ForwardMultiLayerNetCreator<IO> setLayerCount(final int layerCount) {
		
		//Checks if the given layer count is positive.
		Validator.suppose(layerCount).thatIsNamed("number of layers").isPositive();
		
		this.layerCount = layerCount;
		
		return this;
	}
	
	//method
	/**
	 * Sets the number of neurons per layer of the neuronal nets this forward multi layer net creator creates.
	 * 
	 * @param neuronsPerLayer
	 * @return this forward multi layer net creator.
	 * @throws NonPositiveArgumentException if the given neurons per layer is not positive.
	 */
	public ForwardMultiLayerNetCreator<IO> setNeuronsPerLayer(final int neuronsPerLayer) {
		
		//Checks if the given layer count is positive.
		Validator.suppose(neuronsPerLayer).thatIsNamed("neurons per layer").isPositive();
		
		this.neuronsPerLayer = neuronsPerLayer;
		
		return this;
	}
	
	//method
	/**
	 * Sets the output function of the neurons of the neuronal nets this forward multi layer net creator creates.
	 * 
	 * @param outputFunction
	 * @return this forward multi layer net creator.
	 * @throws NullArgumentException if the given output function is null.
	 */
	public ForwardMultiLayerNetCreator<IO> setOutputFunction(final IElementTakerElementGetter<Iterable<IO>, IO> outputFunction) {
		
		//Checks if the given output function is not null.
		Validator.suppose(outputFunction).thatIsNamed("output function").isNotNull();
		
		//Sets the output function of this forward multilayer net creator.
		this.outputFunction
		= in -> {
			
			//Creates input list.
			final List<IO> inputs = new List<IO>();
			for (InputConnection<IO> n: in) {
				inputs.addAtEnd(n.getRefOutput());
			}
			
			return outputFunction.getOutput(inputs);
		};
		
		return this;
	}
	
	//method
	/**
	 * Sets the output function of this forward multi layer net creator.
	 * 
	 * @param weightOutputFunction
	 * @return this forward multi layer net creator.
	 * @throws NullArgumentException if the given weight output function is null.
	 */
	public ForwardMultiLayerNetCreator<IO> setWeightOutputFunction(
		final IElementTakerElementGetter<Iterable<InputConnection<IO>>, IO> weightOutputFunction
	) {
		
		//Checks if the given output function is not null.
		Validator.suppose(weightOutputFunction).thatIsNamed("weight output function").isNotNull();
		
		outputFunction = weightOutputFunction;
		
		return this;
	}
}
